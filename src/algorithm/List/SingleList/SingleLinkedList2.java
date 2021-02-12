package algorithm.List.SingleList;

import core.List.AbstractList;

//带有虚拟头节点的单链表
public class SingleLinkedList2<E> extends AbstractList<E> {
	
	private Node<E> first;

	public SingleLinkedList2() {
		first = new Node<E>(null, null);
	} 

	@Override
	public void add(int index, E element) {
		rangeCheckForAdd(index);
		
		Node<E> prevNode = index == 0 ? first : getNodeByIndex(index - 1);
		prevNode.next = new Node<E>(element, prevNode.next);
		size++;
	}



	@Override
	public E remove(int index) {
		rangeCheck(index);
		
		Node<E> prevNode = index == 0 ? first : getNodeByIndex(index - 1);
		Node<E> oldNode = prevNode.next;
		prevNode.next = oldNode.next;
		
		size--;
		
		return oldNode.element;
	}


	@Override
	public E get(int index) {
		return getNodeByIndex(index).element;
	}


	@Override
	public E set(int index, E element) {
		Node<E> oldNode = getNodeByIndex(index);
		E oldElement = oldNode.element;
		
		oldNode.element = element;
		
		return oldElement;
	}

	@Override
	public int indexOf(E element) {
		Node<E> node = first.next;
		
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
		first.next = null;
	}
	
	@Override
	public String toString() {
		StringBuilder string = new StringBuilder();
		string.append("size=").append(size).append(", [");
		Node<E> node = first.next;
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
		Node<E> node = first.next;
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
