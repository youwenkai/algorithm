package algorithm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;


import algorithm.List.Stack;
import algorithm.List.Circle.CircleLinkedList;
import algorithm.List.Queue.Circle.CircleDeque;
import algorithm.List.Queue.Circle.CircleQueue;
import algorithm.List.SingleList.SingleLinkedList2;
import algorithm.Sort.AbstractSort;
import algorithm.Sort.Compare.BubbleSort;
import algorithm.Sort.Compare.BubbleSort_1;
import algorithm.Sort.Compare.BubbleSort_2;
import algorithm.Sort.Compare.HeapSort;
import algorithm.Sort.Compare.InsertionSort;
import algorithm.Sort.Compare.InsertionSort_1;
import algorithm.Sort.Compare.InsertionSort_2;
import algorithm.Sort.Compare.SelectionSort;
import algorithm.Sort.tools.Asserts;
import algorithm.Sort.tools.Integers;
import core.Heap.BinaryHeap;
import leetcode.Queue._232_用栈实现队列;
import leetcode.Stack._150_逆波兰表达式求值;
import leetcode.Stack._20_有效的括号;
import leetcode.Stack._224_基本计算器;
import leetcode.Stack._856_括号的分数;
import 复习.printer.BinaryTrees;

@SuppressWarnings({"rawtypes", "unchecked", "unused"})
public class main {

	public static void main(String[] args) {
		Integer[] array = Integers.random(10000, 0, 10000); 
		
		testSorts(array,
//				new BubbleSort(),
//				new BubbleSort_1(),
				new BubbleSort_2(),
				new SelectionSort(),
				new HeapSort(),
				new InsertionSort(),
				new InsertionSort_1(),
				new InsertionSort_2()
		);
	}
	
	static void testSorts(Integer[] array, AbstractSort... sorts) {
		for (AbstractSort sort : sorts) {
			Integer[] newArray = Integers.copy(array);
			sort.sort(newArray);
			Asserts.test(Integers.isAscOrder(newArray));
		}
		Arrays.sort(sorts);
		
		for (AbstractSort sort : sorts) {
			System.out.println(sort);
		}
	}

}
