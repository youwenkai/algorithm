package algorithm;

import algorithm.List.ArrayList;

public class main {

	public static void main(String[] args) {

		ArrayList<Object> list  = new ArrayList<>();
		list.add(new Person(10, "Jack"));
		list.add(null);
		list.add(new Person(15, "Rose"));
		list.add(null);
		list.add(new Person(12, "James"));
		list.add(null);

		System.out.println(list.indexOf(null));
		
		
		
		System.out.println(list);
	}

}
