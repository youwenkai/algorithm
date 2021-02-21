package core.Heap;

import java.util.Comparator;

import 复习.printer.BinaryTreeInfo;


/**
 * 最大堆
 * */
@SuppressWarnings("unchecked")
public class BinaryHeap<E> extends AbstractHeap<E> implements BinaryTreeInfo {

	private static final int DEFAULT_CAPACITY = 10;
	private E[] elements;
	
	
	public BinaryHeap(Comparator<E> comparator) {
		this(null, comparator);
	}
	
	public BinaryHeap() {
		this(null, null);
	}
	
	public BinaryHeap(E[] elements) {
		this(elements, null);
	}

	public BinaryHeap(E[] elements, Comparator<E> comparator) {
		super(comparator);
		
		if(elements == null || elements.length == 0) {
			this.elements = (E[])new Object[DEFAULT_CAPACITY];
		} else {
			size = elements.length;
			int capcity = Math.max(elements.length, DEFAULT_CAPACITY);
			this.elements = (E[])new Object[capcity];
			for(int i = 0; i < elements.length; i++) {
				this.elements[i] = elements[i];
			}
			
			heapify();
		}
	}

	@Override
	public void clear() {
		for(int i = 0; i < size; i++) {
			elements[i] = null;
		}
		size = 0;
	}

	@Override
	public void add(E element) {
		//最大堆添加 再数组尾部添加新元素 然后通过上滤 找到正确位置
		elementNotNullCheck(element);
		
		ensureCapcity(size + 1);
		
		elements[size++] = element;
		
		//上滤
		siftUp(size - 1);
	}

	@Override
	public E get() {
		emptyCheck();
		return elements[0];
	}

	@Override
	public E remove() {
		E element = elements[0];
		
		int lastIndex = --size;
		//将尾节点替换根节点 
		elements[0] = elements[lastIndex];
		elements[lastIndex] = null;
		
		siftDown(0);
		return element;
	}

	@Override
	public E replace(E element) {
		elementNotNullCheck(element);
		
		E root = null;
		if(size == 0) {
			elements[0] = element;
			size++;
		} else {
			root = elements[0];
			elements[0] = element;
			siftDown(0);
		}
		return root;
	}
	
	private void heapify() {
		// 自下而上的下滤
		for(int i = (size >> 1) - 1; i >= 0; i++) {
			siftDown(i);
		}
	}
	
	/**
	 * 上滤
	 * */
	private void siftUp(int index) {
		E element = elements[index];
		while (index > 0) {
			int parentIndex = (index - 1) >> 1;
			E parent = elements[parentIndex];
			// 父节点大于当前节点
			if(compare(parent, element) >= 0) break;
			
			//将父节点安排到index位置上
			elements[index] = parent;
			index = parentIndex;
		}
		
		elements[index] = element;
	}
	
	/**
	 * 下滤
	 * */
	private void siftDown(int index) {
		E element = elements[index];
		
		//index 必是非叶子节点
		int half = size >> 1;
		while (index < half) {
			// 左子节点 默认
 			int childIndex = (index << 1) + 1;
			E child = elements[childIndex];
			
			//右子节点
			int rightChildIndex = childIndex + 1;
			
			//如果右子节点比左子节点大
			if(rightChildIndex < size && 
					compare(elements[rightChildIndex], child) > 0) {
				child = elements[childIndex = rightChildIndex];
			}
			
			if(compare(element, child) >= 0) return;
			
			//将子节点存放到index的位置
			elements[index] = child;
			
			//重新设置index
			index = childIndex;
		}
		
		elements[index] = element;
	}

	private void ensureCapcity(int capcity) {
		int oldCapcity = elements.length;
		if(oldCapcity >= capcity) return;
		
		//新容量为就容量的1.5倍
		int newCapcity = oldCapcity + (oldCapcity >> 1);
		E[] newElements = (E[])new Object[newCapcity];
		for(int i = 0; i < size; i++) {
			newElements[i] = elements[i];
		}
		elements = newElements;
	}

	private void emptyCheck() {
		if(size == 0) {
			throw new IndexOutOfBoundsException("Heap is empty");
		}
	}
	
	private void elementNotNullCheck(E element) {
		if(element == null) {
			throw new IllegalArgumentException("element must not be null");
		}
	}
	

	@Override
	public Object root() {
		return 0;
	}

	@Override
	public Object left(Object node) {
		int index = ((int)node << 1) + 1;
		return index >= size ? null : index;
	}

	@Override
	public Object right(Object node) {
		int index = ((int)node << 1) + 2;
		return index >= size ? null : index;
	}

	@Override
	public Object string(Object node) {
		return elements[(int)node];
	}
}
