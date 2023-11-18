import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Iterator;

/**
 * @copyright 한국기술교육대학교 컴퓨터공학부 객체지향개발론및실습
 * @version 2023년도 2학기
 * @author 김상진 
 * 반복자 패턴
 * 배열 기반 비정렬 범용 리스트
 * 이 코드의 중심내용은 자바에서 범용 자료구조를 만들때 겪게 되는 번거로움에 대한 것임.

 * 구현 문제점 1. Object 배열을 이용하여 구현할 수 밖에 없음(여기서 오는 불편함을 막고자 강제형변환하여 T배열을 사용함)
 * 구현 문제점 2. T가 수정 가능한 객체일 때 반환 받은 것을 수정하면 리스트에도 영향을 줌
 * >> 이 문제를 해결하기 위해 clone을 사용하기 힘듦(clone을 사용하여 해결할 수 있지만 reflection을 사용해야하는 것에서 불편함)
 * >> 모든 T가 이 문제가 있는 것은 아니기 때문에 자바는 자료구조(여기서는 범용 ArrayList)를 구현할 때
 * >> 복제하여 저장하거나 복제하여 반환하는 형태로 구현하지 않음
 *
 *
 * 자바에서 범용리스트 구현시 그 안에 배열을 사용한다고 하면 범용배열, 이를태면 T[ ]list=new T[capacity];이런 식으로 만들수
 * 없고 항상 Object를 사용한 Object배열을 만들수 밖에 없습니다. 이것은 자바의 템플릿 사용 규칙 때문인데 이러한 템플릿 사용 규칙
 * 때문에 반복자뿐만이 아니라 여러가지 다른것을 사용할 때 문제점이 있습니다(하지만 배포된 교수의 코드는 강제형변환을 통해서 T배열을 사용함).

 * 또한 템플릿T가 불변객체가 아닌 수정 가능한 객체일때 반환 받은 것을 수정하면 원본인 items배열에도 영향을 주게 됩니다.
 * 이렇게 수정할 수 있는 객체를 얻었을 때 원본이 변하는 문제는 곧 반복자를 사용했을때 반복자를 사용하여 객체데이터를 얻어 그
 * 것을 수정하면 원본이 수정되는 문제점과 같은 것이다(참고로 반복자의 next()를 통해 얻는 객체는 강제형변환 해주어 Object객체가 아닌 T객체를 얻게 하였다).
 *
 * 이러한 원본이 변하는 문제점을 해결하기 위하여 T를 대상으로 clone을 사용하면 되는데 clone을 사용하기 위해서 T extends Clonable을
 * 해주어도 list배열이 T가 아닌 Object배열 이므로 Clone을 사용하기 어렵습니다. 이에 대한 해결책으로 교수의 코드에서는 new Object[capacity]
 * 로 배열을 생성한뒤 이를 강제 형변환하여 T[ ] items 로써 사용할 수 있게함. 하지만 이렇게 T형 배열을 가진다고 하더라도 clone객체를 얻기 위해서
 * reflection라이브러리를 사용해야만 clone객체를 얻을 수 있어서 매우 까다롭습니다.
 * 한편 이러한 까다로움은 데이터를 next()를 통해 얻어낼때만 문제가 되는것이 아니라 push()해서 데이터를 넣을 때도 여전히 까다롭습니다.
 * 받은 데이터를 복제하여 자료구조(T형 배열)에 저장해야 하는 번거로움이 남아있는 것입니다.  이상으로 자바에서 범용자료구조를 만들때 겪는 번거로움에 대해 이야기 하였다.
 */
public class UnsortedArrayList<T> implements Iterable<T> {
	private int capacity = 5;
	@SuppressWarnings("unchecked")
	private T[] items = (T[])(new Object[capacity]);
//	private T[]items=new T[capacity];할수없음. 	private Object[]items=new Object[capacity];이렇게 밖에 못함. 혹은 위와 같이
//	강제형변환하는방법으로 해야함.

	private int size = 0;
	
	private class ArrayListIterator implements Iterator<T>{
		private int index = 0;
		@Override public boolean hasNext() {
			return index < size;
		}

//		@Override public T next(){
//			T retVal=(T)items[index++];
//			return retVal;
//		}
		@Override public T next() {
//			 return items[index++];
			return getClonedIfCloneable(items[index++]);
		}
	}
	
	public boolean isFull(){
		return false;
	}
	
	public boolean isEmpty(){
		return size==0;
	}
	
	public int size() {
		return size;
	}
	
	@SuppressWarnings("unchecked")
	private T getCloned(T item) {
		T cloned = null;
		try {
			Class<?> C = item.getClass();
			Method cloneMethod = C.getMethod("clone");
			cloned = (T)cloneMethod.invoke(item);
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		return cloned;
	}
	
	private T getClonedIfCloneable(T item) {
		if(item instanceof Cloneable) return getCloned(item);
		return item;
	}
	
	public T peekBack() {
		if(isEmpty()) throw new IllegalStateException();
		// return items[size - 1];
		return getClonedIfCloneable(items[size - 1]);
	}
	
	private void increaseCapacity() {
		capacity *= 2;
		items = Arrays.copyOf(items, capacity);
	}
	
	public void pushBack(T item){
		if(size == capacity) increaseCapacity();
		//list[size] = item;
		items[size] = getClonedIfCloneable(item);
		++size;
	}
	
	public T popBack() {
		if(isEmpty()) throw new IllegalStateException();
		T retval = items[size - 1];
		items[size - 1] = null; // 선택사항
		--size;
		return retval;
	}
	
	public T get(int index){
		if(index < 0 && index >= size) throw new IndexOutOfBoundsException("유효하지 않은 색인 사용");
		//return items[index];
		return getClonedIfCloneable(items[index]);
	}
	
	public void remove(T item) {
		for(int i = 0; i < size; ++i)
			if(items[i].equals(item)) {
				for(int j = i + 1; j < size; ++j) {
					items[j - 1] = items[j];
				}
				--size;
				items[size] = null; // 선택사항
				break;
			}
	}
	
	@Override public Iterator<T> iterator() {
		return new ArrayListIterator();
	}
}
