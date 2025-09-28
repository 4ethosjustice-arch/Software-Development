import java.io.DataOutputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Random;

public class Exercise17_03 {

    public static void main(String[] args) {
        String filename = "Exercise17_03.dat";

        writeRandomIntegers(filename);
        int sum = sumIntegers(filename);

        System.out.println("Sum of integers in " + filename + " is: " + sum);
    }

    public static void writeRandomIntegers(String filename) {
        File file = new File(filename);

        try (DataOutputStream output = new DataOutputStream(new FileOutputStream(file, true))) {
            Random rand = new Random();

            for (int i = 0; i < 100; i++) {
                int number = rand.nextInt(1000); // 0-999
                output.writeInt(number);
            }

            System.out.println("100 random integers written to " + filename);
        } catch (IOException ex) {
            System.out.println("Error writing to file: " + ex.getMessage());
        }
    }

    public static int sumIntegers(String filename) {
        int sum = 0;

        try (DataInputStream input = new DataInputStream(new FileInputStream(filename))) {
            while (true) {
                sum += input.readInt();
            }
        } catch (IOException ex) {
            // End of file reached, normal termination
        }

        return sum;
    }
}