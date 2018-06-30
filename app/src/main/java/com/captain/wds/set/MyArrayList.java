package com.captain.wds.set;

/**
 * Created by wudongsheng on 2018/6/10.
 */

public class MyArrayList<AnyType> implements Iterable<AnyType>
{
    private static final int DEFAULT_CAPACITY = 10;

    private int theSize;
    private AnyType[] theItems;

    public int size()
    {
        return theSize;
    }

    public boolean isEmpty()
    {
        return size() == 0;
    }

    //调整容量符合大小
    public void trimToSize()
    {
        ensureCapacity(size());
    }
    //确保数组大小足够大
    public void ensureCapacity(int newCapacity)
    {
        if(newCapacity < theSize)
            return;

        //复制数据到新数组中
        AnyType[] old = theItems;
        theItems = (AnyType[]) new Object[newCapacity];
        for(int i = 0; i <size(); i++)
        {
            theItems[i] = old[i];
        }
    }
    public AnyType get(int index)
    {
        if(index < 0 || index >= size())
        {
            throw new ArrayIndexOutOfBoundsException();
        }
        return theItems[index];
    }

    public AnyType set(int index, AnyType newVal)
    {
        if(index < 0 || index >= size())
        {
            throw new ArrayIndexOutOfBoundsException();
        }
        AnyType old = theItems[index];
        theItems[index] = newVal;
        return old;
    }

    public void add(int index, AnyType x)
    {
        //数组不够大，则扩大数组
        if(theItems.length == size())
        {
            ensureCapacity(size()*2 + 1);
        }
        //从index开始，元素往后移动一位
        for(int i = theSize; i > index; i--)
        {
            theItems[i] = theItems[i - 1];
        }
        //index位置赋值x
        theItems[index] = x;
        theSize++;
    }

    public AnyType remove(int index)
    {
        AnyType removedItem = theItems[index];
        for(int i = index; i < size(); i++)
        {
            //从index位置开始，所有元素都往前移动一位
            theItems[i] = theItems[i + 1];
        }
        theSize--;
        return removedItem;
    }

    public java.util.Iterator<AnyType> iterator()
    {
        return new ArrayListIterator<AnyType>();
    }

    private  class ArrayListIterator<AnyType> implements java.util.Iterator<AnyType>
    {
        private int current = 0;

        public boolean hasNext()
        {
            return current < MyArrayList.this.size();
        }

        public AnyType next()
        {
            return (AnyType) MyArrayList.this.theItems[current++];
        }

        public void remove()
        {
            //防止迭代器的remove与MyArrayList的remove冲突
            MyArrayList.this.remove(--current);
        }
    }
};
