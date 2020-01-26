
public class Stack<T> {
	private Node<T> base;
	private Node<T> top;
	private int size=0;
	
	private class Node <T> {
		Node<T> nextDown=null;
		Node<T> nextUp=null;
		T data;
		
		public Node(T data) {
			this.data = data;
		}
		
		public Node(Node<T> nextDown, Node<T> nextUp, T data) {
			this.data = data;
			this.nextDown = nextDown;
			this.nextUp = nextUp;
		}
		
		public void free() {
			this.data = null;
			this.nextDown = null;
			this.nextUp = null;
		}
	}
	
	public int size() {
		return this.size;
	}
	
	public boolean isEmpty() {
		return this.size > 0 ? false : true; 
	}
	
	public void push(T data) {
		if(isEmpty()) {
			this.base = this.top = new Node<T>(data);
		} else {
			Node<T> toAdd = new Node<T>(this.top, null, data);
			this.top.nextUp = toAdd;
			this.top = toAdd;
			toAdd = null;
		}
		this.size++;
	}
	public T peek() {
		if(isEmpty()) return null;
		return this.top.data;
	}
	
	public T pop() {
		T result = peek();
		if(result != null) {
			if(this.top == this.base) {
				this.top.free();
				this.top = this.base = null;
				size=0;
			} else {
				this.top = this.top.nextDown;
				this.top.nextUp.free();
				this.top.nextUp=null;
				this.size--;
			}
		}
		return result;
	}
	
	public String toString() {
		if(isEmpty()) return "----empty stack----";
		String result = "-------TOP------";
		Node<T> pointer = this.top;
		while(pointer != null) {
			result += "\n\t" + pointer.data;
			pointer = pointer.nextDown;
		}
		result += "\n------BOTTOM-----";
		// freeing pointer
		pointer = null;
		return result;
	}
	
	public void clear() {
		Node<T> pointer = this.top;
		while(pointer != null) {
			Node<T> oldPointer = pointer;
			pointer = pointer.nextDown;
			oldPointer.free();
		}
		this.top = this.base = null;
		pointer = null;
		size=0;
	}
}
