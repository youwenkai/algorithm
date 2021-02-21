package algorithm.Sort.Compare;

import algorithm.Sort.AbstractSort;

/**
 * 
 * 
 * */
public class InsertionSort<T extends Comparable<T>> extends AbstractSort<T> {

	@Override
	protected void sort() {
		for(int begin = 1; begin < array.length; begin++) {
			int cur = begin;
			while (cur > 0 && cmp(cur, cur - 1) < 0) {
				// 交换
				swap(cur, cur - 1);
				cur--;
			}
		}
	}

}
