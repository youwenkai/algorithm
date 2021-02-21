package algorithm.Sort.Compare;

import algorithm.Sort.AbstractSort;

/**
 * 堆排序(对选择排序的一种优化)
 * 
 * 对序列进行原地建堆
 * 重复执行以下操作 知道堆的元素数量为1
 * 
 * 交换堆顶元素与尾元素
 * 堆的元素数量减1
 * 对0的位置进行一次siftDown操作
 * 
 * o(nlogn)
 * */ 
public class HeapSort<T extends Comparable<T>> extends AbstractSort<T> {
	private int heapSize;
	
	@Override
	protected void sort() {
		// 原地建堆
		heapSize = array.length;
		for(int begin = (heapSize >> 1) - 1; begin >= 0; begin--) {
			siftDown(begin);
		}
		
		// 每次把最大值和末尾元素交换 然后容量减1
		// 保证了尾元素是最大值
		// 然后把剩余的元素进行堆性质修复 实现大顶堆 重复操作
		while (heapSize > 1) {
			//交换堆顶元素和尾元素
			swap(0, --heapSize);
			
			//对0位置进行siftDown(恢复堆的性质)
			siftDown(0);
		}
	}
	
	private void siftDown(int index) {
		T element = array[index];
		int half = heapSize >> 1;
		while (index < half) {
			int childIndex = (index << 1) + 1;
			T child = array[childIndex];
			
			int rightIndex = childIndex + 1;
			if(rightIndex < heapSize && 
					cmp(array[rightIndex], child) > 0) {
				child = array[childIndex = rightIndex];
			}
			
			if(cmp(child, element) <= 0) break;
			
			array[index] = child;
			index = childIndex;
		}
		array[index] = element;
	}

}
