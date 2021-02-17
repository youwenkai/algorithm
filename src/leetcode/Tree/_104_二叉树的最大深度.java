package leetcode.Tree;

import java.util.LinkedList;
import java.util.Queue;

public class _104_二叉树的最大深度 {
	// 迭代方式
	public int maxDepth(TreeNode root) {
		// 利用层序遍历
		if(root == null) return 0;
		
		Queue<TreeNode> queue = new LinkedList<TreeNode>();
		queue.offer(root);
		
		int height = 0;
		int levelSize = 1;
		
		while (!queue.isEmpty()) {
			TreeNode node = queue.poll();
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

	// 遍历方式
	public int maxDepth1(TreeNode root) {
		return depth(root);
	}
	
	private int depth(TreeNode node) {
		if(node == null) return 0;
		return 1 + Math.max(depth(node.left), depth(node.right));
	}
}
