import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Exercise08_37_Map {
    public static void main(String[] args) {
        Map<String, String> stateCapital = new HashMap<>();
        stateCapital.put("Alabama", "Montgomery");
        stateCapital.put("Alaska", "Juneau");
        stateCapital.put("Arizona", "Phoenix");
        stateCapital.put("Arkansas", "Little Rock");
        stateCapital.put("California", "Sacramento");
        stateCapital.put("Colorado", "Denver");
        stateCapital.put("Connecticut", "Hartford");
        stateCapital.put("Delaware", "Dover");
        stateCapital.put("Florida", "Tallahassee");
        stateCapital.put("Georgia", "Atlanta");
        stateCapital.put("Hawaii", "Honolulu");
        stateCapital.put("Idaho", "Boise");
        stateCapital.put("Illinois", "Springfield");
        stateCapital.put("Indiana", "Indianapolis");
        stateCapital.put("Iowa", "Des Moines");
        stateCapital.put("Kansas", "Topeka");
        stateCapital.put("Kentucky", "Frankfort");
        stateCapital.put("Louisiana", "Baton Rouge");
        stateCapital.put("Maine", "Augusta");
        stateCapital.put("Maryland", "Annapolis");
        stateCapital.put("Massachusetts", "Boston");
        stateCapital.put("Michigan", "Lansing");
        stateCapital.put("Minnesota", "Saint Paul");
        stateCapital.put("Mississippi", "Jackson");
        stateCapital.put("Missouri", "Jefferson City");
        stateCapital.put("Montana", "Helena");
        stateCapital.put("Nebraska", "Lincoln");
        stateCapital.put("Nevada", "Carson City");
        stateCapital.put("New Hampshire", "Concord");
        stateCapital.put("New Jersey", "Trenton");
        stateCapital.put("New York", "Albany");
        stateCapital.put("New Mexico", "Santa Fe");
        stateCapital.put("North Carolina", "Raleigh");
        stateCapital.put("North Dakota", "Bismarck");
        stateCapital.put("Ohio", "Columbus");
        stateCapital.put("Oklahoma", "Oklahoma City");
        stateCapital.put("Oregon", "Salem");
        stateCapital.put("Pennsylvania", "Harrisburg");
        stateCapital.put("Rhode Island", "Providence");
        stateCapital.put("South Carolina", "Columbia");
        stateCapital.put("South Dakota", "Pierre");
        stateCapital.put("Tennessee", "Nashville");
        stateCapital.put("Texas", "Austin");
        stateCapital.put("Utah", "Salt Lake City");
        stateCapital.put("Vermont", "Montpelier");
        stateCapital.put("Virginia", "Richmond");
        stateCapital.put("Washington", "Olympia");
        stateCapital.put("West Virginia", "Charleston");
        stateCapital.put("Wisconsin", "Madison");
        stateCapital.put("Wyoming", "Cheyenne");

        Scanner input = new Scanner(System.in);
        System.out.print("Enter a state: ");
        String state = input.nextLine().trim();

        if (stateCapital.containsKey(state)) {
            System.out.println("The capital of " + state + " is " + stateCapital.get(state));
        } else {
            System.out.println("State not found.");
        }
        input.close();
    }
}