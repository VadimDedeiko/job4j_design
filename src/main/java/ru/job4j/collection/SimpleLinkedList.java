package ru.job4j.collection;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class SimpleLinkedList<E> implements LinkedList<E> {
    private int size;
    private int modCount;
    Node<E> last;
    Node<E> first;

    private static class Node<E> {
        E item;
        Node<E> next;
        Node<E> prev;

        Node(Node<E> prev, E element, Node<E> next) {
            this.item = element;
            this.next = next;
            this.prev = prev;
        }

        public E getItem() {
            return item;
        }
    }

    @Override
    public void add(E value) {
        Node<E> elementLinked = last;
        Node<E> newNode = new Node<>(elementLinked, value, null);
        last = newNode;
        if (elementLinked == null) {
            first = newNode;
        } else {
            elementLinked.next = newNode;
        }
        size++;
        modCount++;
    }

    @Override
    public E get(int index) {
        Objects.checkIndex(index, size);
            Node<E> elementLinked = first;
            for (int i = 0; i < index; i++) {
                elementLinked = elementLinked.next;
                return elementLinked.item;
            }
        elementLinked = last;
        for (int i = size - 1; i > index; i--) {
            elementLinked = elementLinked.prev;
        }
        return elementLinked.item;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            private int expectedModCount = modCount;
            private Node<E> cursor = first;

            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                return cursor != null;
            }
            @Override
            public E next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                E result = cursor.item;
                cursor = cursor.next;
                return result;
            }
        };
    }
}