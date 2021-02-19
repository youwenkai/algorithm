package leetcode.Tree;

/**
 * 给定一个二叉搜索树的根节点 root 和一个值 key，删除二叉搜索树中的 key 对应的节点，并保证二叉搜索树的性质不变。
 * 返回二叉搜索树（有可能被更新）的根节点的引用。
 * */
public class _450_删除二叉搜索树中的节点 {
	
	
    public TreeNode deleteNode(TreeNode root, int key) {
    	// 删除有三种情况 如果key对应的node
    	// 1.是叶子节点 则直接删除
    	// 2.是度为1的节点 则用它的子节点替代它的位置
    	// 3.是度为2的节点 则用它的前驱或者后继节点 替换 然后删除前驱或者后继节点
    	// 时间复杂度 O(logn)
    	
    	
    	// 第二中 是用递归方法
    	if(root == null) return root;
    	
    	if(key > root.val) {
    		root.right = deleteNode(root.right, key);
    	} else if(key < root.val) {
    		root.left = deleteNode(root.left, key);
    	} else {
    		//如果是叶子节点 则直接删除
    		if(root.left == null && root.left == null) {
    			root = null;
    		} else if(root.right != null) {
    			root.val = successor(root);
    			root.right = deleteNode(root.right, root.val);
    		} else {
    			root.val = predecessor(root);
    			root.left = deleteNode(root.left, root.val);
    		}
    	}
    	
    	return root;
    
    }
    // 前驱
    private int predecessor(TreeNode node) {
    	node = node.left;
		while (node.right != null) {
			node = node.right;
		}
		return node.val;
    }
    
    private int successor(TreeNode node) {
    	node = node.right;
    	while (node.left != null) {
			node = node.right;
		}
    	
    	return node.val;
    }
    
}
