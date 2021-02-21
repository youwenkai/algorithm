package 复习.Tree;

import java.util.Comparator;

/**
 * 
 * AVL树 引进平衡因子 将树变成一颗平衡树 高度会变低
 * */
public class AVLTree<E> extends BBST<E> {

	
	
	
	public AVLTree() {
		this(null);
	}

	public AVLTree(Comparator<E> comparator) {
		super(comparator);
	}

	@Override
	protected Node<E> createNode(E element, Node<E> parent) {
		return new AVLNode<>(element, parent);
	}

	@Override
	protected void afterAdd(Node<E> node) {
		while ((node = node.parent) != null) {
			// 如果node节点是平衡的 则更新高度
			if(isBalanced(node)) {
				//更新高度
				updateHeight(node);
			} else {// 如果不平衡 则修复
				rebalance(node); // 由于修复后 节点的高度没有改不 所以整棵树就恢复平衡了
				break;
			}
		}
	}

	@Override
	protected void afterRemove(Node<E> node) {
		while ((node = node.parent) != null) {
			if(isBalanced(node)) {
				updateHeight(node);
			}else {
				rebalance(node);
			}
		}
	}
	
	/**
	 * 修复平衡
	 * */
	private void rebalance(Node<E> grand) {
		// parent是grand的高度最高的子节点
		Node<E> parent = ((AVLNode<E>)grand).tallerChild();
		Node<E> child = ((AVLNode<E>)parent).tallerChild();
		
		if(parent.isLeftChild()) { // L
			if(child.isLeftChild()) { // LL
				rotateRight(grand);
			} else { // LR
				rotateLeft(parent);
				rotateRight(grand);
			}
		} else {// R
			if(child.isLeftChild()) {// RL
				rotateRight(parent);
				rotateLeft(grand);
			} else {// RR
				rotateLeft(grand);
			}
		}
		
	}

	@Override
	protected void afterRotate(Node<E> grand, Node<E> parent, Node<E> child) {
		super.afterRotate(grand, parent, child);
		//更新高度
		updateHeight(parent);
		updateHeight(grand);
	}
	
	private boolean isBalanced(Node<E> node) {
		return Math.abs(((AVLNode<E>)node).balanceFactor()) <= 1;
	}
	
	private void updateHeight(Node<E> node) {
		((AVLNode<E>)node).updateHeigth();
	}
	
	private static class AVLNode<E> extends Node<E>{
		
		int height = 1;

		public AVLNode(E element, Node<E> parent) {
			super(element, parent);
		}
		
		private int leftHeight() {
			return left == null ? 0 : ((AVLNode<E>)left).height;
		}
		
		private int rightHeight() {
			return right == null ? 0 : ((AVLNode<E>)right).height;
		}
		
		// 左右子树的高度差
		public int balanceFactor() {
			return leftHeight() -  rightHeight();
		}
		
		//更新节点的高度(左右子树高度的最大值)
		public void updateHeigth() {
			height =  1 + Math.max(leftHeight(), rightHeight());
		}
		
		public Node<E> tallerChild() {
			int cmp = leftHeight() - rightHeight();
			if(cmp > 0) return left;
			if(cmp < 0) return right;
			return isLeftChild() ? left : right;
		}
		
		@Override
		public String toString() {
			String parentString = "null";
			if (parent != null) {
				parentString = parent.element.toString();
			}
			return element + "_p(" + parentString + ")_h(" + height + ")";
		}
		
	}

}
