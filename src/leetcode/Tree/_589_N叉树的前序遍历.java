package leetcode.Tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 就是前序遍历的迭代方法
 * */
public class _589_N叉树的前序遍历 {
	public List<Integer> preorder(Node root) {
        List<Integer> res = new ArrayList<Integer>();
        if(root == null) return res;
        
        Stack<Node> stack = new Stack<Node>();
        Node node = root;
        while (true) {
        	if(node == null) {
        		if(stack.isEmpty()) return res;
        		node = stack.pop();
        	} else {
        		// 先访问值
        		res.add(node.val);
        		// 然后将除了第一个子节点 其他的都入栈保存
        		int size = node.children.size();
        		if(size >= 1) {
        			for(int i = size - 1; i > 0; i--) {
        				Node child = node.children.get(i);
        				if(child != null) {
        					stack.push(child);        					
        				}
        			}
        			node = node.children.get(0);
        		} else {
        			node = null;
        		}
        	}
		}
    }
}
