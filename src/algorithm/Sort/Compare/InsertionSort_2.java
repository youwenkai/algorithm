package algorithm.Sort.Compare;

import algorithm.Sort.AbstractSort;

/**
 * 插入排序优化 - 二分搜索优化
 * */
public class InsertionSort_2<T extends Comparable<T>> extends AbstractSort<T> {

	@Override
	protected void sort() {
		for(int begin = 1; begin < array.length; begin++) {
			insert(begin, search(begin));
		}
	}
	
	//插入元素
	private void insert(int source, int dest) {
		T temp = array[source];
		for(int i = source; i > dest; i--) {
			array[i] = array[i - 1];
		}
		array[dest] = temp;
	}
	
	// 找寻待插入元素的位置
	private int search(int index) {
		int begin = 0;
		int end = index;
		while (begin < end) {
			int mid = (begin + end) >> 1;
			if(cmp(index, mid) < 0) {
				end = mid;
			} else {
				begin = mid + 1;
			}
		}
		
		return begin;
	}

}
