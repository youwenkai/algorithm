package algorithm;

//import algorithm.List.ArrayList;
import algorithm.List.SingleLinkedList;

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
		
		SingleLinkedList<Object> list = new SingleLinkedList<>();
		
		list.add(new Person(10, "Jack"));
		list.add(null);
		list.add(new Person(15, "Rose"));
		list.add(null);
		list.add(new Person(12, "James"));
		list.add(null);
		
		
		System.out.println(list);
		
//		list.remove(3);
		
//		System.out.println(list.set(0, null));

		System.out.println(list.get(list.size() -  1));
		System.out.println(list);
	}

}