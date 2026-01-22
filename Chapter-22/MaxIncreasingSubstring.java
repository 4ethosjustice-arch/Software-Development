import java.util.Scanner;

public class MaxIncreasingSubstring {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.print("Enter a string: ");
        String str = input.nextLine();
        input.close();
        if (str.isEmpty()) {
            System.out.println("Empty string!");
            return;
        }

        String maxSub = str.substring(0, 1); 
        String currentSub = str.substring(0, 1); 

        for (int i = 1; i < str.length(); i++) {
            if (str.charAt(i) > str.charAt(i - 1)) {
                currentSub += str.charAt(i);
            } else {
                if (currentSub.length() > maxSub.length()) {
                    maxSub = currentSub;
                }
                currentSub = "" + str.charAt(i);
            }
        }

        if (currentSub.length() > maxSub.length()) {
            maxSub = currentSub;
        }

        System.out.println("Maximum consecutive increasingly ordered substring: " + maxSub);
    }
}