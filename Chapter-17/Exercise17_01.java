import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class Exercise17_01 {
    public static void main(String[] args) {
        File file = new File("Exercise17_01.txt");

        try (FileWriter output = new FileWriter(file, true)) { // true = append mode
            Random rand = new Random();

            for (int i = 0; i < 100; i++) {
                int number = rand.nextInt(1000); // generates 0-999
                output.write(number + " ");
            }

            System.out.println("100 random integers written to " + file.getName());

        } catch (IOException ex) {
            System.out.println("Error writing to file: " + ex.getMessage());
        }
    }
}