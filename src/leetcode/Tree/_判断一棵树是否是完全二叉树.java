package leetcode.Tree;

import java.util.LinkedList;
import java.util.Queue;

public class _判断一棵树是否是完全二叉树 {
	public boolean isFullBenaryTree(TreeNode root) {
		
		// 判断是否是完全二叉树 利用层次遍历
		Queue<TreeNode> queue = new LinkedList<TreeNode>();
		queue.offer(root);
		boolean isLeaf = false;
		while (!queue.isEmpty()) {
			TreeNode node = queue.poll();
			
			if(isLeaf) {
				if(node.left != null || node.right != null) {
					return false;
				}
			} else {
				// 如果有左子树，则添加入队
				if(node.left != null) {
					queue.offer(node.left);
				} else if(node.left == null && node.right != null) {
					//如果左子树不存在，而右子树存在 则不是完全二叉树
					return false;
				}
				//如果右子树存在 则入队
				if(node.right != null) {
					queue.offer(node.right);
				} else { // 右子树不存在， 说明后面的节点都是子节点， 否则为false
					isLeaf = true;
				}				
			}
			
		}
		
		return true;
	}
}
