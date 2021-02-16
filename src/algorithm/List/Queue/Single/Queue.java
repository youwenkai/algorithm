package algorithm.List.Queue.Single;

import algorithm.List.ArrayList;

/**
 * 动态数组实现
 * */
public class Queue<E> {
	private ArrayList<E> list = new ArrayList<>();
	
	public int size() {
		return list.size();
	}
	
	public boolean isEmpty() {
		return list.isEmpty();
	}
	
	public void clear() {
		list.clear();
	}
	
	public void enQueue(E element) {
		list.add(element);
	}
	
	public E deQueue() {
		return list.remove(0);
	}
	
	public E front() {
		return list.get(0);
	}
}
