public class Exercise25_01 {
    public static void main(String[] args) {
        new Exercise25_01();
    }

    public Exercise25_01() {
        BinaryTree<String> tree = new BinaryTree<>();
        System.out.print("The height of tree is " + tree.height());

        tree.insert("Red");
        System.out.print("\nThe height of tree is " + tree.height());

        tree.insert("Green");
        System.out.print("\nThe height of tree is " + tree.height());

        BinaryTree<String> tree1 = new BinaryTree<>(new String[]{
                "Tom", "George", "Jean", "Jane", "Kevin", "Peter",
                "Susan", "Jen", "Kim", "Michael", "Michelle"
        });

        System.out.print("\nThe breadth-first traversal for tree1 is ");
        tree1.breadthFirstTraversal();
        System.out.print("\nThe height of tree1 is " + tree1.height());

        BinaryTree<Integer> tree2 =
                new BinaryTree<>(new Integer[]{50, 45, 35, 48, 59, 51, 58});

        System.out.print("\nThe breadth-first traversal for tree2 is ");
        tree2.breadthFirstTraversal();
        System.out.print("\nThe height of tree2 is " + tree2.height());
    }

    public class BinaryTree<E extends Comparable<E>> extends AbstractTree<E> {
        protected TreeNode root;
        protected int size = 0;

        public BinaryTree() {}

        public BinaryTree(E[] objects) {
            for (E object : objects)
                insert(object);
        }

        public int height() {
            return height(root);
        }

        private int height(TreeNode node) {
            if (node == null)
                return 0;
            return 1 + Math.max(height(node.left), height(node.right));
        }

        public void breadthFirstTraversal() {
            if (root == null)
                return;

            java.util.LinkedList<TreeNode> queue = new java.util.LinkedList<>();
            queue.add(root);

            while (!queue.isEmpty()) {
                TreeNode current = queue.removeFirst();
                System.out.print(current.element + " ");

                if (current.left != null)
                    queue.add(current.left);
                if (current.right != null)
                    queue.add(current.right);
            }
        }

        public boolean search(E e) {
            TreeNode current = root;
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
                root = new TreeNode(e);
            else {
                TreeNode parent = null;
                TreeNode current = root;

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
                    parent.left = new TreeNode(e);
                else
                    parent.right = new TreeNode(e);
            }
            size++;
            return true;
        }

        public class TreeNode {
            E element;
            TreeNode left;
            TreeNode right;

            public TreeNode(E e) {
                element = e;
            }
        }

        public int getSize() {
            return size;
        }
    }

    public abstract class AbstractTree<E extends Comparable<E>> implements Tree<E> {
        public boolean isEmpty() {
            return getSize() == 0;
        }
    }

    public interface Tree<E extends Comparable<E>> {
        boolean search(E e);
        boolean insert(E e);
        int getSize();
    }
}