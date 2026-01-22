import java.util.Iterator;

public interface MyList<E> extends Iterable<E> {
    void add(E e);
    void add(int index, E e);
    void clear();
    boolean contains(E e);
    E get(int index);
    int indexOf(E e);
    boolean isEmpty();
    int lastIndexOf(E e);
    boolean remove(E e);
    E remove(int index);
    E set(int index, E e);
    int size();

    boolean addAll(MyList<E> otherList);

    boolean removeAll(MyList<E> otherList);

    boolean retainAll(MyList<E> otherList);
}

abstract class MyAbstractList<E> implements MyList<E> {
    protected int size = 0;

    protected MyAbstractList() {}

    protected MyAbstractList(E[] objects) {
        for (E e : objects) add(e);
    }

    @Override
    public void add(E e) {
        add(size, e);
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean remove(E e) {
        int index = indexOf(e);
        if (index >= 0) {
            remove(index);
            return true;
        }
        return false;
    }

    @Override
    public boolean addAll(MyList<E> otherList) {
        boolean changed = false;
        for (E e : otherList) {
            add(e);
            changed = true;
        }
        return changed;
    }

    @Override
    public boolean removeAll(MyList<E> otherList) {
        boolean changed = false;
        for (E e : otherList) {
            while (contains(e)) {
                remove(e);
                changed = true;
            }
        }
        return changed;
    }

    @Override
    public boolean retainAll(MyList<E> otherList) {
        boolean changed = false;
        for (int i = size() - 1; i >= 0; i--) {
            if (!otherList.contains(get(i))) {
                remove(i);
                changed = true;
            }
        }
        return changed;
    }
}

class MyArrayList<E> extends MyAbstractList<E> {
    private static final int INITIAL_CAPACITY = 16;
    private E[] data = (E[]) new Object[INITIAL_CAPACITY];

    public MyArrayList() {}

    public MyArrayList(E[] objects) {
        for (E e : objects) add(e);
    }

    @Override
    public void add(int index, E e) {
        ensureCapacity();
        for (int i = size - 1; i >= index; i--) {
            data[i + 1] = data[i];
        }
        data[index] = e;
        size++;
    }

    private void ensureCapacity() {
        if (size >= data.length) {
            E[] newData = (E[]) new Object[size * 2 + 1];
            System.arraycopy(data, 0, newData, 0, size);
            data = newData;
        }
    }

    @Override
    public void clear() {
        data = (E[]) new Object[INITIAL_CAPACITY];
        size = 0;
    }

    @Override
    public boolean contains(E e) {
        return indexOf(e) >= 0;
    }

    @Override
    public E get(int index) {
        return data[index];
    }

    @Override
    public int indexOf(E e) {
        for (int i = 0; i < size; i++) {
            if (data[i].equals(e)) return i;
        }
        return -1;
    }

    @Override
    public int lastIndexOf(E e) {
        for (int i = size - 1; i >= 0; i--) {
            if (data[i].equals(e)) return i;
        }
        return -1;
    }

    @Override
    public E remove(int index) {
        E removed = data[index];
        for (int i = index; i < size - 1; i++) {
            data[i] = data[i + 1];
        }
        data[size - 1] = null;
        size--;
        return removed;
    }

    @Override
    public E set(int index, E e) {
        E old = data[index];
        data[index] = e;
        return old;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < size; i++) {
            sb.append(data[i]);
            if (i < size - 1) sb.append(", ");
        }
        sb.append("]");
        return sb.toString();
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            private int current = 0;
            public boolean hasNext() { return current < size; }
            public E next() { return data[current++]; }
            public void remove() { MyArrayList.this.remove(current); }
        };
    }
}