package leetcode.Tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 非递归
 * */
public class _94_二叉树的中序遍历 {
	public List<Integer> inorderTraversal(TreeNode root) {
		List<Integer> result = new ArrayList<>();
		if(root == null) return result;
		
		Stack<TreeNode> stack = new Stack<TreeNode>();
		TreeNode node = root;
		while (true) {
			if(node == null) {
				if(stack.isEmpty()) return result;
				node = stack.pop();
				result.add(node.val);
				node = node.right;
			} else {
				stack.push(node);
				node = node.left;
			}
		}
    }
}
