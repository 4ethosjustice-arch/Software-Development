import java.util.ListIterator;

public class TwoWayLinkedList<E> extends java.util.AbstractSequentialList<E> {
    private Node<E> head, tail;
    private int size;

    public TwoWayLinkedList() {
    }

    public TwoWayLinkedList(E[] objects) {
        for (E e : objects) add(e);
    }

    public E getFirst() {
        return (size == 0) ? null : head.element;
    }

    public E getLast() {
        return (size == 0) ? null : tail.element;
    }

    public void addFirst(E e) {
        Node<E> newNode = new Node<>(e);
        newNode.next = head;
        newNode.previous = null;
        if (head != null) head.previous = newNode;
        head = newNode;
        if (tail == null) tail = head;
        size++;
    }

    public void addLast(E e) {
        Node<E> newNode = new Node<>(e);
        newNode.previous = tail;
        newNode.next = null;
        if (tail != null) tail.next = newNode;
        tail = newNode;
        if (head == null) head = tail;
        size++;
    }

    @Override
    public void add(int index, E e) {
        if (index == 0) {
            addFirst(e);
        } else if (index >= size) {
            addLast(e);
        } else {
            Node<E> current = head;
            for (int i = 0; i < index; i++) current = current.next;

            Node<E> newNode = new Node<>(e);
            newNode.next = current;
            newNode.previous = current.previous;

            current.previous.next = newNode;
            current.previous = newNode;
            size++;
        }
    }

    public E removeFirst() {
        if (size == 0) return null;
        Node<E> temp = head;
        head = head.next;
        if (head != null) head.previous = null;
        else tail = null;
        size--;
        return temp.element;
    }

    public E removeLast() {
        if (size == 0) return null;
        Node<E> temp = tail;
        tail = tail.previous;
        if (tail != null) tail.next = null;
        else head = null;
        size--;
        return temp.element;
    }

    @Override
    public E remove(int index) {
        if (index < 0 || index >= size) return null;
        if (index == 0) return removeFirst();
        if (index == size - 1) return removeLast();

        Node<E> current = head;
        for (int i = 0; i < index; i++) current = current.next;

        current.previous.next = current.next;
        current.next.previous = current.previous;
        size--;
        return current.element;
    }

    @Override
    public E set(int index, E e) {
        Node<E> current = head;
        for (int i = 0; i < index; i++) current = current.next;
        E old = current.element;
        current.element = e;
        return old;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public ListIterator<E> listIterator(int index) {
        return new LinkedListIterator(index);
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder("[");
        Node<E> current = head;
        while (current != null) {
            result.append(current.element);
            if (current.next != null) result.append(", ");
            current = current.next;
        }
        result.append("]");
        return result.toString();
    }

    public void clear() {
        head = tail = null;
        size = 0;
    }

    public boolean contains(Object e) {
        Node<E> current = head;
        while (current != null) {
            if (current.element.equals(e)) return true;
            current = current.next;
        }
        return false;
    }

    public E get(int index) {
        Node<E> current = head;
        for (int i = 0; i < index; i++) current = current.next;
        return current.element;
    }

    public int indexOf(Object e) {
        Node<E> current = head;
        int index = 0;
        while (current != null) {
            if (current.element.equals(e)) return index;
            current = current.next;
            index++;
        }
        return -1;
    }

    public int lastIndexOf(Object e) {
        Node<E> current = tail;
        int index = size - 1;
        while (current != null) {
            if (current.element.equals(e)) return index;
            current = current.previous;
            index--;
        }
        return -1;
    }

    private class LinkedListIterator implements ListIterator<E> {
        private Node<E> current;
        private int nextIndex;

        public LinkedListIterator(int index) {
            if (index < 0 || index > size)
                throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
            current = head;
            nextIndex = 0;
            for (int i = 0; i < index; i++) {
                current = current.next;
                nextIndex++;
            }
        }

        public boolean hasNext() { return current != null; }
        public boolean hasPrevious() { return (current != null && current.previous != null) || nextIndex > 0; }

        public E next() {
            E e = current.element;
            current = current.next;
            nextIndex++;
            return e;
        }

        public E previous() {
            if (current == null) { // at end
                current = tail;
            } else {
                current = current.previous;
            }
            nextIndex--;
            return current.element;
        }

        public int nextIndex() { return nextIndex; }
        public int previousIndex() { return nextIndex - 1; }

        public void remove() { throw new UnsupportedOperationException(); }
        public void set(E e) { current.element = e; }
        public void add(E e) { throw new UnsupportedOperationException(); }
    }

    public static class Node<E> {
        E element;
        Node<E> next;
        Node<E> previous;
        public Node(E e) { element = e; }
    }
}