package leetcode.Tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class _145_二叉树的后序遍历 {
	public List<Integer> postorderTraversal(TreeNode root) {
		List<Integer> result = new ArrayList<>();
		if(root == null) return result;
		
		Stack<TreeNode> stack = new Stack<TreeNode>();
		TreeNode visitNode = null;
		stack.push(root);
		while (!stack.isEmpty()) {
			TreeNode topNode = stack.peek();
			
			
			// 如果是叶子节点 或者 上次访问节点的是栈顶节点的子节点 则弹出访问
			if((topNode.left == null && topNode.right == null) || 
					(visitNode != null && ( topNode.left == visitNode || topNode.right == visitNode ))) {
				visitNode = stack.pop();
				result.add(visitNode.val);					
			} else {
				if(topNode.right != null) {
					stack.push(topNode.right);					
				}
				
				if(topNode.left != null) {					
					stack.push(topNode.left);
				}
			}
		}
		
		return result;
    }
}
