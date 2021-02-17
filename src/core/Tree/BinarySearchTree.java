package core.Tree;

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
		
		// 如果根节点是null
		if(root == null) {
			root = new Node<E>(element, null);
			size++;
			return;
		}
		
		// 添加的不是第一个节点
		// 需要找到父节点
		Node<E> parent = root;
		Node<E> node = root;
		int cmp = 0;
		while (node != null) {
			cmp = compare(element, node.element);
			parent = node;
			if(cmp > 0) {
				node = node.right;
			} else if(cmp < 0) {
				node = node.left;
			} else {
				node.element = element;
				return;
			}
		}
		
		// 添加节点
		Node<E> newNode = new Node<E>(element, parent);
		if(cmp > 0) {
			parent.right = newNode;
		} else if(cmp < 0) {
			parent.left = newNode;
		}
		size++;
	}
	
	public void remove(E element) {
		remove(getNode(element));
	}
	
	private void remove(Node<E> node) {
		if(node == null) return;
		
		size--;
		// 度为2的节点 则用前驱或者后继节点 替换 然后再删除前驱或者后继
		if(node.hasTwoChildren()) {
			//找到后继节点
			Node<E> sNode = successor(node);
			//用后继节点内容覆盖 node的值
			node.element = sNode.element;
			// 将要删除的节点赋值给node
			node = sNode;
		}
		
		//删除node节点(node的度必然是0 或者 1)
		Node<E> replacement = node.left != null ? node.left : node.right;
		
		if(replacement != null) { // node是度为1的节点
			//删除度为1的节点 只需用node的子节点替换自己
			
			// 更改replacement的parent的指向
			replacement.parent = node.parent;
			
			// 更改node.parent的left和right的指向
			if(node.parent == null) {
				root = replacement;
			}else if(node.parent.left == node) {
				node.parent.left = replacement;				
			} else {
				node.parent.right = replacement;
			}
		} else if(node.parent == null) {// node的是根节点
			root = null;
		} else { // node 为叶子节点 直接删除
			if(node == node.parent.left) {
				node.parent.left = null;
			} else {
				node.parent.right = null;
			}
		}
		
	}
	
	
	public boolean containes(E element) {
		return getNode(element) != null;
	} 
	
	private Node<E> getNode(E element){
		Node<E> node = root;
		while (node != null) {
			int cmp = compare(element, node.element);
			if(cmp > 0) {
				node = node.right;
			} else if(cmp < 0) {
				node = node.left;
			} else {
				return node;
			}
		}
		
		return null;
	}

	private int compare(E e1, E e2) {
		if(comparator != null) {
			return comparator.compare(e1, e2);
		} else {
			return ((Comparable<E>)e1).compareTo(e2);
		}
	}
	
	private void elementNotNullCheck(E element) {
		if(element == null) {
			throw new IllegalArgumentException("element must not be null");
		}
	}
}
