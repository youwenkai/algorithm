package leetcode.Heap;

public class _703_数据流中的第K大元素 {
	
	private int size;
	private int[] elements;
	private int k;
	
	public void KthLargest(int k, int[] nums) {
		elements = new int[10];
		size = 0;
		this.k = k;
		if(nums != null && nums.length != 0) {
			for(int i = 0; i < nums.length; i++) {
				addElement(nums[i]);
			}
		} 
    }
    
    public int add(int val) {
    	
    	addElement(val);
    	return elements[0];
    }
    
    private void addElement(int element) {
    	ensureCapcity(size + 1);
    	if(size < k) {
    		elements[size++] = element;
    		siftUp(size - 1);
    	} else if(element > elements[0]) {
    		replace(element);
    	}
    }
    
    private void replace(int element) {
    	if(size == 0) {
    		elements[0] = element;
    		size++;
    	} else {
    		elements[0] = element;
    		siftDown(0);
    	}
    }
    
    
    private void siftUp(int index) {
    	int element = elements[index];
    	while (index > 0) {
			int parentIndex = (index - 1) >> 1;
			int parent = elements[parentIndex];
			
			if(parent <= element) break;
			
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
}
