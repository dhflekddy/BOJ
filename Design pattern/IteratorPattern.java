/*
반복자(Iterator)를 갖는 범용자료구조 구현 코드

-범용자료구조의 반복자를 구현하려면 해당범용자료구조는 Iterable<T>인터페이스를 구현해야함.
-Iterable<T>인터페이스를 구현해야 한다는 것은 iterator()메서드를 구현한다는 것
(public Iterator<T> iterator(){} )
-내가 반복자를 구현할 일이 있을까? 없을 것이다. 범용자료구조도 만들일이 없을것이다. 하지만
구현방식만 기록해 놓자면 아래와 같이 반환값으로 ArrayListIterator를 반환하고 이 반복자는
hasNext와 next를 구현한, 즉, Iterator<T>인터페이스를 구현한 클래스이다.
 */

import java.util.Iterator;


public class UnsortedArrayList<T> implements Iterable<T>{
    private int size;
    private int capacity=5;
    private T[]items=(T[])new Object[capacity];
    private class ArrayListIterator implements Iterator<T>{
        private int index;
        @Override
        public boolean hasNext(){
            return index<size;
        }
        @Override
        public T next(){
            return items[index++];
        }
    }

    @Override
    public Iterator<T> iterator(){
        return new ArrayListIterator();//이렇게 Iterator클래스를 직접만들어서
        //반환할 수도 있지만 snapshot방식으로 사용할 어떠한 보통의 자료구조.iterator(); 를 반환할 수도 있음.
        // 예를들면 list.iterator();
        //그러면 그 자료구조의 iterator는 디폴트로hasNext(), next()의 기능이 있는 것임.
    }

}
