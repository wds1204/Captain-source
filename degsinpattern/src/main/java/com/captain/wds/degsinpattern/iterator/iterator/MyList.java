package com.captain.wds.degsinpattern.iterator.iterator;

//定义容器角色
public interface MyList<T> {
     void add(T obj);
     T get(int index);
     IIterator iterator();
     int getSize();
}
