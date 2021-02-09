package algorithm.List;

import core.List.AbstractList;

public class LinkedList<E> extends AbstractList<E> {
	
	private Node<E> first;
	private Node<E> last;


	@Override
	public void add(int index, E element) {
		rangeCheckForAdd(index);
		
		Node<E> curNode = getNodeByIndex(index);
		Node<E> prevNode = curNode.prev;
		Node<E> newNode = new Node<E>(prevNode, element, curNode);
		prevNode.next = newNode;
	}


	@Override
	public E remove(int index) {
		// TODO Auto-generated method stub
		return super.remove(index);
	}


	@Override
	public E get(int index) {
		// TODO Auto-generated method stub
		return super.get(index);
	}


	@Override
	public E set(int index, E element) {
		// TODO Auto-generated method stub
		return super.set(index, element);
	}


	@Override
	public boolean contains(E element) {
		// TODO Auto-generated method stub
		return super.contains(element);
	}


	@Override
	public int indexOf(E element) {
		// TODO Auto-generated method stub
		return super.indexOf(element);
	}


	@Override
	public void clear() {
		// TODO Auto-generated method stub
		super.clear();
	}
	
	private Node<E> getNodeByIndex(int index){
		rangeCheck(index);
		Node<E> node = first
		for(int i = 0; i < index; i++) {
			node = first.next;
		}
		
		return node;
	}


	private static class Node<E> {
		E element;
		Node<E> prev;
		Node<E> next;
		@SuppressWarnings("unused")
		public Node(Node<E> prev, E element, Node<E> next) {
			this.element = element;
			this.prev = prev;
			this.next = next;
		}
		
		@Override
		public String toString() {
			StringBuilder sb = new StringBuilder();
			
			if (prev != null) {
				sb.append(prev.element);
			} else {
				sb.append("null");
			}
			
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
