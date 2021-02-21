package algorithm.Sort.Compare;

import algorithm.Sort.AbstractSort;


/**
 * 
 * 如果序列已经完全有序，可以提前结束
 * */
public class BubbleSort_1<T extends Comparable<T>> extends AbstractSort<T> {

	@Override
	protected void sort() {
		for(int end = array.length - 1; end > 0; end--) {
			boolean sorted = true;
			for(int begin = 1; begin <= end; begin++) {
				if(cmp(begin, begin - 1) < 0) {
					swap(begin, begin - 1);
					//交换了 就说明 是无序的
					sorted = false;
				}
			}
			if(sorted) break;
		}
	}
	
}
