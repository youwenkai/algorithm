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
			root = createNode(element, null);
			size++;
			// 新添加节点后的操作
			afterAdd(root);
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
		Node<E> newNode = createNode(element, parent);
		if(cmp > 0) {
			parent.right = newNode;
		} else if(cmp < 0) {
			parent.left = newNode;
		}
		size++;
		// 新添加节点后的操作
		afterAdd(newNode);
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
			
			// 删除节点之后的处理
			afterRemove(replacement);
		} else if(node.parent == null) {// node的是根节点
			root = null;
			// 删除节点之后的处理
			afterRemove(node);
		} else { // node 为叶子节点 直接删除
			if(node == node.parent.left) {
				node.parent.left = null;
			} else {
				node.parent.right = null;
			}
			// 删除节点之后的处理
			afterRemove(node);
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
	/**
	 * 添加node之后的调整
	 * @param node 新添加的节点
	 */
	protected void afterAdd(Node<E> node) {}

	/**
	 * 删除node之后的调整
	 * @param node 被删除的节点 或者 用以取代被删除节点的子节点（当被删除节点的度为1）
	 */
	protected void afterRemove(Node<E> node) {}
	
	private void elementNotNullCheck(E element) {
		if(element == null) {
			throw new IllegalArgumentException("element must not be null");
		}
	}
}
