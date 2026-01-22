import java.util.LinkedList;

public class GenericQueue<E> extends LinkedList<E> {
    public void enqueue(E element) {
        addLast(element);
    }

    public E dequeue() {
        return isEmpty() ? null : removeFirst();
    }

    public E peek() {
        return isEmpty() ? null : getFirst();
    }

    public static void main(String[] args) {
        GenericQueue<Integer> queue = new GenericQueue<>();

        System.out.println("Enqueue 10, 20, 30");
        queue.enqueue(10);
        queue.enqueue(20);
        queue.enqueue(30);
        System.out.println("Queue: " + queue);

        System.out.println("\nDequeue: " + queue.dequeue());
        System.out.println("Queue: " + queue);

        System.out.println("\nEnqueue 40");
        queue.enqueue(40);
        System.out.println("Queue: " + queue);

        System.out.println("\nPeek: " + queue.peek());
        System.out.println("Queue: " + queue);

        System.out.println("\nDequeue all elements:");
        while (!queue.isEmpty()) {
            System.out.println("Dequeued: " + queue.dequeue() + ", Queue: " + queue);
        }
    }
}