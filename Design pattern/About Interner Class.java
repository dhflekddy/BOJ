import java.util.Iterator;

/**
 * @copyright 한국기술교육대학교 컴퓨터공학부 객체지향개발론및실습
 * @version 2023년도 2학기
 * @author 김상진 
 * 반복자 패턴
 * 단일 연결구조 기반 비정렬 범용 리스트
 * UnsortedArrayList처럼 getClonedIfCloneable를 만들어 복제하여 저장하고
 * 복제하여 반환할 수 있음. 보통은 모든 T가 복제가 필요한 것은 아니며 효율성 때문에 
 * 이와 같이 구현하지 않음
 */
public class UnsortedLinkedList<T> implements Iterable<T> {

	private static class Node<T>{
		private T item = null;
		private Node<T> next = null;
		public Node() {}
		public Node(Node<T> next) {
			this.next = next;
		}
	}
	/*
	내부 클래스 복습:내부 클래스는 언제 만드는 것이 적절한가?  내부 클래스가 매우 간단하게 이루어지고
	외부클래스에서만 내부클래스가 될 클래스를 사용할 경우(캡슐화, 은닉성을 얻을 수 있음).
	즉 다른 클래스에서는 내부클래스가 될 클래스를 사용하지 않을때.


	내부 클래스는 외부 클래스의 맴버변수처럼 사용된다. 따라서 내부 클래스에서 외부클래스의 맴버변수에 자유롭게 접근할 수 있다.
	하지만 외부클래스에서는 내부클래스의 객체를 만들어야 내부클래스의 변수에 접근해야 한다. 그리고 이건 당연한 것이다.
	왜? 내부 클래스는 클래스니까! 클래스안의 멤버에 접근하려면 당연히 객체를 생성하는게 맞는 거니까!
	 */

	/*
   왜 LinkedListIterator처럼 반복자 클래스를 내부 클래스로 정의해 주어야 하나? 가장 직접적인 원인은
   내부클래스인 반복자 클래스에서 외부클래스의 멤버변수의 데이터(list, size)를 사용하고 있어서 그런거임.
   또한 내부 클래스는 외부클래스에서 밖에 쓰이지 않고 내부 클래스가될 클래스가 충분히 간단하기 때문에
   반복자는 내부 클래스안에 정의하는 게 맞음.


	반복자 클래스는 자료구조 클래스 내에서만 쓰이므로 내부클래스로 정의하는 것이 맞다. 하지만 반복자를 독립 클래스로
	정의했을때 어떤 형식으로 굴러 가는지 보여주는 것이 위의 BST파일이다. 즉, 자료구조인 BST,
	반복자인 BST_BFS_Iterator, BST_Inorder_Iterator 그리고 데이터 클래스인 TreeNode가 모두 독립클래스로서
	각각 분리되어 있음.
	 */
	private class LinkedListIterator implements Iterator<T>{
		private Node<T> curr = head;
		@Override public boolean hasNext() {
			return curr != null;
		}
		@Override public T next() {
			T ret = curr.item;
			curr = curr.next;
			return ret;
		}
	}
	
	private Node<T> head = null;
	private int size = 0;
	
	public boolean isFull() {
		return false;
	}
	
	public boolean isEmpty() {
		return size == 0;
	}
	
	public int size() {
		return size;
	}
	
	public void pushFront(T item) {
		Node<T> newNode = new Node<>();
		newNode.item = item;
		newNode.next = head;
		head = newNode;
		++size;
	}
	
	public T popFront() {
		if(isEmpty()) throw new IllegalStateException();
		Node<T> popNode = head;
		head = head.next;
		--size;
		return popNode.item;
	}
	
	public T peekFront() {
		if(isEmpty()) throw new IllegalStateException();
		return head.item;
	}
	
	public boolean find(T item) {
		Node<T> curr = head;
		while(curr != null) {
			if(curr.item.equals(item)) return true;
			curr = curr.next;
		}
		return false;
	}
	
	public void remove(T item) {
		if(isEmpty()) return;
		Node<T> dummy = new Node<>(head);
		Node<T> prev = dummy;
		Node<T> curr = head;
		while(curr != null) {
			if(curr.item.equals(item)) {
				prev.next = curr.next;
				--size;
				break;
			}
			prev = curr;
			curr = curr.next;
		}
		head = dummy.next;
	}
	
	@Override
	public Iterator<T> iterator() {
		return new LinkedListIterator();
	}
}
