package 复习.Tree;

import java.util.Comparator;

import 复习.Tree.BinaryTree.Node;

public class BBST<E> extends BinarySearchTree<E> {

	public BBST() {
		this(null);
	}

	public BBST(Comparator<E> comparator) {
		super(comparator);
	}
	
	/**左旋*/
	protected void rotateLeft(Node<E> grand) {
		Node<E> parent = grand.right;
		Node<E> child = parent.left;
		
		//将parent成为根节点
		parent.left = grand;
		grand.right = child;
		
		afterRotate(grand, parent, child);
	}
	
	/**右旋*/
	protected void rotateRight(Node<E> grand) {
		Node<E> parent = grand.left;
		Node<E> child = parent.right;
		
		grand.left = child;
		parent.right = grand;
		
		afterRotate(grand, parent, child);
	}
	
	protected void afterRotate(Node<E> grand, Node<E> parent, Node<E> child) {
		// 让parent称为子树的根节点
		parent.parent = grand.parent;

		if(grand.isLeftChild()) {
			grand.parent.left = parent;
		} else if(grand.isRightChild()) {
			grand.parent.right = parent;
		} else {// grand是root节点
			root = parent;
		}
		// 更新child的parent
		if(child != null) {
			child.parent = grand;
		}
		
		//更新grand的父节点
		grand.parent = parent;
	}

}
