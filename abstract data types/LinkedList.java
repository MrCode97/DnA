public class LinkedList <T> {
	private Node <T> start;
	private Node <T> end;
	private int size=0;

	private class Node <T> {
		T data;
		Node <T> next;
		
		public Node(T data) {
			this.data = data;
		}
		
		public void free() {
			this.data = null;
			this.next = null;
		}
	}
	
	public boolean isEmpty() {
		return size > 0 ? false : true;
	}

	public void add(T data) {
		if(isEmpty()) {
			this.start = new Node<T>(data); 
			this.end = this.start;
		} else {
			this.end.next = new Node<T>(data);
			this.end = this.end.next;
		}
		this.size++;
	}
	
	public boolean contains(T data) {
		Node<T> pointer = this.start;
		
		while(pointer != null) {
			if(pointer.data == data) {
				pointer = null;
				return true;
			}
		}
		// free pointer
		pointer = null;
		return false;
	}
	
	public T removeFirst() {
		// returns data-value
		Node<T> pointer = this.start;
		this.start = pointer.next;
		T data = (T) pointer.data;
		this.size--;
		
		// free node
		pointer.free();
		pointer = null;
		return data;
	}
	
	public T removeLast() {
		Node<T> pointer = this.start;
		while(pointer.next != this.end) pointer = pointer.next;
		T data = this.end.data;
		this.end = pointer;
		this.size--;
		
		pointer = pointer.next;
		pointer.free();
		this.end.next = null;
		pointer = null;
		
		return data;
	}
	
	public void clear() {
		Node<T> pointer = this.start;
		while(pointer != this.end.next) {
			Node<T> tmp = pointer;
			pointer = pointer.next;
			tmp.free();
			tmp = null;
		}
		this.size=0;
	}
	
	public T get(int i) {
		if(i > this.size || i < 0) throw new IllegalArgumentException("Index not in range");
		int counter = 0;
		Node<T> pointer = this.start;
		while(counter++ != i) {
			pointer = pointer.next;
		}
		T data = (T) pointer.data;
		pointer = null;
		return data;
	}
	
	public String toString() {
		if(this.isEmpty()) return "[]";
		Node<T> pointer = this.start;
		String result = "[" + this.start.data;
		while(pointer != this.end) {
			pointer = pointer.next;
			result += ", " + pointer.data;
		}
		result += "]";
		return result;
	}


}
