package leetcode.Tree;

import java.util.LinkedList;
import java.util.Queue;

public class _226_翻转二叉树 {
	// 请分别使用递归和迭代的方式实现
	
	//递归
	public TreeNode invertTree(TreeNode root) {
		if(root == null) return root;
		
		return inverTree(root);
    }
	
	private TreeNode inverTree(TreeNode node) {
		if(node == null) return node;
		
		TreeNode temp = node.left;
		node.left = node.right;
		node.right = temp;
		
		if(node.left != null) inverTree(node.left);
		if(node.right != null) inverTree(node.right);
		return node;
	}
	
	// 迭代 相当于层次遍历
	public TreeNode invertTree1(TreeNode root) {
		if(root == null) return root;
		
		Queue<TreeNode> queue = new LinkedList<TreeNode>();
		queue.offer(root);
		
		while(!queue.isEmpty()) {
			TreeNode node = queue.poll();
			
			TreeNode temp = node.left;
			node.left = node.right;
			node.right = temp;
			
			if(node.left != null) queue.offer(node.left);
			if(node.right != null) queue.offer(node.right);
		}
		
		return root;
    }
}
