package leetcode.Stack;

import java.util.Stack;

public class _224_基本计算器 {
/**
 * 实现一个基本的计算器来计算一个简单的字符串表达式 s 的值。
 * 输入：s = "1 + 1"
 * 输出：2
 * */
	
    public int calculate(String s) {
    	// 先转为后缀表达式
    	String temp = s.replaceAll("\\s", "");
    	System.out.println(temp);
    	
    	Stack<String> stack = new Stack<String>();
    	for(int i = 0; i < temp.length(); i++) {
    		char cur = temp.charAt(i);
    	}
    	
    	return 0;
    }
}
