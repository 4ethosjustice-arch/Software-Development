import java.io.*;
import java.util.*;

public class Exercise12_15 {
    public static void main(String[] args) throws IOException {
        File file = new File("Exercise12_15.txt");

        if (!file.exists()) {
            PrintWriter output = new PrintWriter(file);
            for (int i = 0; i < 100; i++) {
                output.print((int)(Math.random() * 100) + " ");
            }
            output.close();
        }

        Scanner input = new Scanner(file);
        ArrayList<Integer> list = new ArrayList<>();
        while (input.hasNextInt()) {
            list.add(input.nextInt());
        }
        input.close();

        Collections.sort(list);

        for (int num : list) {
            System.out.print(num + " ");
        }
    }
}