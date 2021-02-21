package core.Heap;

import java.util.Comparator;

/**
 * 优先级队列
 * 底层实现用二叉堆 即大顶堆
 * */
public class PriorityQueue<E> {
	private BinaryHeap<E> heap;
	
	public PriorityQueue(Comparator<E> comparator) {
		heap = new BinaryHeap<>(comparator);
	}
	
	public PriorityQueue() {
		this(null);
	}
	
	public int size() {
		return heap.size;
	}
	
	public boolean isEmpty() {
		return heap.isEmpty();
	}
	
	public void clear() {
		heap.clear();
	}
	
	public void enQueue(E element) {
		heap.add(element);
	}
	
	public E deQueue() {
		return heap.remove();
	}
	
	public E front() {
		return heap.get();
	}
}
