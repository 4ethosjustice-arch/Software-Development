import java.io.*;
import java.util.Scanner;

public class EncryptFiles {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.print("Enter the input file name: ");
        String inputFile = input.nextLine();

        System.out.print("Enter the output file name: ");
        String outputFile = input.nextLine();

        try (
            FileInputStream fis = new FileInputStream(inputFile);
            FileOutputStream fos = new FileOutputStream(outputFile);
        ) {
            int byteData;
            while ((byteData = fis.read()) != -1) {
                fos.write(byteData + 5); // add 5 to encrypt
            }
            System.out.println("Encryption completed. Encrypted file saved as " + outputFile);
        } 
        catch (IOException ex) {
            System.out.println("Error: " + ex.getMessage());
        }
        input.close();
    }

}