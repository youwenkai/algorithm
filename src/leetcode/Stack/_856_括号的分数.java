package leetcode.Stack;

import java.util.Stack;

public class _856_括号的分数 {
/**
 * 给定一个平衡括号字符串 S，按下述规则计算该字符串的分数：
 * () 得 1 分。
 * AB 得 A + B 分，其中 A 和 B 是平衡括号字符串。
 * (A) 得 2 * A 分，其中 A 是平衡括号字符串。
 * */
	
	public int scoreOfParentheses(String S) {
		
		if(S == null || S.length() == 0) return 0;
		
		int len = S.length();
		Stack<Object> stack = new Stack<>();
		for(int i = 0; i < len; i++) {
			char ch = S.charAt(i);
			if(ch == '(') {
				stack.push(ch);
			} else {
				Object cur = stack.get(stack.size() -  1);
				if( ("(").equals(cur.toString())) {
					stack.pop();
					stack.push(1);
				} else {// 栈顶是数字即（ABC）的情况
					int sum = 0;
					String num;
					while (!("(").equals((num = stack.pop().toString()))) {
						sum += Integer.parseInt(num.toString());
					}
					stack.push(sum * 2);
				}
			}
			
		}
		
		int score = 0;
		while (!stack.isEmpty()) {
			score += Integer.parseInt(stack.pop().toString());
		}
		return score;
    }
}
