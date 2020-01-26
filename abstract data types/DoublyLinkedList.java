
public class DoublyLinkedList<T> {
	private Node<T> start;
	private Node<T> end;
	private int size = 0;
	
	private class Node <T> {
		Node<T> prev;
		Node<T> next;
		T data;
		
		public Node(Node<T> prev, Node<T> next, T data) {
			this.prev = prev;
			this.next = next;
			this.data = data;
		}
		public Node(T data) {
			this.prev = null;
			this.next = null;
			this.data = data;
		}
		public void free() {
			this.data=null;
			this.next=null;
			this.prev=null;
		}
	}
	
	public int size() {
		return this.size;
	}
	
	public boolean isEmpty() {
		return this.size > 0 ? false : true;
	}
	
	public void clear() {
		Node<T> pointer = this.start;
		while(pointer != null) {
			Node<T> oldPointer = pointer;
			pointer= pointer.next;
			oldPointer.free();
		}
		this.size=0;
	}
	
	public void addFirst(T data) {
		if(this.isEmpty()) {
			this.end = this.start = new Node<T>(data);
			size++;
		} else {
			Node<T> nodeToAdd = new Node<T>(null, this.start, data);
			this.start.prev = nodeToAdd;
			this.start = nodeToAdd;
			size++;
			// free newNode reference
			nodeToAdd = null;
		}
	}
	
	private void addLast(T data) {
		if(this.isEmpty()) {
			this.start = this.end = new Node<T>(data);
			size++;
		} else {
			Node<T> nodeToAdd = new Node<T>(this.end, null, data);
			this.end.next = nodeToAdd;
			this.end = nodeToAdd;
			size++;
			// free newNode reference
			nodeToAdd = null;
		}
	}
	private Node<T> getPointerTo(int index) throws OutOfBoundsException{
		// Error handling
		if(index > this.size) {
			throw new OutOfBoundsException("Index is higher than then number of elements in list");
		} else if(index < 0) {
			throw new OutOfBoundsException("List starts at zero, no negative indecies supported");
		}
		// Get to index
		int counter = 0;
		Node <T> pointer = this.start;
		while(counter != index) {
			pointer = pointer.next;
			counter++;
		}
		return pointer;
		
	}
	private void add(T data, int at) throws OutOfBoundsException {
		Node<T> pointer = getPointerTo(at);
		// Insert
		Node<T> newNext = pointer;
		Node<T> newPrev = pointer.prev;
		Node<T> nodeToAdd = new Node<T>(newPrev, newNext, data);
		newNext.prev = nodeToAdd;
		newPrev.next = nodeToAdd;
		// free references
		pointer = newNext = newPrev = nodeToAdd = null;
		size++;
	}
	
	public void add(T data) {
		addLast(data);
	}
	
	public void insert(T data, int index) throws OutOfBoundsException {
		if(index==0) {
			addFirst(data);
		} else {
			add(data, index);
		}
	}
	
	public T getValue(int at) {
		try {
			Node<T> pointer = getPointerTo(at);
			T data = pointer.data;
			//free pointer reference
			pointer = null;
			return data;
		} catch (OutOfBoundsException e) {
			e.getMessage();
		}
		return null;
	}
	
	public boolean setValueAt(int index, T newData) {
		try {
			Node<T> pointer = getPointerTo(index);
			pointer.data = newData;
			// free pointer-reference
			pointer = null;
			return true;
		} catch (OutOfBoundsException e) {
			e.getMessage();
			return false;
		}
	}
	private int find(T data) {
		// returns index if found otherwise -1
		int counter = 0;
		Node<T> pointer = this.start;
		
		while(pointer != null) {
			if(pointer.data.equals(data)) {
				return counter;
			}
			pointer = pointer.next;
			counter++;
		}
		return -1;
	}

	public boolean contains(T data) {
		return find(data)==-1 ? false : true;
	}
	
	public int getIndexOf(T data) throws OutOfBoundsException {
		int index = find(data);
		if(index==-1) throw new OutOfBoundsException("no such element");
		return index;
	}
	
	private int replace(T dataFrom, T dataTo, int amount) throws OutOfBoundsException {
		// replaces the first `amount` c
		int changes = 0;
		Node<T> pointer = getPointerTo(0);
		
		while(pointer != null && changes < amount) {
			if(pointer.data.equals(dataFrom)) {
				pointer.data = dataTo;
				changes++;
			}
			pointer = pointer.next;
		}
		// free pointer
		pointer = null;
		if(changes < amount) {
			return changes;
		} else if (changes == amount) {
			return 1;
		} else {
			return -1;
		}
	}
	
	public void replaceAll(T dataFrom, T dataTo) throws OutOfBoundsException {
		replace(dataFrom, dataTo, Integer.MAX_VALUE);
	}
	public boolean replaceFistX(T dataFrom, T dataTo, int firstX) {
		// firstX specifies how many changes shall be made
		int ans=-1;
		try {
			ans = replace(dataFrom, dataTo, firstX);
		} catch (OutOfBoundsException e) {
			e.printStackTrace();
		}
		
		if(ans==1) {
			return true;
		} else if(ans > 1) {
			System.out.println(firstX + " changes were requested but list only contained: " + " occurrences, so only: " + ans + " have been made!");
			return true;
		} else {
			//System.out.println(ans + " changes have been made");
			return false;
		}
		
	}
	
	private void removeFirst() {
		if(this.isEmpty()) return;
		Node<T> nodeToRemove = this.start; 
		this.start = this.start.next;
		this.start.prev = null;
		this.size--;
		// freeing references
		nodeToRemove.free();
		nodeToRemove=null;
	}
	private void removeLast() {
		if(this.isEmpty()) return;
		Node<T> nodeToRemove = this.end;
		this.end = this.end.prev;
		this.end.next = null;
		this.size--;
		// freeing references
		nodeToRemove.free();
		nodeToRemove=null;
	}
	private void removeAt(int index) throws OutOfBoundsException {
		Node<T> nodeToRemove = getPointerTo(index);
		nodeToRemove.prev.next = nodeToRemove.next;
		nodeToRemove.next.prev = nodeToRemove.prev;
		this.size--;
		// freeing references
		nodeToRemove.free();
		nodeToRemove = null;
	}
	public void remove(int index) throws OutOfBoundsException {
		if(index < 0 || index > this.size-1) throw new OutOfBoundsException("no such index exists");
		if(index==0) {
			removeFirst();
		} else if(index == this.size-1) {
			removeLast();
		} else {
			removeAt(index);
		}
	}
	public String toString() {
		if(isEmpty()) return "[]";
		
		String result = "[\"";
		Node<T> pointer = this.start;
		result += pointer.data;
		pointer=pointer.next;
		
		while(pointer != null) {
			result += "\", \"" + pointer.data;
			pointer = pointer.next;
		}
		result += "\"]";
		return result;
	}
}




