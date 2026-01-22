import java.util.Scanner;
import java.util.Random;

public class Exercise08_37 {
    public static void main(String[] args) {
        String[][] stateCapital = {
            {"Alabama", "Montgomery"}, {"Alaska", "Juneau"}, {"Arizona", "Phoenix"},
            {"Arkansas", "Little Rock"}, {"California", "Sacramento"}, {"Colorado", "Denver"},
            {"Connecticut", "Hartford"}, {"Delaware", "Dover"}, {"Florida", "Tallahassee"},
            {"Georgia", "Atlanta"}, {"Hawaii", "Honolulu"}, {"Idaho", "Boise"},
            {"Illinois", "Springfield"}, {"Indiana", "Indianapolis"}, {"Iowa", "Des Moines"},
            {"Kansas", "Topeka"}, {"Kentucky", "Frankfort"}, {"Louisiana", "Baton Rouge"},
            {"Maine", "Augusta"}, {"Maryland", "Annapolis"}, {"Massachusetts", "Boston"},
            {"Michigan", "Lansing"}, {"Minnesota", "Saint Paul"}, {"Mississippi", "Jackson"},
            {"Missouri", "Jefferson City"}, {"Montana", "Helena"}, {"Nebraska", "Lincoln"},
            {"Nevada", "Carson City"}, {"New Hampshire", "Concord"}, {"New Jersey", "Trenton"},
            {"New York", "Albany"}, {"New Mexico", "Santa Fe"}, {"North Carolina", "Raleigh"},
            {"North Dakota", "Bismarck"}, {"Ohio", "Columbus"}, {"Oklahoma", "Oklahoma City"},
            {"Oregon", "Salem"}, {"Pennsylvania", "Harrisburg"}, {"Rhode Island", "Providence"},
            {"South Carolina", "Columbia"}, {"South Dakota", "Pierre"}, {"Tennessee", "Nashville"},
            {"Texas", "Austin"}, {"Utah", "Salt Lake City"}, {"Vermont", "Montpelier"},
            {"Virginia", "Richmond"}, {"Washington", "Olympia"}, {"West Virginia", "Charleston"},
            {"Wisconsin", "Madison"}, {"Wyoming", "Cheyenne"}
        };

        Scanner input = new Scanner(System.in);
        Random random = new Random();
        int correctCount = 0;

        int[] indices = new int[stateCapital.length];
        for (int i = 0; i < indices.length; i++) {
            indices[i] = i;
        }

        for (int i = indices.length - 1; i > 0; i--) {
            int j = random.nextInt(i + 1);
            int temp = indices[i];
            indices[i] = indices[j];
            indices[j] = temp;
        }

        for (int k = 0; k < stateCapital.length; k++) {
            int i = indices[k];
            System.out.print("What is the capital of " + stateCapital[i][0] + "? ");
            String capital = input.nextLine().trim();
            if (capital.equalsIgnoreCase(stateCapital[i][1])) {
                System.out.println("Your answer is correct");
                correctCount++;
            } else {
                System.out.println("The correct answer should be " + stateCapital[i][1]);
            }
        }

        System.out.println("The correct count is " + correctCount);
        input.close();
    }
}