import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class MaxHeapWithPointers<T extends Comparable<T>> {
	private Map<T, List<Node<T>>> lookup = new HashMap<T, List<Node<T>>>();
	private Node<T> root;
	private Node<T> lastAdded;
	private Node<T> nextFree; // indicates the last free node (i.e pointer to object where we want to add stuff), updates by `updateLast()`
	private int size=0;
	
	private class Node<T>{
		private Node<T> parent;
		private Node<T> leftChild;
		private Node<T> rightChild;
		private T data;
		
		public Node (Node<T> parent, Node<T> leftChild, Node<T> rightChild, T data){
			this.parent = parent;
			this.leftChild = leftChild;
			this.rightChild = rightChild;
			this.data = data;
		}
		
		public void free() {
			boolean isLeftChild = this.parent.leftChild == this ? true : false;
			Node<T> _parent = this.parent;
			this.data = null;
			this.leftChild = this.rightChild = this.parent = null;
			if(isLeftChild) {
				_parent.leftChild=null;
			} else if(! isLeftChild) _parent.rightChild=null;
			_parent =null;
		}
		
		public void clear() {
			this.leftChild = this.rightChild = null;
			this.data = null;
		}
		
	}
	
	public MaxHeapWithPointers(){
	}
	
	public boolean isEmpty() {
		return this.size > 0 ? false : true;
	}
	
	public int size() {
		return this.size;
	}
	
	public void add(T data) {
		if(isEmpty()) {
			this.root = new Node<T>(null, null, null, data);
			this.root.data = data;
			this.lookup.put(data, new ArrayList<Node<T>>());
			this.lookup.get(data).add(root);
			size++;
			
			// initialize last pointer
			this.nextFree = new Node<T>(this.root, null, null, null);
			this.root.leftChild = this.nextFree;
		} else {
			// Adding it to the heap
			this.nextFree.data = data;
			
			// Update indices
			if(this.lookup.containsKey(data)) {
				this.lookup.get(data).add(this.nextFree);
			} else {
				this.lookup.put(data, new ArrayList<Node<T>>());
				this.lookup.get(data).add(this.nextFree);
			}
			// keep track of last added node
			this.lastAdded = this.nextFree;
			size++;
			
			// restore heap-property
			//siftUp(this.nextFree.parent);
			siftUp(this.nextFree);
			
			// get a new node for further adding
			updateLast();
			

		}
	}
	
	public void removeKey(T data) {
		// removes *all* same keys.
		List<Node<T>> copy = this.lookup.get(data);
		this.size = this.size - copy.size();
		while(copy.size()>0) {
			Node<T> next = copy.get(0);
			swap(next, this.lastAdded);
			this.nextFree.free();
			this.nextFree = this.lastAdded;
			this.lastAdded.clear();
			if(next.leftChild != null) siftDown(next);
			
			copy.clear();
			this.lookup.get(data).forEach(x -> {
				if(x!=null) copy.add(x);
			});
		}
		// remove references to key in lookup
		this.lookup.remove(data);
	}

	private void siftDown(Node<T> parent) {
		// swaps biggest node with parent if is bigger than parent
		if ((nodeIsDefined(parent.rightChild) && parent.rightChild.data.compareTo(parent.data) > 0)
				|| parent.leftChild.data.compareTo(parent.data) > 0) {
			if (nodeIsDefined(parent.rightChild) && parent.rightChild.data.compareTo(parent.leftChild.data) > 0) {
				swap(parent, parent.rightChild);
				if(parent.rightChild.leftChild != null) siftUp(parent.rightChild); 
			} else {
				swap(parent, parent.leftChild);
					if(parent.leftChild.leftChild != null) siftUp(parent.leftChild);
			}

		}
	}

	private void siftUp(Node<T> node) {
		while(node.parent != null && node.data.compareTo(node.parent.data) > 0) {
			swap(node.parent, node);
			node = node.parent;
			node.parent = node.parent;
		}
		return;
		
	}
	
	private void swap(Node<T> parent, Node<T> childNode) {
		// swap values of nodes (nodes stay the same)
		// update lookup table
		this.lookup.get(parent.data).remove(parent);
		this.lookup.get(childNode.data).add(parent);
		this.lookup.get(childNode.data).remove(childNode);
		this.lookup.get(parent.data).add(childNode);
		// actually swap values
		T tmp = parent.data;
		parent.data = childNode.data;
		childNode.data = tmp;
		tmp = null;
		return;
	}

	private boolean nodeIsDefined(Node<T> node) {
		return this.lookup.containsKey(node);
	}

	private void updateLast() {
		// same level: left -> right node
		if(this.nextFree.parent.rightChild == null) {
			this.nextFree.parent.rightChild = new Node<T>(this.nextFree.parent, null, null, null);
			this.nextFree = this.nextFree.parent.rightChild;
		} else {
			// level / height is full, starting new / deeper level (on the left)
			this.nextFree.parent.leftChild.leftChild = new Node<T>(this.nextFree.parent.leftChild, null, null, null);
			this.nextFree = this.nextFree.parent.leftChild.leftChild;
		}
	}
	
}
