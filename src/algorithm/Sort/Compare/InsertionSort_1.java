package algorithm.Sort.Compare;

import algorithm.Sort.AbstractSort;

/**
 * 插入排序优化
 * 
 * 将交换转为挪动
 * */
public class InsertionSort_1<T extends Comparable<T>> extends AbstractSort<T> {

	@Override
	protected void sort() {
		for(int begin = 1; begin < array.length; begin++) {
			int cur = begin;
			// 备份要排序的元素
			T temp = array[cur];
			while (cur > 0 && cmp(temp, array[cur - 1]) < 0) {
				array[cur] = array[cur - 1];
				cur--;
			}
			array[cur] = temp;
		}
	}

}
