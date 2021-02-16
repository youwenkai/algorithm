package leetcode.Tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;


public class _144_二叉树的前序遍历 {
	public List<Integer> preorderTraversal(TreeNode root){		
		List<Integer> result = new ArrayList<>();
		
		if(root == null) return result;
		Stack<TreeNode> stack = new Stack<TreeNode>();
		stack.push(root);
		
		while (!stack.isEmpty()) {
			TreeNode node = stack.pop();
			result.add(node.val);
			
			if(node.right != null) {
				stack.push(node.right);
			}
			
			if(node.left != null) {
				stack.push(node.left);
			}
		}
		
		
		return result;
	}
	// 此方法用时更短
	public List<Integer> preorderTraversal1(TreeNode root){
		List<Integer> result = new ArrayList<>();
		
		if(root == null) return result;
		Stack<TreeNode> stack = new Stack<TreeNode>();
		TreeNode node = root;
		while (true) {
			if(node == null) {
				if(stack.isEmpty()) return result;
				// node为空 说明已经到达了最左子树
				node = stack.pop();
			} else {
				//先访问根节点
				result.add(node.val);
				//将右子树入栈
				if(node.right != null) {
					stack.push(node.right);					
				}
				node = node.left;
			}
		}
	}
}
