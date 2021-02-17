package leetcode.Tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 
 * 给定一个二叉树，返回其节点值自底向上的层序遍历
 * 即按从叶子节点所在层到根节点所在的层，逐层从左向右遍历
 * */
public class _107_二叉树的层序遍历II {
	public List<List<Integer>> levelOrderBottom(TreeNode root) {
		List<List<Integer>> res = new ArrayList<>();
		if(root == null) return res;

		Queue<TreeNode> queue = new LinkedList<TreeNode>();
		queue.offer(root);
		int levelSize = 1;
		while (!queue.isEmpty()) {
			TreeNode node = queue.poll();
			levelSize--;
			if(res.size() == 0) {
				res.add(0,new ArrayList<Integer>());
			}
			res.get(0).add(node.val);
			
			if(node.left != null) queue.offer(node.left);
			if(node.right != null) queue.offer(node.right);
			
			if(levelSize == 0 && queue.size() != 0) {// 说明这一层已经访问完
				levelSize = queue.size();
				res.add(0, new ArrayList<Integer>());
			}
		}
		return res;
    }
}
