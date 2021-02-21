package core.Set;

import algorithm.List.SingleLinkedList;
import core.List.List;

public class ListSet<E> implements Set<E> {
	private  List<E> list = new SingleLinkedList<>();
	
	@Override
	public int size() {
		return list.size();
	}

	@Override
	public boolean isEmpty() {
		return list.isEmpty();
	}

	@Override
	public void clear() {
		list.clear();
	}

	@Override
	public boolean contains(E element) {
		return list.contains(element);
	}

	@Override
	public void add(E element) {
		int index = list.indexOf(element);
		if(index != List.ELEMENT_NOT_FOUND) {
			//有此对象 覆盖
			list.set(index, element);
		} else {
			list.add(element);
		}
	}

	@Override
	public void remove(E element) {
		int index = list.indexOf(element);
		if(index != List.ELEMENT_NOT_FOUND) {
			list.remove(index);
		}
	}

	@Override
	public void traversal(Visitor<E> visitor) {
		if(visitor == null) return;
		int size = list.size();
		for(int i = 0; i < size; i++) {
			if(visitor.visit(list.get(i))) return;
		}
	}

}
