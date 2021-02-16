package algorithm.List.Queue.Circle;

@SuppressWarnings("unchecked")
public class CircleQueue<E> {
	private static final int DEFAULT_CAPACITY = 10;
	private int size = 0;
	private int front = 0;
	private E[] elements;
	
	public CircleQueue() {
		 elements = (E[]) new Object[DEFAULT_CAPACITY];
	}
	
	public int size() {
		return size;
	}
	
	public E front() {
		return elements[front];
	}
	
	
	public boolean isEmpty() {
		return size == 0;
	}
	
	public void clear() {
		for(int i = 0; i < size; i++) {
			elements[getIndex(i)] = null;
		}
		front = 0;
		size = 0;
	}
	
	public void enQueue(E element) {
		ensureCapcity(size + 1);
		
		elements[getIndex(size)] = element;
		size++;
	}
	
	public E deQueue() {
		// 出队
		E oldElement = elements[front];
		elements[front] = null;
		// 重置front
		front = getIndex(1);
		size--;
		
		return oldElement;
	}
	
	private void ensureCapcity(int capcity) {
		int oldCapcity = elements.length;
		if(oldCapcity >= capcity) return;
		
		int newCapcity = oldCapcity + (oldCapcity >> 1);
		E[] newElements = (E[])new Object[newCapcity];
		for(int i = 0; i < size; i++) {
			newElements[i] = elements[getIndex(i)];
		}
		
		elements = newElements;
		front = 0;
	}
	
	private int getIndex(int index) {
		index += front;
		/**
		 * 已知n >= 0 m > 0
		 * n % m = n - (m > n ? 0 : m);
		 * */
		int length = elements.length;
		return index - (length > index ? 0 : length);
	}
	
	@Override
	public String toString() {
		StringBuilder string = new StringBuilder();
		string.append("capcacity=").append(elements.length)
		.append(" size=").append(size)
		.append(" front=").append(front)
		.append(", [");
		for (int i = 0; i < elements.length; i++) {
			if (i != 0) {
				string.append(", ");
			}
			
			string.append(elements[i]);
		}
		string.append("]");
		return string.toString();
	}
}
