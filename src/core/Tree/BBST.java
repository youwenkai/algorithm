package core.Tree;

import java.util.Comparator;

import core.Tree.BinaryTree.Node;

public class BBST<E> extends BinarySearchTree<E> {

	public BBST() {
		this(null);
	}

	public BBST(Comparator<E> comparator) {
		super(comparator);
	}
	
	/**
	 * 左旋转
	 * */
	protected void rotateLeft(Node<E> grand) {
		Node<E> parent = grand.right;
		Node<E> T1 = parent.left;
		
		parent.right = grand;
		grand.left = T1;
		
		afterRotate(grand, parent, T1);
	}
	/**
	 * 右旋转
	 * */
	protected void rotateRight(Node<E> grand) {
		Node<E> parent = grand.left;
		Node<E> T1 = parent.right;
		
		grand.left = T1;
		parent.right = parent;
		
		afterRotate(grand, parent, T1);
	}
	/**
	 * 旋转后的一些操作
	 * */
	protected void afterRotate(Node<E> grand, Node<E> parent, Node<E> T1) {
		// 让parent成为根节点
		parent.parent = grand.parent;
		if(grand.isLeftChild()) {
			grand.parent.left = parent;
		} else if(grand.isRightChild()) {
			grand.parent.right = parent;
		} else {// 说明grand是root
			root = parent;
		}
		
		
		// 修改T1的父节点
		if(T1 != null) {
			T1.parent = grand;			
		}
		
		// 修改grand的父节点
		grand.parent = parent;
		
	}
	
}
