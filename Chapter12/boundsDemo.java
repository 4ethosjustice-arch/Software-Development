import java.util.Scanner;

public class boundsDemo {
    public static void main(String[] args) {
        int[] numbers = new int[100];
        for (int i = 0; i < numbers.length; i++) {
            numbers[i] = (int)(Math.random() * 1000);
        }

        Scanner input = new Scanner(System.in);
        System.out.print("Enter an index (0 to 99): ");
        int index = input.nextInt();

        if (index >= 0 && index < numbers.length) {
            System.out.println("Value at index " + index + ": " + numbers[index]);
        } else {
            System.out.println("Out of Bounds");
        }

        input.close();
    }
}