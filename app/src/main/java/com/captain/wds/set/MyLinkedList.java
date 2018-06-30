package com.captain.wds.set;

import android.support.annotation.NonNull;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Created by wudongsheng on 2018/6/17.
 */

public class MyLinkedList<AnyType> implements Iterable<AnyType> {

	private int				theSize		= 0;

	private int				modCount	= 0;

	private Node<AnyType>	beginMarker;

	private Node<AnyType>	endMarker;

	private static class Node<AnyType> {

		public AnyType			data;

		public Node<AnyType>	prev;

		public Node<AnyType>	next;

		public Node(AnyType data, Node<AnyType> prev, Node<AnyType> next) {
			this.data = data;
			this.prev = prev;
			this.next = next;
		}
	}

	public MyLinkedList() {

		doClear();
	}

	public void clear() {
		doClear();
	}

	public int size() {
		return theSize;
	}

	public boolean isEmpty() {
		return size() == 0;
	}

	public boolean add(AnyType anyType) {
		add(size(), anyType);

		return true;
	}

	public void add(int index, AnyType anyType) {
		addBefore(getNode(index, 0, size()), anyType);
	}

	public AnyType get(int index) {
		return getNode(index).data;
	}

	public AnyType set(int index, AnyType newVal) {
		Node<AnyType> p = getNode(index);
		AnyType oldVal = p.data;
		p.data = newVal;
		return oldVal;
	}

	public AnyType remove(int index) {
		return remove(getNode(index));
	}

	private AnyType remove(Node<AnyType> p) {
		Node<AnyType> next = p.next;
		Node<AnyType> prev = p.prev;
		next.prev = prev;
		prev.next = next;

		theSize--;
		modCount++;

		return p.data;

	}

	private void addBefore(Node<AnyType> p, AnyType x) {
		Node<AnyType> newNode = new Node<AnyType>(x, p.prev, p);
		p.prev = newNode;
		newNode.prev.next = newNode;
		theSize++;
		modCount++;

	}

	private Node<AnyType> getNode(int index) {
		return getNode(index, 0, size() - 1);
	}

	private Node<AnyType> getNode(int index, int lower, int upper) {
		Node<AnyType> p;

		if (index < lower || index > upper) throw new IndexOutOfBoundsException();
		if (index < size() / 2) {
			p = beginMarker.next;
			for (int i = 0; i < index; i++) {
				p = p.next;
			}
		} else {
			p = endMarker.prev;
			for (int i = 0; i < index; i++) {
				p = p.prev;

			}
		}
		return p;

	}

	private void doClear() {
		beginMarker = new Node<AnyType>(null, null, null);
		endMarker = new Node<AnyType>(null, beginMarker, null);

		beginMarker.next = endMarker;
		theSize = 0;
		modCount++;

	}

	@NonNull @Override public Iterator<AnyType> iterator() {
		return new LinkedListIterator();
	}

	private class LinkedListIterator implements Iterator<AnyType> {

		private Node<AnyType>	current				= beginMarker.next;

		private int				expectedModCount	= modCount;

		private boolean			okToRemove			= false;

		@Override public boolean hasNext() {
			return current != endMarker;
		}

		@Override public AnyType next() {
			if (modCount != expectedModCount) throw new ConcurrentModificationException();
			if (!hasNext()) throw new NoSuchElementException();
			AnyType nextItem = current.data;
			current = current.next;
			okToRemove = true;
			return nextItem;
		}

		@Override public void remove() {
			if (modCount != expectedModCount) throw new ConcurrentModificationException();
			if (!okToRemove) throw new IllegalStateException();

			MyLinkedList.this.remove(current.prev);
			expectedModCount = modCount;
			okToRemove = false;

		}
	}

}
