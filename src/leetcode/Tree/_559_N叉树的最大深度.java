package leetcode.Tree;

import java.util.LinkedList;
import java.util.Queue;

public class _559_N叉树的最大深度 {
	// 迭代
	public int maxDepth(Node root) {
        int height = 0;
        if(root == null) return height;
        Queue<Node> queue = new LinkedList<Node>();
        queue.offer(root);
        int levelSize = 1;
        
        while (!queue.isEmpty()) {
			Node node = queue.poll();
			levelSize--;
			
			int size = node.children.size();
			for(int i = 0; i < size; i++) {
				Node child = node.children.get(i);
				if(child != null) {
					queue.offer(child);
				}
			}
			
			if(levelSize == 0) {
				levelSize = queue.size();
				height++;
			}
		}
        
        
        
        return height;
    }

	
	// 递归
	public int maxDepth1(Node root) {
		return depth(root);
	}
	
	private int depth(Node node) {
		if(node == null) return 0;
		
		int size = node.children.size();
		int max = 0;
		for(int i = 0; i < size; i++) {
			int curHeight = depth(node.children.get(i));
			if(max < curHeight) max = curHeight;
		}
		
		return 1 + max;
	}
}
