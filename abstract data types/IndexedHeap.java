import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class IndexedHeap <T extends Comparable<T>> {
	protected List<T> heap = new ArrayList<T>();
	protected Map<T, Integer> lookup = new HashMap<>();
	
	public boolean isEmpty() {
		return heap.size() > 0 ? false : true;
	}
	
	public void add(T element) {
		if(isEmpty()) {
			heap.add(element);
			lookup.put(element, 0);
		} else {
			heap.add(element);
			lookup.put(element, heap.size());
			siftUp(heap.size() -1);
		}
	}
	public T peek() {
		return heap.size() > 0 ? heap.get(0) : null;
	}
	
	public T poll() {
		T result = peek();
		swap(0, heap.size()-1);
		lookup.remove(heap.get(heap.size()-1));
		heap.remove(heap.size()-1);
		siftDown(0);
		return result;
	}
	
	public void remove(T object) {
		int idx = lookup.get(object); 
		swap(idx, heap.size()-1);
		heap.remove(heap.size()-1);
		lookup.remove(object);
		siftDown(idx);
	}
	
	public void addOrUpdate(T update) {
		if(lookup.containsKey(update)) {
			update(update, update);
		} else {
			add(update);
		}
	}
	
	public void update(T original, T update) {
		//lookup.put(update, lookup.get(original));
		//lookup.remove(original);
		
		heap.set(lookup.get(update), update);
		if(update.compareTo(original) > 0) {
			siftUp(lookup.get(update));
		} else {
			siftDown(lookup.get(update));
		}
	}
	
	protected void siftDown(int i) {
		int parent = i;
		int leftChild = 2*parent +1;
		int rightChild = 2*parent +2;
		int lastIndex = heap.size()-1;
		int largest =0;
		while(2*parent+1 <= lastIndex) {
			leftChild = 2*parent +1;
			rightChild = 2*parent +2;
			largest = getLargest(parent, leftChild, rightChild, lastIndex);

			if(largest==2) {
				swap(parent, rightChild);
				parent = rightChild;
			} else if(largest==1) {
				swap(parent, leftChild);
				parent = leftChild;
			} else {
				break;
			}
			
		}
	}

	protected int getLargest(int parent, int leftChild, int rightChild, int lastIndex) {
		if(rightChild <= lastIndex) {
			if(heap.get(leftChild).compareTo(heap.get(parent)) > 0 
					&& heap.get(leftChild).compareTo(heap.get(rightChild)) > 0) return 1;
			if(heap.get(rightChild).compareTo(heap.get(parent)) > 0) return 2;
		} else {
			if(heap.get(leftChild).compareTo(heap.get(parent)) > 0) return 1;
		}
		return 0;
	}

	protected void siftUp(int i) {
		int index = i;
		int parentIndex = (index -1) /2;
		
		do {
			if(heap.get(index).compareTo(heap.get(parentIndex)) > 0){
				swap(index, parentIndex);
				
				index = parentIndex;
				parentIndex = (parentIndex -1)/2;
			} else {
				break;
			}
		} while (parentIndex > 0);
	}

	protected void swap(int index, int parentIndex) {
		lookup.put(heap.get(index), parentIndex);
		lookup.put(heap.get(parentIndex), index);
		
		T tmp = heap.get(index);
		heap.set(index, heap.get(parentIndex));
		heap.set(parentIndex, tmp);
		tmp = null;
	}
	
	public String toString() {
		if (isEmpty())
			return "\t\tnull\t\t";
		String result = "";
		int height = (int) (Math.log(heap.size()) / Math.log(2));
		int tabs = height + 1;
		if (height==2) {
			tabs = height;
		} else if (height == 3) {
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
						result += ("    " + heap.get((int) Math.pow(2, level) - 1 + nodes) + "   ");
					} else {
						result += ("\t".repeat(tabs) + heap.get((int) Math.pow(2, level) - 1 + nodes)
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
