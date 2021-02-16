package algorithm.List.Queue.Single;

import algorithm.List.Circle.CircleLinkedList;
import core.List.List;

/**
 * 使用双向链表 实现
 * */
public class Deque<E> {
	private List<E> list = new CircleLinkedList<>();
	
	
	public int size() {
		return list.size();
	}
	
	public boolean isEmpty() {
		return list.isEmpty();
	}
	
	public void clear() {
		list.clear();
	}
	
	/**
	 * 队尾入队
	 * */
	public void enQueueRear(E element) {
		list.add(element);
	}
	
	/**
	 * 队头出队
	 * */
	public E deQueueFront() {
		return list.remove(0);
	}
	
	/**
	 * 队头入队
	 * */
	public void enQueueFront(E element) {
		list.add(0, element);
	}
	
	/**
	 * 队尾出队
	 * */
	public E deQueueRear() {
		return list.remove(list.size() - 1);
	}
	
	public E front() {
		return list.get(0);
	}
	
	public E rear() {
		return list.get(list.size() - 1);
	}
}
