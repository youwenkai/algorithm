package core.Tree;

import java.util.Comparator;
import java.util.function.LongToDoubleFunction;


/**
 * AVL树是一种自平衡的二叉搜索树
 * 引进平衡因子的概念 保证每个节点都是平衡（节点的左右子树的高度差不超过1）
 * 让二叉搜索树的高度变矮
 * 
 * 维护AVL树
 * 在添加新节点的时候 让不平衡的节点修复
 * */ 
public class AVLTree<E> extends BinarySearchTree<E> {

	public AVLTree() {
		this(null);
	}

	public AVLTree(Comparator<E> comparator) {
		super(comparator);
	}
	
	@Override
	protected void afterAdd(Node<E> node) {
		// 由于新加节点 导致树的失衡
		// 但是其父节点和非祖先节点都不会失衡
		// 只有其节点的祖先节点会导致失衡
		// 最坏的情况: 其祖先节点都失衡
		// 所以要遍历其parent的节点 修复
		while ((node = node.parent) != null) {
			// 判断node的是否平衡
			if(isBalanced(node)) {
				//更新高度
				updateHeight(node);
			} else {
				// 修复的第一个失衡的节点 使他的高度 保持和 失衡前的高度保持一致
				// 所以整棵树都已经平衡了
				rebalance(node);
				break;
			}
		}
	}
	
	@Override
	protected void afterRemove(Node<E> node) {
		// 删除一个节点可能会导致其祖先节点失衡
		// 必然导致高度改变
		// 所以要遍历其所有祖先节点 让其恢复
		while ((node = node.parent) != null) {
			if(isBalanced(node)) {
				updateHeight(node);
			} else {
				rebalance(node);
			}
		}
	}
	
	@Override
	protected Node<E> createNode(E element, Node<E> parent) {
		return new AVLNode<>(element, parent);
	}
	/**
	 * @param grand 高度最低的那个不平衡节点
	 * */
	private void rebalance(Node<E> grand) {
		//parent的节点 是左右子树的高度最高的节点
		AVLNode<E> parent = ((AVLNode<E>)grand).tallerChild();
		AVLNode<E> node = ((AVLNode<E>)parent).tallerChild();
		
		if(parent.isLeftChild()) { //L
			if(node.isLeftChild()) { //LL
				rotateRight(grand);
			} else { //LR
				// 先是父节点左旋转
				rotateLeft(parent);
				// 然后父节点右旋转
				rotateRight(grand);
			}
		} else {
			if(node.isLeftChild()) { //RL
				rotateRight(parent);
				rotateLeft(grand);
			} else { //RR
				rotateLeft(grand);
			}
			
		}
	}
	
	/**
	 * 左旋转
	 * */
	private void rotateLeft(Node<E> grand) {
		Node<E> parent = grand.right;
		Node<E> T1 = parent.left;
		
		parent.right = grand;
		grand.left = T1;
		
		afterRotate(grand, parent, T1);
	}
	/**
	 * 右旋转
	 * */
	private void rotateRight(Node<E> grand) {
		Node<E> parent = grand.left;
		Node<E> T1 = parent.right;
		
		grand.left = T1;
		parent.right = parent;
		
		afterRotate(grand, parent, T1);
	}
	/**
	 * 旋转后的一些操作
	 * */
	private void afterRotate(Node<E> grand, Node<E> parent, Node<E> T1) {
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
		
		//更新高度
		updateHeight(grand);
		updateHeight(parent);
	}
	
	/**
	 * 更新节点高度
	 * */
	private void updateHeight(Node<E> node) {
		((AVLNode<E>)node).updateHeight();
	}
	
	/**
	 * 判断节点是否平衡
	 * */
	private boolean isBalanced(Node<E> node) {
		// 平衡因子为-1 0 1;
		return Math.abs(((AVLNode<E>)node).balanceFactor()) <= 1;
	}
	
	private static class AVLNode<E> extends Node<E> {
		// 新节点的高度 默认为1
		int height = 1;
		public AVLNode(E element, Node<E> parent) {
			super(element, parent);
		}
		
		public int balanceFactor() {
			// 左右子树的高度差
			return leftHeight() - rightHeight();
		}
		
		// 更新节点的高度
		public void updateHeight() {
			// 左右子树高度最大值
			height = 1 + Math.max(leftHeight(), rightHeight());
		}
		
		// 获取左右子树中的高度最高的节点
		public AVLNode<E> tallerChild() {
			int leftH = leftHeight();
			int rightH = rightHeight();
			if(leftH > rightH) return (AVLNode<E>)left;
			if(leftH < rightH) return (AVLNode<E>)right;
			return isLeftChild() ? (AVLNode<E>)left : (AVLNode<E>)right;
		}
		
		//左子树的高度
		private int leftHeight() {
			return left == null ? 0 : ((AVLNode<E>)left).height;
		}
		// 右子树高度
		private int rightHeight() {
			return right == null ? 0 : ((AVLNode<E>)right).height;
		}
		
	}
	
}
