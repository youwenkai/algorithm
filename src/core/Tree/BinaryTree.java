package core.Tree;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

import javax.xml.soap.Node;

@SuppressWarnings("unused")
public class BinaryTree<E> {
	private Node<E> root;
	private int size;
	
	public int size() {
		return size;
	}
	
	public boolean isEmpty() {
		return size == 0;
	}
	
	public void clear() {
		size = 0;
		root = null;
	}
	
	
	/**
	 * 前序遍历 访问顺序 根节点 左子树 右子树
	 * 递归方式
	 * */
	public void preorderTraversalByRecursive(Visitor<E> visitor) {
		if(visitor == null) return;
		preorderTraversal(root, visitor);
	}
	
	private void preorderTraversal(Node<E> node, Visitor<E> visitor) {
		if(node == null || visitor.stop) return;
		// 先访问根节点
//		System.out.println("节点:" + node.element);
		visitor.stop = visitor.visit(node.element);
		// 再访问左子树
		preorderTraversal(node.left, visitor);
		preorderTraversal(node.right, visitor);
	}
	
	/**
	 * 前序遍历 迭代方式1
	 * */
	public void preorderTraversal() {
		Node<E> node = root;
		Stack<Node<E>> stack = new Stack<>();
		while (true) {
			
			if(node == null) {
				if(stack.isEmpty()) return;
				// 栈不为空 则将右子树弹出
				node = stack.pop();
			} else {
				System.out.println("节点：" + node.element);
				//将node的右子树保存起来
				stack.push(node.right);
				
				node = node.left;				
			}
		}
	}
	
	/**
	 * 前序遍历 迭代方式2
	 * */

	public void preorderTraversal1() {
		Stack<Node<E>> stack = new Stack<>();
		stack.push(root);
		
		while (!stack.isEmpty()) {
			Node<E> node = stack.pop();
			System.out.println("节点:" + node.element);
			
			if(node.right != null) {
				stack.push(node.right);
			}
			
			if(node.left != null) {
				stack.push(node.left);
			}
		}
	}
	
	
	/**
	 * 中序遍历 访问顺序 左子树 根节点 右子树
	 * 递归方式
	 * */
	public void inorderTraversalByRecursive(Visitor<E> visitor) {
		if(visitor == null) return;
		inorderTraversal(root, visitor);
	}
	private void inorderTraversal(Node<E> node, Visitor<E> visitor) {
		if(node == null || visitor.stop) return;
		inorderTraversal(node.left, visitor);
		if(visitor.stop) return;
//		System.out.println("节点:" + node.element);
		visitor.stop = visitor.visit(node.element);
		inorderTraversal(node.right, visitor);
	}
	
	/**
	 * 中序遍历 非递归方式
	 * */
	public void inorderTraversal() {
		Node<E> node = root;
		Stack<Node<E>> stack = new Stack<>();
		while (true) {
			if(node != null) {
				stack.push(node);
				node = node.left;				
			} else {
				if(stack.isEmpty()) return;
				node = stack.pop();
				System.out.println("节点:" + node.element);
				node = node.right;
			}
		}
	}
	
	/**
	 * 后序遍历 访问顺序 左子树 右子树 根节点
	 * 递归方式
	 * */
	public void postorderTraversalByRecursive(Visitor<E> visitor) {
		if(visitor == null) return;
		postorderTraversal(root, visitor);
	}
	
	private void postorderTraversal(Node<E> node, Visitor<E> visitor) {
		if(node == null || visitor.stop) return;
		postorderTraversal(node.left, visitor);
		postorderTraversal(node.right, visitor);
		if(visitor.stop) return;
//		System.out.println("节点:" + node.element);
		visitor.stop = visitor.visit(node.element);
	}
	
	/**
	 * 后序遍历 迭代方式
	 * */
	public void postorderTraversal(Visitor<E> visitor) {
		if(root == null || visitor == null) return;
		Stack<Node<E>> stack = new Stack<>();
		
		stack.push(root);
		Node<E> visitNode = null;
		while (!stack.isEmpty()) {
			
			if(visitor.stop) return;
			
			Node<E> node = stack.peek();
			// 如果栈顶节点是叶子节点 或者上一次访问节点是栈顶节点的子节点
			// 则弹出
			// 否则将right left顺序入栈
			if((node.left == null && node.right == null) || (visitNode != null && ( node.left == visitNode || node.right == visitNode ))) {
				visitNode = stack.pop();
				visitor.stop = visitor.visit(visitNode.element);
			} else {
				if(node.right != null) stack.push(node.right);
				if(node.left != null) stack.push(node.left);
			}
		}
	}
	
	/**
	 * 层序遍历 访问顺序 从上到下 从左到右
	 * */
	public void levelorderTraversal(Visitor<E> visitor) {
		if(root == null || visitor == null) return; 
		// 利用队列的性质 先进先出
		// 先将根节点入队
		Queue<Node<E>> queue = new LinkedList<>();
		queue.offer(root);
		while (!queue.isEmpty()) {
			//将队列头出队 将左右子树入队
			Node<E> frontNode = queue.poll();
			
			if(visitor.visit(frontNode.element)) return;
			
//			System.out.println(frontNode.element);
			if(frontNode.left != null) {
				queue.offer(frontNode.left);
			}
			if(frontNode.right != null) {
				queue.offer(frontNode.right);
			}
		}
	}
	
	/**
	 * 判断一棵树是否是完全二叉树
	 * */
	public boolean isComplete() {
		if(root == null) return false;
		
		Queue<Node<E>> queue = new LinkedList<>();
		queue.offer(root);
		//利用层次遍历
		
		boolean leaf = false;
		while(!queue.isEmpty()) {
			Node<E> node = queue.poll();
			if(leaf && !node.isLeaf()) return false;
			
			if(node.left != null) {
				queue.offer(node.left);
			} else if(node.right != null) {// 左子树为空 而右子树不为空 肯定不是完全二叉树
				return false;
			}
			
			if(node.right != null) {
				queue.offer(node.right);
			} else {
				// 如果右子树为空 则后面的节点应该都是叶子节点才能保证是完全二叉树
				leaf = true;
			}
			
		}
		
		return true;
	}
	
	/**
	 * 求二叉树的高度 利用层序遍历 
	 * 迭代方法
	 * */
	public int height() {
		if(root == null) return 0;
		// 树的高度
		int height = 0;
		// 每一层 节点数量 默认是第一层 所以默认是1
		int levelSize = 1;
		
		Queue<Node<E>> queue = new LinkedList<>();
		queue.offer(root);
		
		while (!queue.isEmpty()) {
			Node<E> node = queue.poll();
			levelSize--;
			
			if(node.left != null) queue.offer(node.left);
			if(node.right != null) queue.offer(node.right);
			
			if(levelSize == 0) {
				height++;
				levelSize = queue.size();
			}
		}
		return height;
	}
	/**
	 * 求二叉树的高度 递归方法
	 * */
	public int heightByRecursive() {
		return height(root);
	}
	
	// 树的高度等于 根节点的 + 左右子树高度的最大值
	private int height(Node<E> node) {
		if(node == null) return 0;
		return 1 + Math.max(height(node.left), height(node.right));
	}
	
	/**
	 * 前驱节点 中序遍历中 小于此值的最大值
	 * */
	public Node<E> predecessor(Node<E> node) {
		if(node == null) return null;
		
		// 前驱节点在左子树上(node.left.right.right...)
		Node<E> leftNode = node.left;
		if(leftNode != null) {
			while (leftNode.right != null) {
				//一直向右找寻最大值
				leftNode = leftNode.right;
			}
			return leftNode;
		}
		// 如果左子树为空 则从父节点、祖父节点中寻找前驱节点
		while (node.parent != null && node == node.parent.left) {
			node = node.parent;
		}
		
		return node.parent;
	}
	
	/**
	 * 后继节点 中序遍历中 大于此值的最小值
	 * */
	public Node<E> successor(Node<E> node) {
		if(node == null) return null;
		
		// 后继节点在右子树上(node.right.left.left......)
		Node<E> rightNode = node.right;
		
		if(rightNode != null) {
			while (rightNode.left != null) {
				rightNode = rightNode.left;
			}
			return rightNode;
		}
		
		//如果右子树为空 则去父节点 祖先节点上去找
		while (node.parent != null && node == node.parent.right) {
			node = node.parent;
		}
		
		return node.parent;
	}
	
	private static class Node<E> {
		E element;
		Node<E> left;
		Node<E> right;
		Node<E> parent;
		public Node(E element, Node<E> parent) {
			this.element = element;
			this.parent = parent;
		}
		
		/**
		 * 是否是叶子节点
		 * */
		public boolean isLeaf() {
			return left == null && right == null;
		}
	}
	
	public static abstract class Visitor<E> {
		boolean stop;
		
		abstract boolean visit(E element);
	}
}
