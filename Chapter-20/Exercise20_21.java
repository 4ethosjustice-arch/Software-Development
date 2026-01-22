import java.util.Comparator;

public class Exercise20_21 {
    public static void main(String[] args) {
        GeometricObject[] list = {
            new Circle(5), new Rectangle(4, 5),
            new Circle(5.5), new Rectangle(2.4, 5), new Circle(0.5),
            new Rectangle(4, 65), new Circle(4.5), new Rectangle(4.4, 1),
            new Circle(6.5), new Rectangle(4, 5)
        };

        selectionSort(list, new GeometricObjectComparator());

        for (GeometricObject obj : list) {
            System.out.println(obj.getArea() + " ");
        }
    }

    public static <E> void selectionSort(E[] list, Comparator<? super E> comparator) {
        for (int i = 0; i < list.length - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < list.length; j++) {
                if (comparator.compare(list[j], list[minIndex]) < 0) {
                    minIndex = j;
                }
            }
            E temp = list[i];
            list[i] = list[minIndex];
            list[minIndex] = temp;
        }
    }
}