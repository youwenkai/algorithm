package leetcode.Tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;


public class _102_二叉树的层序遍历 {
	public List<List<Integer>> levelOrder(TreeNode root) {
		List<List<Integer>> res = new ArrayList<>();
		
		if(root == null) return res;
		
		//从上到下 从左到右 顺序访问节点
		Queue<TreeNode> inQueue = new LinkedList<TreeNode>();
		Queue<TreeNode> outQueue = new LinkedList<TreeNode>();
		inQueue.offer(root);
		int index = 0;
		while (!inQueue.isEmpty()) {
			TreeNode node = inQueue.poll();
			if(res.size() <= index) {
				res.add(new ArrayList<Integer>());
			}
			res.get(index).add(node.val);
			
			if(node.left != null)  outQueue.offer(node.left);
			if(node.right != null) outQueue.offer(node.right);
			
			if(inQueue.isEmpty()) {
				Queue<TreeNode> temp = outQueue;
				outQueue = inQueue;
				inQueue = temp;
				index++;
			}
		}
		
		return res;
    }
}
