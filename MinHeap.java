/**
 * @author Rajat Mishra
 */
public class MinHeap<T extends Comparable<T>> {
	private T allNodes[];
	private int capacity;
	private int size;
	private boolean isResizable;

	/**
	 * Creates an Object of MinHeap
	 * 
	 * @param capacity
	 *            initial capacity of heap.
	 * @param isResizable
	 *            should the heap be re sizable when reaches capacity.
	 */
	@SuppressWarnings("unchecked")
	public MinHeap(int capacity, boolean isResizable) {
		this.capacity = capacity;
		this.allNodes = (T[]) new Comparable[this.capacity + 1];
		this.isResizable = isResizable;
	}

	/**
	 * Creates an Object of MinHeap with given list of items
	 * 
	 * @param initialList
	 *            list of items initially added in list
	 * @param isResizable
	 *            should the heap be re sizable when reaches capacity.
	 */
	public MinHeap(T initialList[], boolean isResizable) {
		this(initialList.length, isResizable);
		init(initialList);
	}

	private void init(T[] initialList) {
		for (T t : initialList) {
			this.add(t);
		}
	}

	/**
	 * 
	 * @param item
	 *            item that need to be added in heap
	 * @return returns the successfully added item
	 * @throws ArrayIndexOutOfBoundsException
	 */
	public T add(T item) throws ArrayIndexOutOfBoundsException {
		if (size == capacity) {
			if (isResizable) {
				resizeArray();
			} else {
				throw new ArrayIndexOutOfBoundsException("Heap capacity exceeds.");
			}
		}
		size++;
		allNodes[size] = item;
		percolateUp(size);
		return item;
	}

	/**
	 * Deletes the minimum item in heap
	 * 
	 * @return returns successfully deleted item
	 * @throws ArrayIndexOutOfBoundsException
	 */
	public T deleteMin() throws ArrayIndexOutOfBoundsException {
		if (size < 1) {
			throw new ArrayIndexOutOfBoundsException("Heap is Empty.");
		} else {
			T removedItem = allNodes[1];
			allNodes[1] = allNodes[size];
			size--;
			percolateDown(1);
			return removedItem;
		}
	}

	/**
	 * 
	 * @return returns the minimum item in heap
	 */
	public T getMin() {
		return size == 0 ? null : allNodes[1];
	}

	/**
	 * 
	 * @return returns if heap is empty
	 */
	public boolean isEmpty() {
		return size == 0 ? true : false;
	}

	/**
	 * 
	 * @return returns if heap is full
	 */
	public boolean isFull() {
		return size == capacity ? true : false;
	}

	/**
	 * 
	 * @return returns the number of items in heap
	 */
	public int getSize() {
		return size;
	}

	private void percolateUp(int index) {
		while (index > 1 && isLess(allNodes[index], allNodes[index / 2])) {
			swap(index, index / 2);
			index = index / 2;
		}
	}

	private void percolateDown(int index) {
		while (index * 2 <= size) {
			int minIndex = index * 2;
			if (minIndex < size && isLess(allNodes[minIndex + 1], allNodes[minIndex])) {
				minIndex++;
			}
			if (isLess(allNodes[index], allNodes[minIndex])) {
				break;
			}
			swap(index, minIndex);
			index = minIndex;
		}
	}

	private void swap(int i, int j) {
		T temp = allNodes[i];
		allNodes[i] = allNodes[j];
		allNodes[j] = temp;
	}

	private boolean isLess(T a, T b) {
		return a.compareTo(b) < 0 ? true : false;
	}

	private void resizeArray() {
		capacity = capacity * 2;
		@SuppressWarnings("unchecked")
		T tempArray[] = (T[]) new Comparable[capacity + 1];
		System.arraycopy(allNodes, 1, tempArray, 1, size);
		allNodes = tempArray;
	}
}
