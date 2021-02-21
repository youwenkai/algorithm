package algorithm.Sort.Compare;

import algorithm.Sort.AbstractSort;

/**
 * 如果尾部局部有序 可以记录最后一次交换的位置 减少交换次数
 * */
public class BubbleSort_2<T extends Comparable<T>> extends AbstractSort<T> {

	@Override
	protected void sort() {
		for(int end = array.length - 1; end > 0; end--) {
			int sortIndex = 1;
			for(int begin = 1; begin <= end; begin++) {
				if(cmp(begin, begin - 1) < 0) {
					swap(begin, begin - 1);
					sortIndex = begin;
				}
			}
			end = sortIndex;
		}
	}

}
