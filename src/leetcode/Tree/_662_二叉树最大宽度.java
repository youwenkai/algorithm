package leetcode.Tree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 给定一个二叉树，编写一个函数来获取这个树的最大宽度。树的宽度是所有层中的最大宽度
 * 这个二叉树与满二叉树（full binary tree）结构相同，但一些节点为空。
 * 
 * 每一层的宽度被定义为两个端点（该层最左和最右的非空节点，两端点间的null节点也计入长度）之间的长度。
 * 
 * */
public class _662_二叉树最大宽度 {
	// 添加一个position的属性 => left = postion * 2  => right = postion * 2 + 1;   width = right - left + 1; 
	public int widthOfBinaryTree(TreeNode root) {
        int maxWidth = 0;
        if(root == null) return maxWidth;
        
        Queue<Node> queue = new LinkedList<Node>();
        queue.offer(new Node(root, 1));
        int levelSize = 1;
        int width = 0;
        int startIndex = -1;
        while (!queue.isEmpty()) {
			Node node = queue.poll();
			levelSize--;
			
			if(node.element.left != null) {
				queue.offer(new Node(node.element.left, (node.position << 1)));
			}
			
			if(node.element.right != null) {
				queue.offer(new Node(node.element.right, (node.position << 1) + 1));
			}
			
			if(startIndex == -1) {
				startIndex = node.position;
			}
			
			if(levelSize == 0) {
				levelSize = queue.size();
				width =  node.position - startIndex + 1;
				maxWidth = Math.max(maxWidth, width);
				startIndex = -1;
			}
		}
        
        
        return maxWidth;
    }
	
	private static class Node{
		 int position;
		 TreeNode element;
		 public Node(TreeNode element, int position) {
			 this.element = element;
			 this.position = position;
		 }
	}
}
