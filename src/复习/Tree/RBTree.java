package 复习.Tree;

public class RBTree<E> extends BBST<E>{
	
	private static final boolean RED = false;
	private static final boolean BLACK = true;

	@Override
	protected Node<E> createNode(E element, Node<E> parent) {
		return new RBNode<>(element, parent);
	}

	@Override
	protected void afterAdd(Node<E> node) {
		Node<E> parent = node.parent;
		
		//如果新增的是根节点 则直接染黑
		if(parent == null) {
			paintToBlack(node);
			return;
		}
		
		// 如果新添加的节点的父节点是黑色 则直接添加 不会影响红黑树的性质
		if(isBlack(parent)) return;
		
		// 到这里就说明父节点是红色的 就是出现双红
		Node<E> uncle = parent.sibling();
		Node<E> grand = parent.parent;
		// 如果叔父节点是黑色
		if(isBlack(uncle)) {
			if(parent.isLeftChild()) { //L
				if(node.isLeftChild()) { // LL
					paintToRed(grand);
					paintToBlack(parent);
					rotateRight(grand);
				} else {// LR
					paintToBlack(node);
					paintToRed(grand);
					
					rotateLeft(parent);
					rotateRight(grand);
				}
			} else {//R
				if(node.isLeftChild()) {
					paintToBlack(node);
					paintToRed(grand);
					
					rotateLeft(parent);
					rotateRight(grand);
				} else {
					paintToRed(grand);
					paintToBlack(parent);
					rotateLeft(grand);
				}
			}
		}
		
		// 到这里就说 叔父节点是红色 会造成节点上溢
		// 将grand 将其染红 向上合并
		paintToRed(grand);
		paintToBlack(parent);
		paintToBlack(uncle);
		afterAdd(grand);
	}

	@Override
	protected void afterRemove(Node<E> node) {
		// 如果删除的节点是红色 或者 用以取代删除节点的子节点是红色 将其染黑
		if(isRed(node)) {
			paintToBlack(node);
			return;
		}
		
		Node<E> parent = node.parent;
		if(parent == null) return;
		
		
		//到这里就是说明 被删除的是黑色子节点 然后再修复之前 它的父节点已经将相对应的左右指针断开
		// 会造成下溢的情况
		boolean left = node.left == null || node.isLeftChild();
		Node<E> sibling = left ? parent.right : parent.left;
		// 如果兄弟节点是红色的
		if(left) {
			// 兄弟节点是红色的， 需要将兄弟的子节点变成其父节点的子节点 旋转
			// 兄弟节点染黑 父节点染红
			if(isRed(sibling)) {
				paintToBlack(sibling);
				paintToRed(parent);
				
				rotateLeft(parent);
				
				//更改兄弟节点
				sibling = parent.right;
			}
			
			// 到这里就是说明兄弟节点必是黑色
			// 则要判断兄弟节点是否右子节点可以借 即判断兄弟节点的左右子节点至少有一个红节点
			if(isBlack(sibling.left) && isBlack(sibling.right)) {// 没有可以借的子节点 需要父节点向下合并
				boolean parentIsBlack = isBlack(parent);
				paintToBlack(parent);
				paintToRed(sibling);
				if(parentIsBlack) {// 如果父节点是黑色的 则会向上继续造成下溢
					afterRemove(parent);
				}
				
			} else {// 兄弟节点至少有个红节点  可以向它借
				// 有三种情况 B -> R  R <- B  R <- B -> R
				//将LR的情况先处理
				if(isBlack(sibling.right)) {
					rotateRight(sibling);
					sibling = parent.right;
				}
				// 兄弟节点 继承父节点的颜色
				paint(sibling, colorOf(parent));
				paintToBlack(parent);
				paintToBlack(sibling.right);
				rotateLeft(parent);
			}
		} else {
			// 兄弟节点是红色的， 需要将兄弟的子节点变成其父节点的子节点 旋转
			// 兄弟节点染黑 父节点染红
			if(isRed(sibling)) {
				paintToBlack(sibling);
				paintToRed(parent);
				
				rotateRight(parent);
				
				//更改兄弟节点
				sibling = parent.left;
			}
			
			// 到这里就是说明兄弟节点必是黑色
			// 则要判断兄弟节点是否右子节点可以借 即判断兄弟节点的左右子节点至少有一个红节点
			if(isBlack(sibling.left) && isBlack(sibling.right)) {// 没有可以借的子节点 需要父节点向下合并
				boolean parentIsBlack = isBlack(parent);
				paintToBlack(parent);
				paintToRed(sibling);
				if(parentIsBlack) {// 如果父节点是黑色的 则会向上继续造成下溢
					afterRemove(parent);
				}
				
			} else {// 兄弟节点至少有个红节点  可以向它借
				// 有三种情况 B -> R  R <- B  R <- B -> R
				//将LR的情况先处理
				if(isBlack(sibling.left)) {
					rotateLeft(sibling);
					sibling = parent.left;
				}
				// 兄弟节点 继承父节点的颜色
				paint(sibling, colorOf(parent));
				paintToBlack(parent);
				paintToBlack(sibling.left);
				rotateRight(parent);
			}
		}
	}
	
	private Node<E> paint(Node<E> node, boolean color) {
		if(node == null) return node;
		((RBNode<E>)node).color = color;
		return node;
	}
	
	private Node<E> paintToBlack(Node<E> node) {
		return paint(node, BLACK);
	}
	
	private Node<E> paintToRed(Node<E> node) {
		return paint(node, RED);
	}
	
	private boolean colorOf(Node<E> node) {
		return ((RBNode<E>)node).color;
	}
	
	private boolean isBlack(Node<E> node) {
		return colorOf(node) == BLACK;
	}
	
	private boolean isRed(Node<E> node) {
		return colorOf(node) == RED;
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
