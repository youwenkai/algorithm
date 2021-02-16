package leetcode.LinkedList;

public class _876_链表的中间结点 {
/**
 * 给定一个头结点为 head 的非空单链表，返回链表的中间结点。
 * 如果有两个中间结点，则返回第二个中间结点。
 * 
 * 
 * 输入：[1,2,3,4,5]
 * 输出：此列表中的结点 3 
 * 
 * 输入：[1,2,3,4,5,6]
 * 输出：此列表中的结点 4
 * */
	
	public ListNode middleNode(ListNode head) {
		if(head == null) return head;
		if(head.next == null) return head;
		if(head.next.next == null) return head.next;
		// 利用快慢指针， 快指针每次走2步 慢指针每次走1步 
		// 快指针到达时 慢指针正好在中间位置
		
		ListNode fast = head;
		ListNode slow = head;
		
		while (fast != null && fast.next != null) {
			slow = slow.next;
			fast = fast.next.next;
		}
		
		return slow;
    }
	
	
	public class ListNode {
	     int val;
	     ListNode next;
	     ListNode(int x) { val = x; }
	 }
}
