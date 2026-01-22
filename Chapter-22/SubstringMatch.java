import java.util.Scanner;

public class SubstringMatch {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.print("Enter the main string: ");
        String s1 = input.nextLine();
        
        System.out.print("Enter the substring to match: ");
        String s2 = input.nextLine();

        if (isSubstring(s1, s2)) {
            System.out.println("\"" + s2 + "\" is a substring of \"" + s1 + "\"");
        } else {
            System.out.println("\"" + s2 + "\" is NOT a substring of \"" + s1 + "\"");
        }
        input.close();
    }

    public static boolean isSubstring(String s1, String s2) {
        int n = s1.length();
        int m = s2.length();

        if (m > n) return false; 

        for (int i = 0; i <= n - m; i++) {
            boolean match = true;
            for (int j = 0; j < m; j++) {
                if (s1.charAt(i + j) != s2.charAt(j)) {
                    match = false;
                    break;
                }
            }
            if (match) return true;
        }
        return false;
    }
}