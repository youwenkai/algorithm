package algorithm.List;

import core.List.AbstractList;

/**
 * 单链表
 * */

public class SingleLinkedList<E> extends AbstractList<E> {
	
	private Node<E> first;


	@Override
	public void add(int index, E element) {
		rangeCheckForAdd(index);
		
		//最好o(1) 最坏o(n) 平均o(n)
		if(index == 0) {
			first = new Node<E>(element, first);
		} else {
			Node<E> prevNode = getNodeByIndex(index - 1);
			Node<E> newNode = new Node<E>(element, prevNode.next);
			prevNode.next = newNode;
		}
		size++;
	}


	@Override
	public E remove(int index) {
		rangeCheck(index);
		
		//最好o(1) 最坏o(n) 平均o(n)

		Node<E> removeNode = first;
		if(index == 0) {
			first = first.next;
		} else {
			Node<E> prevNode = getNodeByIndex(index - 1);
			removeNode = prevNode.next;
			prevNode.next = removeNode.next;
		}
		
		size--;
		
		return removeNode.element;
	}


	@Override
	public E get(int index) {
		return getNodeByIndex(index).element;
	}


	@Override
	public E set(int index, E element) {
		// 最好是o(1) 最坏o(n) 平均o(n)
		Node<E> oldNode = getNodeByIndex(index);
		E oldElement = oldNode.element;
		
		oldNode.element = element;
		
		return oldElement;
	}

	@Override
	public int indexOf(E element) {
		Node<E> node = first;
		
		if (element == null) {
			for(int i = 0; i < size; i++) {
				if (node.element == null) {
					return i;
				}
				node = node.next;
			}
		} else {
			for(int i = 0; i < size; i++) {
				if (element.equals(node.element)) {
					return i;
				}
				node = node.next;
			}			
		}
		
		return ELEMENT_NOT_FOUND;
	}


	@Override
	public void clear() {
		size = 0;
		first = null;
	}
	
	@Override
	public String toString() {
		StringBuilder string = new StringBuilder();
		string.append("size=").append(size).append(", [");
		Node<E> node = first;
		for (int i = 0; i < size; i++) {
			if (i != 0) {
				string.append(", ");
			}
			
			string.append(node.element);
			
			node = node.next;
		}
		string.append("]");
		
//		Node<E> node1 = first;
//		while (node1 != null) {
//			
//			
//			node1 = node1.next;
//		}
		return string.toString();
	}
	
	private Node<E> getNodeByIndex(int index){
		rangeCheck(index);
		Node<E> node = first;
		for(int i = 0; i < index; i++) {
			node = node.next;
		}
		
		return node;
	}


	@SuppressWarnings("unused")
	private static class Node<E> {
		E element;
		Node<E> next;
		public Node(E element, Node<E> next) {
			this.element = element;
			this.next = next;
		}
		
		@Override
		public String toString() {
			StringBuilder sb = new StringBuilder();
			
			
			sb.append("_").append(element).append("_");

			if (next != null) {
				sb.append(next.element);
			} else {
				sb.append("null");
			}
			
			return sb.toString();
		}
	}
}
