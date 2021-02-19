package leetcode.链表;

/**
 *  输入: 1->2->3->4->5->NULL
	输出: 5->4->3->2->1->NULL
	
	你可以迭代或递归地反转链表。你能否用两种方法解决这道题？
 * */

public class _206_反转链表 {
	
	// 迭代方法 思路就是利用头插法添加节点
	public ListNode reverseList(ListNode head) {
		if(head == null || head.next == null) return head;
		ListNode newHead = null;
		// 头插法
		while (head != null) {
			ListNode tmp = head.next;
			
			head.next = newHead;
			newHead = head;
			
			head = tmp;
			
		}
		return newHead;
    }
	
	// 递归去视频看一下 
	public ListNode reverseList1(ListNode head) {
		if(head == null || head.next == null) return head;
		
		ListNode newHead = reverseList1(head.next);
		
		return newHead;
	}
	
	
	
	public class ListNode {
		int val;
	    ListNode next;
	    ListNode() {}
	    ListNode(int val) { this.val = val; }
	    ListNode(int val, ListNode next) { this.val = val; this.next = next; }
	}
}
