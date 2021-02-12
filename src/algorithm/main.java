package algorithm;

import algorithm.List.Circle.CircleLinkedList;
import algorithm.List.SingleList.SingleLinkedList2;

public class main {

	public static void main(String[] args) {

//		ArrayList<Object> list  = new ArrayList<>();
//		list.add(new Person(10, "Jack"));
//		list.add(null);
//		list.add(new Person(15, "Rose"));
//		list.add(null);
//		list.add(new Person(12, "James"));
//		list.add(null);
//
//		System.out.println(list.indexOf(null));
		
//		SingleLinkedList2<Object> list = new SingleLinkedList2<>();
//		
//		list.add(new Person(10, "Jack"));
//		list.add(null);
//		list.add(new Person(15, "Rose"));
//		list.add(null);
//		list.add(new Person(12, "James"));
//		list.add(null);
		
		
//		System.out.println(list);
		
//		list.remove(3);
		
//		System.out.println(list.set(0, null));

//		System.out.println(list.get(list.size() -  1));
//		System.out.println(list);
//		
//		list.clear();
		
		CircleLinkedList<Object> list = new CircleLinkedList<>();
		
		list.add(new Person(10, "Jack"));
		list.add(null);
		list.add(new Person(15, "Rose"));
//		list.add(null);
//		list.add(new Person(12, "James"));
//		list.add(null);
		
//		list.remove(1);
		
//		System.out.println(list.set(0, 2));
//		System.out.println(list.get(1));
//		System.out.println(list.indexOf(new Person(10, "Jack")));
		
		
		System.out.println(list);
	}

}
