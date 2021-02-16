package leetcode.Queue;

import java.util.Stack;

/**
 * 请你仅使用两个栈实现先入先出队列。
 * 队列应当支持一般队列的支持的所有操作（push、pop、peek、empty）：
 * */
public class _232_用栈实现队列 {

    private Stack<Integer> inStack;
    private Stack<Integer> outStack;
    public _232_用栈实现队列() {
    	inStack = new Stack<>();
    	outStack = new Stack<>();
    }
    
    /** Push element x to the back of queue. */
    public void push(int x) {
    	inStack.push(x);
    }
    
    /** Removes the element from in front of queue and returns that element. */
    public int pop() {
    	if(outStack.isEmpty()) {
    		while (!inStack.isEmpty()) {
				outStack.push(inStack.pop());
			}
    	}
    	
    	return outStack.pop();
    }
    
    /** Get the front element. */
    public int peek() {
    	if(outStack.isEmpty()) {
    		return inStack.get(0);
    		
    	}
    	return outStack.peek();
    }
    
    /** Returns whether the queue is empty. */
    public boolean empty() {
    	return inStack.isEmpty() & outStack.isEmpty();
    }
}
