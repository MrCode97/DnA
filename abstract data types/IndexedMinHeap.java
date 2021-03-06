
public class IndexedMinHeap<T extends Comparable<T>> extends IndexedHeap<T> {
	@Override
	protected void siftUp(int i) {
		int index = i;
		int parentIndex = (index -1) /2;
		
		do {
			if(heap.get(index).compareTo(heap.get(parentIndex)) < 0){
				swap(index, parentIndex);
				
				index = parentIndex;
				parentIndex = (parentIndex -1)/2;
			} else {
				break;
			}
		} while (parentIndex > 0);
	}
	
	@Override
	protected int getLargest(int parent, int leftChild, int rightChild, int lastIndex) {
		if(rightChild <= lastIndex) {
			if(heap.get(leftChild).compareTo(heap.get(parent)) < 0 
					&& heap.get(leftChild).compareTo(heap.get(rightChild)) < 0) return 1;
			if(heap.get(rightChild).compareTo(heap.get(parent)) < 0) return 2;
		} else {
			if(heap.get(leftChild).compareTo(heap.get(parent)) < 0) return 1;
		}
		return 0;
	}
	
	@Override
	public void update(T original, T update) {
		heap.set(lookup.get(update), update);
		if(update.compareTo(original) < 0) {
			siftUp(lookup.get(update));
		} else {
			siftDown(lookup.get(update));
		}
	}
}
