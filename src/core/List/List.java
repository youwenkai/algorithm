package core.List;

public interface List<E> {
	static final int ELEMENT_NOT_FOUND  = -1;
	
	int size();
	
	boolean isEmpty();
	
	void add(E element);
	void add(int index, E element);
	
	E remove(int index);
	
	E get(int index);
	E set(int index, E element);
	
	boolean contains(E element);
	
	int indexOf(E element);
	
	void clear();
}
