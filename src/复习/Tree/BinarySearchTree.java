package 复习.Tree;

import java.util.Comparator;


@SuppressWarnings("unchecked")
public class BinarySearchTree<E> extends BinaryTree<E> {
	private Comparator<E> comparator;

	public BinarySearchTree() {
		this(null);
	}
	
	public BinarySearchTree(Comparator<E> comparator) {
		this.comparator = comparator;
	}
	
	public void add(E element) {
		elementNotNullCheck(element);
		
		if(root == null) { //添加的是第一个元素
			root = createNode(element, null);
			//添加后操作
			afterAdd(root);
			size++;
			return;
		}
		
		//寻找新增节点的父节点
		Node<E> parent = root;
		Node<E> node = root;
		int cmp = 0;
		do {
			cmp = compare(node.element, element);
			parent = node;
			if(cmp > 0) {
				node = node.left;
			} else if(cmp < 0) {
				node = node.right;
			} else { //相等 就覆盖内容
				node.element = element;
				return;
			}

		} while (node != null);
		
		Node<E> newNode = createNode(element, parent);
		if(cmp > 0) {
			parent.left = newNode;
		} else {
			parent.right = newNode;
		}
		afterAdd(newNode);
		size++;
	}
	
	
	public void remove(E element) {
		remove(getNode(element));
	}
	
	private void remove(Node<E> node) {
		if(node == null) return;
	
		size--;
	
		//如果节点的度为2
		if(node.hasTwoChildren()) {
			//找到后继节点
			Node<E> sNode = successor(node);
			node.element = sNode.element;
			
			//将后继节点删除
			node = sNode;
		}
		// 到这里 就是度为1 或者是 叶子节点
		Node<E> replacement = node.left != null ? node.left : node.right;
		if(replacement != null) { // 说明度为1
			replacement.parent = node.parent;
			if(node.parent == null) {// 删除的是度为1的根节点
				root = replacement;
			} else if(node.isLeftChild()) {
				node.parent.left = replacement;
			} else {
				node.parent.right = replacement;
			}
			
			afterRemove(replacement);
		} else if(node.parent == null){ //删除的是根节点
			root = null;
			afterRemove(node);
		} else {// 删除的是叶子节点
			if(node.isLeftChild()) {
				node.parent.left = null;
			} else {
				node.parent.right = null;
			}
			
			afterRemove(node);
		}
	}
	
	
	
	protected Node<E> createNode(E element, Node<E> parent) {
		return new Node<>(element, parent);
	}
	
	/**
	 * 新增节点后的操作
	 * @param node 新增节点
	 * */
	protected void afterAdd(Node<E> node) {}
	
	/**
	 * 删除节点后的操作
	 * @param node 删除节点
	 * */
	protected void afterRemove(Node<E> node) {}
	
	private Node<E> getNode(E element){
		Node<E> node = root;
		while (node != null) {
			int cmp = compare(node.element, element);
			if(cmp > 0) {
				node = node.left;
			} else if(cmp < 0) {
				node = node.right;
			} else {
				return node;
			}
		}
		
		return null;
	}
	
	private int compare(E e1, E e2) {
		if(comparator != null) {
			return comparator.compare(e1, e2);
		}
		return ((Comparable<E>)e1).compareTo(e2);
	}
	
	private void elementNotNullCheck(E element) {
		if (element == null) {
			throw new IllegalArgumentException("element must not be null");
		}
	}
	
}
