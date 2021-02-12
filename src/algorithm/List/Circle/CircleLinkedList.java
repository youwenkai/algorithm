package algorithm.List.Circle;

import core.List.AbstractList;


// 双向循环链表
public class CircleLinkedList<E> extends AbstractList<E> {
	
	private Node<E> first;
	private Node<E> last;
	private Node<E> current;

	
	
	public void reset() {
		current = first;
	}
	
	public E next() {
		if(current == null) return null;
		
		Node<E> curNode = current;
		current = current.next;
		
		return curNode.element;
	}
	
	@Override
	public void add(int index, E element) {
		rangeCheckForAdd(index);
		
		// 在尾部添加的时候
		if(index == size) {
			Node<E> oldNode = last;
			last = new Node<E>(oldNode, element, first);
			if(oldNode == null) { //last指向null 表示此链表没有节点
				first = last;
				//只有一个元素 则next prev 都指向自己
				first.next = first;
				first.prev = first;
			} else {
				oldNode.next = last;
				first.prev = last;
			}
		} else {
			Node<E> nextNode = getNodeByIndex(index);
			Node<E> prevNode = nextNode.prev;
			Node<E> newNode = new Node<E>(prevNode, element, nextNode);
			nextNode.prev = newNode;
			prevNode.next = newNode;
			
			if(nextNode == first) {
				first = newNode;	
			}
		}
		
		size++;
	}




	@Override
	public E remove(int index) {
		rangeCheck(index);
		
		Node<E> oldNode = first;
		// 如果只有一个元素
		if(size == 1) {
			first = last = null;
		} else {
			oldNode = getNodeByIndex(index);
			Node<E> prevNode = oldNode.prev;
			Node<E> nextNode = oldNode.next;

			nextNode.prev = prevNode;
			prevNode.next = nextNode;
			
			if(oldNode == first) {
				first = nextNode;
			}
			
			if(oldNode == last) {
				last = prevNode;
			}
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
		if(element == null) {
			Node<E> node = first;
			for(int i = 0; i < size; i++) {
				if(node.element == null) return i;
				node = node.next;
			}
		} else {
			Node<E> node = first;
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
		last = null;
	}
	
	
	private Node<E> getNodeByIndex(int index) {
		rangeCheck(index);
		Node<E> node;
		if(index > (size >> 1)) {
			node = last;
			for(int i = size - 1; i > index; i--) {
				node = node.prev;
			}
			
		} else {
			node = first;
			for(int i = 0; i < index; i++) {
				node = node.next;
			}
		}
		
		return node;
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
			
			string.append(node);
			
			node = node.next;
		}
		string.append("]");
		return string.toString();
	}








	@SuppressWarnings("unused")
 	private static class Node<E> {
		E element;
		Node<E> prev;
		Node<E> next;
		public Node(Node<E> prev,E element, Node<E> next) {
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
