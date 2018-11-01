package com.captain.wds.degsinpattern.iterator.iterator;
//迭代器角色
public interface IIterator<T> {
    boolean hasNext();
    T next();

    boolean remove();
}
