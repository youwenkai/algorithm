package algorithm.Sort.Compare;

import algorithm.Sort.AbstractSort;

/**
 * 冒泡排序
 * 
 * 从头开始 比较每一对相邻元素 如果第一个比第二个大，就交换他们的位置
 * */
public class BubbleSort<T extends Comparable<T>> extends AbstractSort<T> {

	@Override
	protected void sort() {
		// TODO Auto-generated method stub
		for(int end = array.length - 1; end > 0; end--) {
			for(int begin = 1; begin <= end; begin++) {
				if(cmp(begin, begin - 1) < 0) {
					swap(begin, begin - 1);
				}
			}
		}
	}

}
