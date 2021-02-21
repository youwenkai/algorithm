package algorithm.Sort;

import java.text.DecimalFormat;

public abstract class AbstractSort<T extends Comparable<T>> implements Comparable<AbstractSort<T>> {
	protected T[] array;
	
	// 比较次数
	private int compareCount;
	// 交换次数
	private int swapCount;
	// 消耗时间
	private long time;
	
	private DecimalFormat fmt = new DecimalFormat("#.00");
	
	public void sort(T[] array) {
		if(array == null || array.length < 2) return;
		
		this.array = array;
		
		long begin = System.currentTimeMillis();
		sort();
		time = System.currentTimeMillis() - begin;
	}
	
	@Override
	public int compareTo(AbstractSort<T> o) {
		int result = (int)(time - o.time);
		if(result != 0) return result;
		
		result = compareCount - o.compareCount;
		if(result != 0) return result;
		
		return swapCount - o.swapCount;
	}
	
	// 排序算法
	protected abstract void sort();
	
	/**
	 * 返回值等于0，代表 array[i1] == array[i2]
	 * 返回值小于0，代表 array[i1] < array[i2]
	 * 返回值大于0，代表 array[i1] > array[i2]
	 * 
	 * @param i1 索引下标
	 * @param i2 索引下标
	 */
	protected int cmp(int i1, int i2) {
		compareCount++;
		return array[i1].compareTo(array[i2]);
	}
	
	protected int cmp(T t1, T t2) {
		compareCount++;
		return t1.compareTo(t2);
	}
	
	/**
	 * 交换
	 * @param i1 交换节点下标
	 * @param i2 交换节点下标
	 * */
	protected void swap(int i1, int i2) {
		swapCount++;
		T temp = array[i1];
		array[i1] = array[i2];
		array[i2] = temp;
	}
	
	@Override
	public String toString() {
		String timeString = "耗时: " + (time / 1000.0) + "s(" + time + "ms)";
		String compareCountString = "比较 " + numberString(compareCount);
		String swapString = "交换 " + numberString(swapCount);
//		String stableString = "稳定性 " + 
		
		return "【" + getClass().getSimpleName() + "】\n" 
//							+ stableStr + " \t"
							+ timeString + " \t"
							+ compareCountString + "\t "
							+ swapString + "\n"
							+ "------------------------------------------------------------------";
	}
	
	private String numberString(int number) {
		if (number < 10000) return "" + number;
		
		if (number < 100000000) return fmt.format(number / 10000.0) + "万";
		return fmt.format(number / 100000000.0) + "亿";
	}
	
//	private boolean isStable() {
//		if (this instanceof RadixSort) return true;
//		if (this instanceof CountingSort) return true;
//		if (this instanceof ShellSort) return false;
//		if (this instanceof SelectionSort) return false;
//		Student[] students = new Student[20];
//		for (int i = 0; i < students.length; i++) {
//			students[i] = new Student(i * 10, 10);
//		}
//		sort((T[]) students);
//		for (int i = 1; i < students.length; i++) {
//			int score = students[i].score;
//			int prevScore = students[i - 1].score;
//			if (score != prevScore + 10) return false;
//		}
//		return true;
//	}
}
