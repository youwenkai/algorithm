package leetcode.链表;

public class _83_删除排序链表中的重复元素 {
/**
 * 给定一个排序链表，删除所有重复的元素，使得每个元素只出现一次。
 * 输入: 1->1->2
 * 输出: 1->2
 * */
	public ListNode deleteDuplicates(ListNode head) {
		
		if(head == null || head.next == null) return head;
		
		// 用双指针
		ListNode prev = head;
		ListNode current = prev.next;
		
		while (current != null) {
			if(prev.val == current.val) {
				current = current.next;
				prev.next = current;
			} else {
				prev = current;
				current = current.next;
			}
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
