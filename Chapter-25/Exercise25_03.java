import java.util.Stack;
import java.util.Collection;

public class Exercise25_03 {
    public static void main(String[] args) {

        BST<String> tree = new BST<>();
        tree.insert("George");
        tree.insert("Michael");
        tree.insert("Tom");
        tree.insert("Adam");
        tree.insert("Jones");
        tree.insert("Peter");
        tree.insert("John");
        tree.insert("Daniel");

        System.out.println("Non-recursive inorder traversal:");
        tree.nonRecursiveInorder();
    }

    public static class BST<E extends Comparable<E>> extends AbstractTree<E> {
        protected TreeNode<E> root;
        protected int size = 0;

        public BST() {
        }

        public boolean search(E e) {
            TreeNode<E> current = root;
            while (current != null) {
                if (e.compareTo(current.element) < 0)
                    current = current.left;
                else if (e.compareTo(current.element) > 0)
                    current = current.right;
                else
                    return true;
            }
            return false;
        }

        public boolean insert(E e) {
            if (root == null)
                root = new TreeNode<>(e);
            else {
                TreeNode<E> parent = null;
                TreeNode<E> current = root;
                while (current != null) {
                    if (e.compareTo(current.element) < 0) {
                        parent = current;
                        current = current.left;
                    } else if (e.compareTo(current.element) > 0) {
                        parent = current;
                        current = current.right;
                    } else
                        return false; 
                }
                if (e.compareTo(parent.element) < 0)
                    parent.left = new TreeNode<>(e);
                else
                    parent.right = new TreeNode<>(e);
            }
            size++;
            return true;
        }

        public int getSize() {
            return size;
        }

        public void nonRecursiveInorder() {
            Stack<TreeNode<E>> stack = new Stack<>();
            TreeNode<E> current = root;

            while (current != null || !stack.isEmpty()) {
                while (current != null) {
                    stack.push(current);
                    current = current.left;
                }

                current = stack.pop();
                System.out.print(current.element + " ");
                current = current.right;
            }
            System.out.println();
        }

        @Override
        public void clear() {
            root = null;
            size = 0;
        }

        public static class TreeNode<E> {
            E element;
            TreeNode<E> left;
            TreeNode<E> right;

            public TreeNode(E e) {
                element = e;
            }
        }
    }
}

abstract class AbstractTree<E> implements Tree<E> {
    @Override
    public boolean isEmpty() {
        return getSize() == 0;
    }
}

interface Tree<E> extends Collection<E> {
    boolean search(E e);
    boolean insert(E e);
    int getSize();

    @Override
@SuppressWarnings("unchecked")
default boolean contains(Object e) {
    return search((E) e);
}

    @Override
    default boolean add(E e) {
        return insert(e);
    }

    @Override
    default int size() {
        return getSize();
    }

    @Override
    default boolean isEmpty() {
        return size() == 0;
    }

    @Override
    default void clear() {
    }

    @Override
    default java.util.Iterator<E> iterator() {
        return null;
    }

    @Override
    default boolean remove(Object e) {
        return false;
    }

    @Override
    default boolean containsAll(Collection<?> c) {
        return false;
    }

    @Override
    default boolean addAll(Collection<? extends E> c) {
        return false;
    }

    @Override
    default boolean removeAll(Collection<?> c) {
        return false;
    }

    @Override
    default boolean retainAll(Collection<?> c) {
        return false;
    }

    @Override
    default Object[] toArray() {
        return null;
    }

    @Override
    default <T> T[] toArray(T[] a) {
        return null;
    }
}