package leetcode.Heap;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class _451_根据字符出现频率排序 {
//给定一个字符串，请将字符串里的字符按照出现的频率降序排列。
	
	public String frequencySort(String s) {
		Map<Character, Integer> map = new HashMap<>();
		for(int i = 0; i < s.length(); i++) {
			char ch = s.charAt(i);
			map.put(ch, map.getOrDefault(ch, 0) + 1);
		}
		
		// 构建大顶堆
		PriorityQueue<Character> maxHeap = new PriorityQueue<>((o1, o2) -> map.get(o2) - map.get(o1));
		maxHeap.addAll(map.keySet());
		
		StringBuilder sb = new StringBuilder();
		while (!maxHeap.isEmpty()) {
			char ch = maxHeap.poll();
			int count = map.get(ch);
			for(int i = 0; i < count; i++) {
				sb.append(ch);
			}
		}
		
		return sb.toString();
    }
}
