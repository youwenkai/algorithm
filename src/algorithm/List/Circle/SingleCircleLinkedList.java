package algorithm.List.Circle;

import core.List.AbstractList;

// 单向循环链表
public class SingleCircleLinkedList<E> extends AbstractList<E> {

	private Node<E> first;
	
	
	
	
	public SingleCircleLinkedList() {
		first = null;
	}

	@Override
	public void add(int index, E element) {
		rangeCheckForAdd(index);	
		
		if(index == 0) {
			// 如果只有一个元素的时候 first指向了原头节点
			Node<E> newFirstNode = new Node<E>(element, first);
			// 索引为0的节点的前驱节点时尾节点
			Node<E> lastNode = size == 0 ? newFirstNode : getNodeByIndex(size - 1);
			lastNode.next = newFirstNode;
			first = newFirstNode;
		} else {
			Node<E> prevNode = getNodeByIndex(index - 1);
			prevNode.next = new Node<E>(element, prevNode.next);
		}
		
		size++;
	}

	@Override
	public E remove(int index) {
		rangeCheck(index);
		
		Node<E> oldNode = first;
		if(index == 0) {
			if(size == 1) {
				first = null;
			} else {
				Node<E> prevNode = getNodeByIndex(size - 1);
				first = first.next;
				prevNode.next = first;
			}
		} else {
			Node<E> prevNode = getNodeByIndex(index - 1);
			oldNode = prevNode.next;
			prevNode.next = oldNode.next;
		}
		
		
		
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
		E oldE = oldNode.element;
		
		oldNode.element = element;
		
		return oldE;
	}

	@Override
	public int indexOf(E element) {
		
		Node<E> node = first;
		if(element == null) {
			for(int i = 0; i < size; i++) {
				if(node.element == null) return i;
				node = node.next;
			}
		} else {
			for(int i = 0; i < size; i++) {
				if(element.equals(node.element)) return i;
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
			
			sb.append(element).append("_");

			if (next != null) {
				sb.append(next.element);
			} else {
				sb.append("null");
			}
			
			return sb.toString();
		}
	}

}
