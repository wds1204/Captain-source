package com.captain.wds.degsinpattern.iterator.iterator;

//具体的迭代器
public class ConcreteIterator<T > implements IIterator<T> {

    private final MyList<T> list;
    private int index;
    ConcreteIterator(MyList<T> list) {
        super();
        this.list=list;
    }

    @Override
    public boolean hasNext() {
        return list.getSize()>index?true:false;
    }

    @Override
    public T next() {
        if(!hasNext())
            throw new NullPointerException();
        return list.get(index++);
    }

    @Override
    public boolean remove() {
        return false;
    }
}
