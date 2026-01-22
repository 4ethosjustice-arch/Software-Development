import java.util.concurrent.RecursiveAction;
import java.util.concurrent.ForkJoinPool;
import java.util.Arrays;

public class ParallelMergeSortGeneric {

    public static void main(String[] args) {
        final int SIZE = 7000000;
        Integer[] list1 = new Integer[SIZE];
        Integer[] list2 = new Integer[SIZE];

        for (int i = 0; i < SIZE; i++)
            list1[i] = list2[i] = (int)(Math.random() * 10000000);

        long startTime = System.currentTimeMillis();
        parallelMergeSort(list1);
        long endTime = System.currentTimeMillis();
        System.out.println("Parallel time with " + 
            Runtime.getRuntime().availableProcessors() + 
            " processors is " + (endTime - startTime) + " milliseconds");

        startTime = System.currentTimeMillis();
        MergeSort.mergeSort(list2);
        endTime = System.currentTimeMillis();
        System.out.println("Sequential time is " + (endTime - startTime) + " milliseconds");
    }

    public static <E extends Comparable<E>> void parallelMergeSort(E[] list) {
        RecursiveAction mainTask = new SortTask<>(list);
        ForkJoinPool pool = new ForkJoinPool();
        pool.invoke(mainTask);
    }

    private static class SortTask<E extends Comparable<E>> extends RecursiveAction {
        private final int THRESHOLD = 500;
        private E[] list;

        SortTask(E[] list) {
            this.list = list;
        }

        @Override
        protected void compute() {
            if (list.length < THRESHOLD)
                Arrays.sort(list);
            else {
                int mid = list.length / 2;
                E[] firstHalf = Arrays.copyOfRange(list, 0, mid);
                E[] secondHalf = Arrays.copyOfRange(list, mid, list.length);

                invokeAll(new SortTask<>(firstHalf), new SortTask<>(secondHalf));
                merge(firstHalf, secondHalf, list);
            }
        }

        private void merge(E[] list1, E[] list2, E[] temp) {
            int i = 0, j = 0, k = 0;
            while (i < list1.length && j < list2.length) {
                if (list1[i].compareTo(list2[j]) < 0) temp[k++] = list1[i++];
                else temp[k++] = list2[j++];
            }
            while (i < list1.length) temp[k++] = list1[i++];
            while (j < list2.length) temp[k++] = list2[j++];
        }
    }
}

class MergeSort {
    public static <E extends Comparable<E>> void mergeSort(E[] list) {
        if (list.length > 1) {
            int mid = list.length / 2;
            E[] firstHalf = Arrays.copyOfRange(list, 0, mid);
            E[] secondHalf = Arrays.copyOfRange(list, mid, list.length);

            mergeSort(firstHalf);
            mergeSort(secondHalf);
            merge(firstHalf, secondHalf, list);
        }
    }

    private static <E extends Comparable<E>> void merge(E[] list1, E[] list2, E[] temp) {
        int i = 0, j = 0, k = 0;
        while (i < list1.length && j < list2.length) {
            if (list1[i].compareTo(list2[j]) < 0) temp[k++] = list1[i++];
            else temp[k++] = list2[j++];
        }
        while (i < list1.length) temp[k++] = list1[i++];
        while (j < list2.length) temp[k++] = list2[j++];
    }
}