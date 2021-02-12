package leetcode.链表;

public class _203_删除链表中等于给定值的所有节点 {

	
	/**
	 * 删除链表中等于给定值 val 的所有节点。
	 * 输入: 1->2->6->3->4->5->6, val = 6
	 * 输出: 1->2->3->4->5
	 * */
	
	public ListNode removeElements(ListNode head, int val) {
		
		if(head == null) return head;
		
		ListNode current = head;
		ListNode prev = null;
		while (current != null) {
			if(current.val == val) {
				if(prev == null) {
					head = current.next;
				} else {
					prev.next = current.next;
				}
			} else {
				prev = current;
			}
			current = current.next;
		}
		return head;
    }
	
	
	public class ListNode {
		int val;
		ListNode next;
		ListNode() {}
		ListNode(int val) { this.val = val; }
		ListNode(int val, ListNode next) { this.val = val; this.next = next; }
	}
}
