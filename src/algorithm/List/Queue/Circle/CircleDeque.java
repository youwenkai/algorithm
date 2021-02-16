package algorithm.List.Queue.Circle;


/**
 * 循环双端队列
 * */
@SuppressWarnings("unchecked")
public class CircleDeque<E> {
	private static final int DEFAULT_CAPACITY = 10;
	private int size = 0;
	private int front = 0;
	private E[] elements;
	
	public CircleDeque() {
		elements = (E[])new Object[DEFAULT_CAPACITY];
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
		for (int i = 0; i < size; i++) {
			elements[getIndex(i)] = null;
		}
		front = 0;
		size = 0;
	}
	/**
	 * 头部入队
	 * */
	public void enQueueFront(E element) {
		ensureCapcity(size + 1);
		
		front = getIndex(-1);
		
		elements[front] = element;
		
		size++;
	}
	
	/**
	 * 尾部入队
	 * */
	
	public void enQueueRear(E element) {
		ensureCapcity(size + 1);
		
		elements[getIndex(size)] = element;
		
		size++;
	}
	
	/**
	 * 头部出队
	 * */
	public E deQueueFront() {
		
		E oldElement = elements[front];
		
		elements[front] = null;
		
		front = getIndex(1);
		
		size--;
		
		return oldElement;
	}
	
	/**
	 * 尾部出队
	 * */
	public E deQueueRear() {
		
		int rear = getIndex(size - 1);
		
		E oldElement = elements[rear];
		
		elements[rear] = null;

		size--;
		
		return oldElement;
	}
	
	private int getIndex(int index) {
		index += front;
		int length = elements.length;
		if(index < 0) {
			return index + length;
		}
		return index - (length > index ? 0 : length); 
	}
	
	private void ensureCapcity(int capcity) {
		int oldCapcity = elements.length;
		
		System.err.println(oldCapcity + "=====" + capcity);
		if(oldCapcity >= capcity) return;
		
		int newCapcity = oldCapcity + (oldCapcity >> 1);
		
		System.out.println("新增加的容量:" + newCapcity + "front" + front);
		E[] newElements = (E[])new Object[newCapcity];
		for(int i = 0; i < size; i++) {
			newElements[i] = elements[getIndex(i)];
		}
		
		
		
		elements = newElements;
		front = 0;
	}
	
	@Override
	public String toString() {
		StringBuilder string = new StringBuilder();
		string.append("capcacity=").append(elements.length)
		.append(" size=").append(size)
		.append(" front=").append(front)
		.append("\n[");
		for (int i = 0; i < elements.length; i++) {
//			if (i != 0) {
//				string.append(", ");
//			}
			
			
			string.append("\n").append(elements[i]);
		}
		string.append("]");
		return string.toString();
	}
}
