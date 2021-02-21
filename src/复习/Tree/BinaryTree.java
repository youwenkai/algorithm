package 复习.Tree;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

import 复习.printer.BinaryTreeInfo;

@SuppressWarnings("unchecked")
public class BinaryTree<E> implements BinaryTreeInfo{
	int size;
	Node<E> root;
	
	//========================二叉树的遍历方法 前序 中序 后序 层次=================================//
	
	/**
	 * 前序遍历 (根 左 右) 递归方式
	 * */
	public void preorder(Visitor<E> visitor) {
		if(visitor == null) return;
		preorder(root, visitor);
	}
	
	private void preorder(Node<E> node, Visitor<E> visitor) {
		if(node == null || visitor.stop) return;
		
		visitor.stop = visitor.visit(node.element);
		
		preorder(node.left, visitor);
		
		preorder(node.right, visitor);
	}
	
	/**
	 * 前序遍历 (根 左 右) 迭代方式
	 * */
	public void preorder1(Visitor<E> visitor) {
		if(root == null || visitor == null) return;
		
		Stack<Node<E>> stack = new Stack<>();
		Node<E> node = root;
		
		while (true) {
			if(node == null) {
				//到这里表明是已经到达了最左子树
				if(stack.isEmpty()) return;
				
				//将右子树弹出 继续遍历
				node = stack.pop();
				
			} else {
				if(visitor.stop) return;
				visitor.stop = visitor.visit(node.element);
				
				//保存右子树
				if(node.right != null) stack.push(node.right);
				
				node = node.left;
			}
		}
	}
	
	/**
	 * 前序遍历 (根 左 右) 迭代方式
	 * */
	public void preorder2(Visitor<E> visitor) {
		if(root == null || visitor == null) return;
		
		Stack<Node<E>> stack = new Stack<>();
		stack.push(root);
		
		while (!stack.isEmpty()) {
			//每次弹出栈顶元素
			Node<E> node = stack.pop();
			
			if(visitor.stop) return;
			
			visitor.stop = visitor.visit(node.element);
			
			//先将右子树进栈
			if(node.right != null) stack.push(node.right);
			
			//再将左子树进栈
			if(node.left != null) stack.push(node.left);
		}
	}
	
	/**
	 * 中序遍历(左 根 右) 递归方式
	 * */
	public void inorder(Visitor<E> visitor) {
		if(visitor == null) return;
		inorder(root, visitor);
	}
	private void inorder(Node<E> node, Visitor<E> visitor) {
		if(node == null || visitor.stop) return;
		
		inorder(node, visitor);
		
		if(visitor.stop) return;
		visitor.stop = visitor.visit(node.element);
		
		inorder(node, visitor);
	}
	
	/**
	 * 中序遍历(左 根 右) 迭代方式
	 * */
	public void inorder1(Visitor<E> visitor) {
		if(root == null || visitor == null) return;
		Stack<Node<E>> stack = new Stack<>();
		Node<E> node = root;
		while (true) {
			if(node == null) {
				if(stack.isEmpty()) return;
				
				node = stack.pop();
				
				if(visitor.stop) return;
				visitor.stop = visitor.visit(node.element);
				
				node = node.right;
				
			} else {
				stack.push(node);
				node = node.left;
			}
		}
	}
	
	/**
	 * 后序遍历(左 右 根) 递归方式
	 * */
	public void postorder(Visitor<E> visitor) {
		if(visitor == null) return;
		postorder(root, visitor);
	}
	private void postorder(Node<E> node, Visitor<E> visitor) {
		if(node == null || visitor.stop) return;
		
		postorder(node, visitor);
		postorder(node, visitor);
		
		if(visitor.stop) return;
		
		visitor.stop = visitor.visit(node.element);
	}

	/**
	 * 后序遍历(左 右 根) 迭代方式
	 * */
	public void postorder1(Visitor<E> visitor) {
		if(root == null || visitor == null) return;
		Stack<Node<E>> stack = new Stack<>();
		Node<E> visitNode = null;
		stack.push(root);
		
		while (!stack.isEmpty()) {
			Node<E> node = stack.peek();
			//如果栈顶元素是叶子节点 或者 是上一次访问节点的父节点 则弹出 访问
			if(node.isLeaf() || (visitNode != null && (visitNode == node.left || visitNode ==  node.right))) {
				visitNode = stack.pop();
				if(visitor.stop) return;
				visitor.stop = visitor.visit(node.element);
			} else {
				if(node.right != null) stack.push(node.right);
				if(node.left != null) stack.push(node.left);
			}
		}
	}
	
	/**
	 * 层次遍历 从上到下 从左到右 一次访问
	 * */
	public void levelorder(Visitor<E> visitor) {
		if(root == null || visitor == null) return;
		Queue<Node<E>> queue = new LinkedList<>();
		queue.offer(root);
		
		while (!queue.isEmpty()) {
			Node<E> node = queue.poll();
			
			if(visitor.stop) return;
			visitor.stop = visitor.visit(node.element);
			
			if(node.left != null) queue.offer(node.left);
			if(node.right != null) queue.offer(node.right);
		}
		
	}
	//========================================end=================================================//
	
	/**
	 * 判断是否是完全二叉树 利用层次遍历
	 * */
	public boolean isComplete() {
		if(root == null) return false;
		
		Queue<Node<E>> queue = new LinkedList<>();
		queue.offer(root);
		boolean isLeaf = false;
		
		while (!queue.isEmpty()) {
			Node<E> node = queue.poll();
			
			if(isLeaf && !node.isLeaf()) return false;
			
			// 如果左节点不为空 则进栈
			if(node.left != null) {
				queue.offer(node.left);
			} else if(node.right != null) {
				return false;
			}
			
			if(node.right != null) {
				queue.offer(node.right);
			} else {// 如果右节点为空 表明后面的节点应该都叶子节点
				isLeaf = true;
			}
		}
		return true;
	}
	
	/**
	 * 树的高度 利用层次遍历 迭代
	 * */
	public int height() {
		int height = 0;
		if(root == null) return height;
		
		Queue<Node<E>> queue = new LinkedList<>();
		queue.offer(root);
		// 存储着每一层的元素数量
		int levelSize = 1;
		
		while (!queue.isEmpty()) {
			Node<E> node = queue.poll();
			levelSize--;
			
			if(node.left != null) queue.offer(node.left);
			if(node.right != null) queue.offer(node.right);
			
			// 意味着即将要访问下一层
			if(levelSize == 0) {
				levelSize = queue.size();
				height++;
			}
		}
		
		
		return height;
	}
	
	/**
	 * 树的高度 递归
	 * */
	public int height2() {
		return height2(root);
	}
	private int height2(Node<E> node) {
		if(node == null) return 0;
		return 1 + Math.max(height2(node.left), height2(node.right));
	}
	
	/**
	 * 前驱节点 就是中序遍历中 再左子树中的最右边 
	 * 若左子树不存在 则是再祖先节点中寻找 并是右子树
	 * */
	public Node<E> predecessor(Node<E> node) {
		if(node == null) return node;
		
		Node<E> pNode = node.left;
		if(pNode != null) { //左子树存在
			while (pNode.right != null) {
				pNode = pNode.right;
			}
			return pNode;
		}
		
		// 左节点不存在 则去祖先节点寻找
		while (node.parent != null && node.isLeftChild() ) {
			node = node.parent;
		}
		return node.parent;
	}
	
	/**
	 * 后继节点
	 * */
	public Node<E> successor(Node<E> node) {
		if(node == null) return node;
		
		Node<E> p = node.right;
		if(p != null) {
			while (p.left != null) {
				p = p.left;
			}
			return p;
		}
		
		while (node.parent != null && node.isRightChild()) {
			node = node.parent;
		}
		
		return node.parent;
	}
	
	
	
	public static abstract class Visitor<E> {
		boolean stop;
		abstract boolean visit(E element);
	}
	
	
	protected static class Node<E> {
		E element;
		Node<E> left;
		Node<E> right;
		Node<E> parent;
		public Node(E element, Node<E> parent) {
			this.element = element;
			this.parent = parent;
		}
		
		public boolean hasTwoChildren() {
			return left != null && right != null;
		}
		
		public boolean isLeaf() {
			return left == null && right == null;
		}
		
		public boolean isLeftChild() {
			return parent != null && this == parent.left;
		}
		
		public boolean isRightChild() {
			return parent != null && this == parent.right;
		}
		
		public Node<E> sibling() {
			if(isLeftChild()) {
				return parent.right;
			}
			
			if(isRightChild()) {
				return parent.left;
			}
			
			return null;
		}
	}


	@Override
	public Object root() {
		return root;
	}

	@Override
	public Object left(Object node) {
		return ((Node<E>)node).left;
	}

	@Override
	public Object right(Object node) {
		return ((Node<E>)node).right;
	}

	@Override
	public Object string(Object node) {
		Node<E> myNode = (Node<E>)node;
		String parentString = "null";
		if (myNode.parent != null) {
			parentString = myNode.parent.element.toString();
		}
		return myNode.element + "_p(" + parentString + ")";
	}
}
