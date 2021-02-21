package 复习;

import 复习.Tree.AVLTree;
import 复习.Tree.BinarySearchTree;
import 复习.Tree.RBTree;
import 复习.printer.BinaryTrees;

public class main {

	public static void main(String[] args) {
		
		int[] data = { 7,4,9,2,5,8,11,1,3,6,10,12 };
		
//		BinarySearchTree<Integer> tree = new BinarySearchTree<>();
//		AVLTree<Integer> tree =  new AVLTree<>();
		RBTree<Integer> tree = new RBTree<>();
		
		for(int i = 0; i < data.length; i++) {
			tree.add(data[i]);
		}
		
//		tree.remove(2);
//		tree.remove(4);
//		tree.remove(8);
		
		BinaryTrees.println(tree);
	}

}
