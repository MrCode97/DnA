
public class Queue<T> {
	private Node<T> start;
	private Node<T> end;
	private int size=0;
	
	private class Node<T>{
		Node<T> next;
		Node<T> prev;
		T data;
		
		public Node(T data) {
			this.data = data;
		}
		
		public Node(Node<T> preNode, Node<T> nextNode, T data) {
			this.data = data;
			this.next = nextNode;
			this.prev =preNode;
		}
		
		public void free() {
			this.next = this.prev = null;
			this.data = null;
		}
	}
	
	public boolean isEmpty() {
		return this.size > 0 ? false : true;
	}
	public int size() {
		return this.size;
	}
	
	public void enqueue(T data) {
		if(isEmpty()) {
			this.start = this.end = new Node<T>(data);
		} else {
			Node<T> toAdd = new Node<T>(end, null, data);
			this.end.next = toAdd;
			this.end = toAdd;
		}
		size++;
	}
	public T peek() {
		if(isEmpty()) return null;
		return this.start.data;
	}
	public T dequeue() {
		T data = peek();
		if(data != null) {
			if(this.start == this.end) {
				this.end.free();
				this.end = this.start = null;
				size=0;
			} else {
				this.start = this.start.next;
				this.start.prev.free();
				this.start.prev=null;
				size--;
			}
		}
		return data;
	}
	public String toString() {
		if(isEmpty()) return "[]";
		Node<T> pointer = this.start;
		String result = "[" + (String) pointer.data;
		pointer = pointer.next;
		while(pointer != null) {
			result += " -> " + pointer.data;
			pointer = pointer.next;
		}
		result += "]";
		pointer = null;
		return result;
	}
	public void clear() {
		Node<T> pointer = this.start;
		while(pointer != null) {
			Node<T> oldPointer = pointer;
			oldPointer.free();
			pointer = pointer.next;
		}
		pointer=null;
		size=0;
	}
}
