package com.captain.wds.degsinpattern.iterator.iterator;

public class ConcreteAggregate< T > implements MyList<T>{
    private T[] list;
    private int size=0;
    private int index=0;

    ConcreteAggregate() {
        list= (T[]) new Object[100];
    }

    @Override
    public void add(T obj) {
        list[index++]=obj;
        size++;
    }

    @Override
    public T get(int index) {
        if(index>size) {
            throw new ArrayIndexOutOfBoundsException();
        }
        return list[index];
    }

    @Override
    public IIterator iterator() {
        return  new ConcreteIterator(this);
    }

    @Override
    public int getSize() {
        return size;
    }
}
