package leetcode.Tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class _590_N叉树的后序遍历 {
	public List<Integer> postorder(Node root) {
        List<Integer> res = new ArrayList<Integer>();
        if(root == null) return res;
        
        Stack<Node> stack = new Stack<Node>();
        stack.push(root);
        Node visit = null;
        while (!stack.isEmpty()) {
			Node node = stack.peek();
			
			// 如果栈顶元素是叶子节点 或者 上一个访问节点是栈顶元素的子节点 则弹出访问
			if(node.children.size() == 0 || (visit != null && node.children.indexOf(visit) > -1 )) {
				visit = stack.pop();
				res.add(visit.val);
			} else {
				int size = node.children.size();
				for(int i = size - 1; i >= 0; i--) {
					Node child = node.children.get(i);
					if(child != null) {
						stack.push(child);							
					}
				}
			}
		}
        
        return res;
    }
}
