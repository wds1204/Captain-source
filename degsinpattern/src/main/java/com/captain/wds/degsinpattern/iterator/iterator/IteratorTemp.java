package com.captain.wds.degsinpattern.iterator.iterator;

public class IteratorTemp {

    public static void main(String[] args) {
        MyList<Integer> list = new ConcreteAggregate();

        list.add(111);
        list.add(222);
        list.add(333);
        list.add(444);

        IIterator<Integer> iterator = list.iterator();

        while (iterator.hasNext()) {
            Integer next = iterator.next();
            System.out.println("next======" + next);
        }

    }
}
