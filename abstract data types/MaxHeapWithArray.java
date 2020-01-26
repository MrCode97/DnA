
public class MaxHeapWithArray<T extends Comparable<T>> {
	private Node<T> root;
	private Node<T>[] storage;
	private int size;
	private int maxSize;

	private class Node<T> {
		int parent;
		int leftChild;
		int rightChild;
		T data;
		int index;
		int maxSize;

		public Node(int index, T data, int maxSize) {
			this.maxSize = maxSize;
			this.data = data;
			this.index = index;
			this.parent = calcParentIndex(index);
			this.leftChild = calcLeftIndex(index);
			this.rightChild = calcRightIndex(index);
		}

		private int calcParentIndex(int index) {
			return index == 0 ? -1 : (index - 1) / 2;
		}

		private int calcLeftIndex(int index) {
			int i = index * 2 + 1;
			return i <= this.maxSize ? i : -1;
		}

		private int calcRightIndex(int index) {
			int i = index * 2 + 2;
			return i <= this.maxSize ? i : -1;
		}
	}

	public MaxHeapWithArray(int maxSize) {
		this.storage = new Node[maxSize];
		this.maxSize = maxSize;
		this.size = 0;
		this.storage[this.size] = new Node<T>(0, null, maxSize);
	}

	public boolean isEmpty() {
		return this.size > 0 ? false : true;
	}
	
	public int size() {
		return this.size;
	}

	public void add(T data) {
		if (isEmpty()) {
			storage[this.size].data = data;
			this.size = 1;
		} else if (this.size < this.maxSize) {
			this.storage[this.size] = new Node<T>(this.size, data, this.maxSize);
			int parentIndex = storage[this.size].parent;
			if (parentIndex != -1)
				siftDown(parentIndex);

			this.size++;
		} else {
			throw new OutOfMemoryError("Maximum capacity reached, cannot add any more elements!");
		}
	}

	private void siftDown(int index) {
		int parent = index;
		int leftChild = storage[index].leftChild;
		int rightChild = storage[index].rightChild;

		T _parent = storage[index].data;
		T _leftChild = safeGet(leftChild);
		T _rightChild = safeGet(rightChild);

		if (_rightChild != null && (_rightChild.compareTo(_leftChild) > 0)) {
			if (_rightChild.compareTo(_parent) > 0) {
				storage[parent].data = _rightChild;
				storage[rightChild].data = _parent;
				int parentOfParent = storage[parent].parent;
				if (parentOfParent != -1)
					siftDown(parentOfParent);
			}
		} else if (_leftChild != null && (_leftChild.compareTo(_parent) > 0)) {
			storage[leftChild].data = _parent;
			storage[parent].data = _leftChild;
			int parentOfParent = storage[parent].parent;
			if (parentOfParent != -1)
				siftDown(parentOfParent);
		}

	}

	private T safeGet(int node) {
		// returns value of node if exists, otherwise returns null
		return this.maxSize > node && size >= node ? storage[node].data : null;
	}

	public String toString2() {
		if (isEmpty())
			return "\t\tnull\t\t";
		int height = (int) (Math.log(this.size) / Math.log(2));
		int divider = 2;
		int c = (int) (Math.log(this.size) / Math.log(2));
		String result = "\t".repeat(c);
		for (int i = 0; i <= height; i++) {
			for (int node = 0; node < Math.pow(2, i); node++) {
				if (i == height)
					result += "    ";
				if (storage[(int) (Math.pow(2, i) - 1 + node)] != null) {
					result += "\t".repeat(i == height ? c / divider : c / divider + 1)
							+ storage[(int) (Math.pow(2, i) - 1 + node)].data + "\t".repeat((c / divider + 1));
				}
			}
			divider++;
			result += "\n\n";
		}
		return result;
	}

	public String toString() {
		if (isEmpty())
			return "\t\tnull\t\t";
		String result = "";
		int height = (int) (Math.log(this.size) / Math.log(2));
		int tabs = height + 1;
		if (height == 3) {
			tabs = height + 1;
		} else if (height == 4) {
			tabs = height + 4;
		} else if (height == 5) {
			tabs = height + 11;
		}

		for (int level = 0; level <= height; level++) {
			for (int nodes = 0; nodes < Math.pow(2, level); nodes++) {
				try {
					if (tabs == 0) {
						result += ("    " + storage[(int) Math.pow(2, level) - 1 + nodes].data + "   ");
					} else {
						result += ("\t".repeat(tabs) + storage[(int) Math.pow(2, level) - 1 + nodes].data
								+ "\t".repeat(tabs));
					}
				} catch (Exception e) {
				}
			}
			result += "\n\n";
			tabs /= 2;
		}
		return result;

	}
}