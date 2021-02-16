package core.Tree;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

import javax.xml.soap.Node;

@SuppressWarnings("unused")
public class BinaryTree<E> {
	private Node<E> root;
	private int size;
	
	
	
	/**
	 * 前序遍历 访问顺序 根节点 左子树 右子树
	 * 递归方式
	 * */
	public void preorderTraversalByRecursive() {
		preorderTraversal(root);
	}
	
	private void preorderTraversal(Node<E> node) {
		if(node == null) return;
		// 先访问根节点
		System.out.println("节点:" + node.element);
		// 再访问左子树
		preorderTraversal(node.left);
		preorderTraversal(node.right);
	}
	
	/**
	 * 前序遍历 非递归方式
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
	public void inorderTraversalByRecursive() {
		inorderTraversal(root);
	}
	private void inorderTraversal(Node<E> node) {
		if(node == null) return;
		inorderTraversal(node.left);
		System.out.println("节点:" + node.element);
		inorderTraversal(node.right);
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
	public void postorderTraversalByRecursive() {
		postorderTraversal(root);
	}
	
	private void postorderTraversal(Node<E> node) {
		if(node == null) return;
		postorderTraversal(node.left);
		postorderTraversal(node.right);
		System.out.println("节点:" + node.element);
	}
	
	/**
	 * 中序遍历 非递归方式
	 * */
	public void postorderTraversal() {
		Node<E> node = root;
		
		Stack<Node<E>> stack = new Stack<>();
		while (true) {
			if(node == null) {
				if(stack.isEmpty()) return;
				node = stack.peek();
				System.out.println("节点:" + node.element);
				node = node.right;
				
			} else {
				stack.push(node);
				node = node.left;
			}
		}
	}
	
	/**
	 * 层序遍历 访问顺序 从上到下 从左到右
	 * */
	public void levelorderTraversal() {
		levelorderTraversal(root);
	}
	private void levelorderTraversal(Node<E> node) {
		// 利用队列的性质 先进先出
		// 先将根节点入队
		Queue<Node<E>> queue = new LinkedList<>();
		queue.offer(root);
		while (!queue.isEmpty()) {
			//将队列头出队 将左右子树入队
			Node<E> frontNode = queue.poll();
			System.out.println(frontNode.element);
			if(frontNode.left != null) {
				queue.offer(frontNode.left);
			}
			if(frontNode.right != null) {
				queue.offer(frontNode.right);
			}
		}
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
	}
}
