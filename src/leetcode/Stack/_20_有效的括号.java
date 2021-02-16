package leetcode.Stack;

import java.util.Stack;

public class _20_有效的括号 {

	/**
	 * 给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串 s ，判断字符串是否有效
	 * 有效字符串需满足：
	 * 1.左括号必须用相同类型的右括号闭合。
	 * 2.左括号必须以正确的顺序闭合。
	 * */
	
	public boolean isValid(String s) {
		int len = s.length();
		Stack<Character> stack = new Stack<>();
		for(int i = 0; i < len; i++) {
			char ch = s.charAt(i);
			if(ch == '(' || ch == '[' || ch == '{') {
				stack.push(ch);
			} else {
				if(stack.isEmpty()) {
					return false;
				} else {
					char curCh = stack.pop();
					if(curCh == '(' && ch != ')' || curCh == '[' && ch != ']' || curCh == '{' && ch != '}') {
						return false;
					}
				}
			}
		}
		return stack.isEmpty() ? true : false;
    }
}
