package algorithm.List;

import core.List.AbstractList;

public class ArrayList<E> extends AbstractList<E>{
	private static final int DEFAULT_CAPACITY = 10;
	private E[] elements;
	
	
	
	@SuppressWarnings("unchecked")
	public ArrayList(int capaticy) {
		capaticy = (capaticy < DEFAULT_CAPACITY) ? DEFAULT_CAPACITY : capaticy;
		elements = (E[]) new Object[capaticy];
	}
	
	public ArrayList() {
		this(DEFAULT_CAPACITY);
	}
	
	
	public boolean contain(int elements) {
		return false;
	}
	
	public void add(int index, E element) {
		rangeCheckForAdd(index);
		// 新增需要确保是否有足够大的容量
		ensureCapacity(size + 1);
	
		//从后往前
		for(int i = size; i > index; i--) {
			elements[i] = elements[i - 1];
		}
		
		elements[index] = element;
		size++;
	}
	
	public E get(int index) {
		rangeCheck(index);
		
		return elements[index];
	}
	public E set(int index, E element) {
		rangeCheck(index);
		
		E oldElement = elements[index];
		elements[index] = element;
		
		return oldElement;
	}
	
	public E remove(int index) {
		E oldElement = elements[index];
		
		for(int i = index + 1; i < size - 1; i++) {
			elements[i - 1] = elements[i];
		}
		elements[--size] = null;
		return oldElement;
	}
	
	public int indexOf(E element) {
		if(element == null) {
			for(int i = 0; i < size; i++) {
				if(elements[i] == null) return i;
			}
		} else {
			for(int i = 0; i < size; i++) {
				if(elements[i].equals(element)) return i;
			}
		}
		return ELEMENT_NOT_FOUND;
	}
	
	public void clear() {
		for(int i = 0; i < size; i++) {
			elements[i] = null;
		}
	}
	
	
	@SuppressWarnings("unchecked")
	private void ensureCapacity(int capacity) {
		int oldCapacity = elements.length;
		if(capacity <= oldCapacity) return;
		//扩容
		// 新容量为旧容量的1.5倍
		int newCapacity = oldCapacity + (oldCapacity >> 1);
		E[] newElements = (E[]) new Object[newCapacity];
		//将旧的内容 移到新的数组中
		for(int i = 0; i < size; i++) {
			newElements[i] = elements[i];
		}
		
		elements = newElements;
		
		System.out.println(oldCapacity + "扩容为" + newCapacity);
	}

	@Override
	public String toString() {
		StringBuilder string = new StringBuilder();
		string.append("size=").append(size).append(", [");
		for (int i = 0; i < size; i++) {
			if (i != 0) {
				string.append(", ");
			}
			
			string.append(elements[i]);
			
		}
		string.append("]");
		return string.toString();
	}
	
	
}
