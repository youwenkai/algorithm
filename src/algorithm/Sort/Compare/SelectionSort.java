package algorithm.Sort.Compare;

import algorithm.Sort.AbstractSort;

/**
 * 选择排序
 * 从序列中找出最大的那个元素，然后与最末尾的元素交换位置
 * */
public class SelectionSort<T extends Comparable<T>> extends AbstractSort<T> {

	@Override
	protected void sort() {
		// TODO Auto-generated method stub
		for(int end = array.length - 1; end > 0; end--) {
			int max = 0;
			for(int begin = 1; begin <= end; begin++) {
				if(cmp(max, begin) < 0) {
					max = begin;
				}
			}
			swap(max, end);
		}
	}

}
