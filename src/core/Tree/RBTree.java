package core.Tree;

import java.util.Comparator;

/**
 * 性质1：节点是红色或者黑色
 * 性质2：根节点是黑色
 * 性质3：叶子节点是黑色
 * 性质4：红色节点的子节点是黑色
 * 性质5：任意节点到叶子节点的路劲上包含的黑色节点是相同的
 * 
 * */
public class RBTree<E> extends BBST<E> {
	
	private static final boolean BLACK = true;
	private static final boolean RED = false;

	public RBTree() {
		this(null);
	}

	public RBTree(Comparator<E> comparator) {
		super(comparator);
	}
	
	@Override
	protected void afterAdd(Node<E> node) {
		//将新增加节点染成红色 能够快速满足红黑树的性质1，2，3，5
		
		Node<E> parent = node.parent;
		if(parent == null) {// 如果node是根节点 则染黑
			paintToBlack(node);
			return;
		}
		//有12种情况 4阶B树
		//如果父节点是黑色（4中） 则不需要处理 因为已经满足的性质4
		if(isBlack(parent)) return;
		
		//剩余其他8种
		// 判断叔父节点的颜色
		Node<E> uncle = parent.sibling();
		Node<E> grand = parent.parent;
		if(isBlack(uncle)) { //如果是黑色 满足LL/RR/LR/RL
			if(parent.isLeftChild()) {// L
				if(node.isLeftChild()){// 	LL
					paintToBlack(parent);
					paintToRed(grand);
					rotateRight(grand);
				} else { // 				LR
					paintToBlack(node);
					paintToBlack(grand);
					rotateLeft(parent);
					rotateRight(grand);
				}
			} else {// R
				if(node.isLeftChild()) {//  RL
					paintToBlack(node);
					paintToRed(grand);
					rotateRight(parent);
					rotateLeft(grand);
				} else {// 					RR
					paintToBlack(parent);
					paintToRed(grand);
					rotateLeft(grand);
				}
			}
		}
		
		// 叔父节点是红色 会造成节点上溢 只需要将分裂成独立节点parent,uncle染黑
		// 然后将grand向上合并的并且染红 将其当作新添加的节点 递归调用afterAdd修复
		paintToBlack(parent);
		paintToBlack(uncle);
		paintToRed(grand);
		afterAdd(grand);
	}
	
	
	@Override
	protected void afterRemove(Node<E> node) {
		//1.如果被删除的节点是红色
		//2.如果被删除的节点度为1，那个用以取代它的节点是红色，将其染黑
		if(isRed(node)) {
			paintToBlack(node);
			return;
		}
		Node<E> parent = node.parent;
		// 删除的是根节点
		if(parent == null) return;
		
		//到这里 就是删除的黑色叶子节点，会造成节点下溢
		
		//找他的兄弟节点  到这里就表明必是叶子节点 parent.left == null 表明在修复之前 已经将父节点对应的指针断开
		boolean left = parent.left == null || node.isLeftChild() ;
		Node<E> sibling = left ? parent.right : parent.left;

		// 被删除的节点在左边
		if(left) {
			// 先去处理兄弟节点是红色，因为通过旋转可以将其转换为兄弟转换为黑色
			if(isRed(sibling)) { //将兄弟节点染成黑色 父节点染成红色
				paintToBlack(sibling);
				paintToRed(parent);
				rotateLeft(sibling);
				//更改兄弟节点
				sibling = parent.right;
			}
			
			//到这里 就说明兄弟节点必是黑色
			// 判断兄弟节点的子节点数量
			if(isBlack(sibling.left) && isBlack(sibling.right)) { //有两个黑色子节点
				//也就是说没有子节点可以借  将父节点向下合并 父节点染黑 兄弟节点染红
				boolean parentIsBlack = isBlack(parent);
				paintToBlack(parent);
				paintToRed(sibling);
				// 如果父节点是黑色节点 向下合并 可能会造成父节点的超级节点也会出现下溢
				// 所以将其当成删除节点 重新修复
				if(parentIsBlack) {
					afterRemove(parent);
				}
			} else { //如果兄弟节点有子节点可以借 (1.左子节点 2.右子节点 3 左子节点 右子节点) 通过旋转 将兄弟节点继承父节点的颜色 父节点和兄弟的子节点染黑
				// 如果兄弟节点是右节点 即兄弟节点的左子节点是黑色的
				if(isBlack(sibling.right)) {
					//左旋转
					rotateRight(sibling);
					sibling = parent.right;
				}
				//将兄弟节点继承父节点的颜色
				painting(sibling, colorOf(parent));
				paintToBlack(parent);
				paintToBlack(sibling.right);
				//右旋转
				rotateLeft(parent);
			}
		} else {
			// 先去处理兄弟节点是红色，因为通过旋转可以将其转换为兄弟转换为黑色
			if(isRed(sibling)) { //将兄弟节点染成黑色 父节点染成红色
				paintToBlack(sibling);
				paintToRed(parent);
				rotateRight(sibling);
				//更改兄弟节点
				sibling = parent.left;
			}
			
			//到这里 就说明兄弟节点必是黑色
			// 判断兄弟节点的子节点数量
			if(isBlack(sibling.left) && isBlack(sibling.right)) { //有两个黑色子节点
				//也就是说没有子节点可以借  将父节点向下合并 父节点染黑 兄弟节点染红
				boolean parentIsBlack = isBlack(parent);
				paintToBlack(parent);
				paintToRed(sibling);
				// 如果父节点是黑色节点 向下合并 可能会造成父节点的超级节点也会出现下溢
				// 所以将其当成删除节点 重新修复
				if(parentIsBlack) {
					afterRemove(parent);
				}
			} else { //如果兄弟节点有子节点可以借 (1.左子节点 2.右子节点 3 左子节点 右子节点) 通过旋转 将兄弟节点继承父节点的颜色 父节点和兄弟的子节点染黑
				// 如果兄弟节点是右节点 即兄弟节点的左子节点是黑色的
				if(isBlack(sibling.left)) {
					//左旋转
					rotateLeft(sibling);
					sibling = parent.left;
				}
				//将兄弟节点继承父节点的颜色
				painting(sibling, colorOf(parent));
				paintToBlack(parent);
				paintToBlack(sibling.left);
				//右旋转
				rotateRight(parent);
			}
		}
		
	}
	
	@Override
	protected Node<E> createNode(E element, Node<E> parent) {
		return new RBNode<>(element, parent);
	}
	
	
	//========================辅助函数======================
	private boolean isBlack(Node<E> node) {
		return colorOf(node) == BLACK;
	}
	
	private boolean isRed(Node<E> node) {
		return colorOf(node) == RED;
	}
	
	private boolean colorOf(Node<E> node) {
		return node == null ? BLACK : ((RBNode<E>)node).color;
	}
	
	private Node<E> painting(Node<E> node, boolean color){
		if(node == null) return node;
		((RBNode<E>)node).color = color;
		
		return node;
	}
	
	private Node<E> paintToBlack(Node<E> node){
		return painting(node, BLACK);
	}
	
	private Node<E> paintToRed(Node<E> node){
		return painting(node, RED);
	}
	
	
	private static class RBNode<E> extends Node<E>{
		boolean color = RED;

		public RBNode(E element, Node<E> parent) {
			super(element, parent);
		}
		
		@Override
		public String toString() {
			String str = "";
			if (color == RED) {
				str = "R_";
			}
			return str + element.toString();
		}
	}

}
