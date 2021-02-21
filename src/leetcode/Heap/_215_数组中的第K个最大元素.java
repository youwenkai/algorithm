package leetcode.Heap;
/**
 * 在未排序的数组中找到第 k 个最大的元素
 * 请注意，你需要找的是数组排序后的第 k 个最大的元素，而不是第 k 个不同的元素
 * */
public class _215_数组中的第K个最大元素 {
	public int findKthLargest(int[] nums, int k) {
		
		MinHeap heap = new MinHeap();
		
		for(int i = 0; i < nums.length; i++) {
			if(heap.size() < k) {
				heap.add(nums[i]);
			} else if(nums[i] > heap.get()) {
				heap.replace(nums[i]);
			}
		}	
		
		return heap.get();
    }
	
	private static class MinHeap {
		private int size;
		private int[] elements;
	
		public MinHeap() {
			elements = new int[10];
		}
		
		public int size() {
			return size;
		}
		
		public int get() {
			return elements[0];
		}
		
		public void add(int element) {
			ensureCapcity(size + 1);
			// 添加到尾部
			elements[size++] = element;
			
			// 上滤
			siftUp(size - 1);
		}
		
		public void replace(int element) {
			
			if(size == 0) {
				elements[0] = element;
				size++;
			} else {
				elements[0] = element;
				// 下滤
				siftDown(0);
			}
		}
		
		private void siftUp(int index) {
			int element = elements[index];
			while (index > 0) {
				int parentIndex = (index - 1) >> 1;
				int parent = elements[parentIndex];
				
				if(parent <= element) break;
				
				//将父元素存储在index位置
				elements[index] = parent;
				index = parentIndex;
			}
			elements[index] = element;
		}
		
		private void siftDown(int index) {
			int element = elements[index];
			
			int half = size >> 1;
			while (index < half) {
				int childIndex = (index << 1) + 1;
				int child = elements[childIndex];
				
				int rightIndex = childIndex + 1;
				if(rightIndex < size && 
						elements[rightIndex] < child) {
					child = elements[childIndex = rightIndex];
				}
				
				if(child >= element) break;
				
				elements[index] = child;
				index = childIndex;
			}
			elements[index] = element;
		}
		
		private void ensureCapcity(int capcity) {
			int oldCapcity = elements.length;
			if(oldCapcity >= capcity) return;
			
			int newCapcity = oldCapcity + (oldCapcity >> 1);
			int[] newElements = new int[newCapcity];
			for(int i = 0; i < size; i++) {
				newElements[i] = elements[i];
			}
			elements = newElements;
		}
		
		@Override
		public String toString() {
			StringBuilder s = new StringBuilder();
			for (int i = 0; i < elements.length; i++) {
				 s.append(elements[i]).append("_");
			}
			return s.toString();
		}
	}
}
