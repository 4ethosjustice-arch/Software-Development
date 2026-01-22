import java.util.*;

class WordOccurrence implements Comparable<WordOccurrence> {
    String word;
    int count;

    public WordOccurrence(String word, int count) {
        this.word = word;
        this.count = count;
    }

    @Override
    public int compareTo(WordOccurrence other) {
        return Integer.compare(this.count, other.count);
    }
}

public class CountOccurrenceOfWords {
    public static void main(String[] args) {
        String text = "Good morning. Have a good class. " +
                      "Have a good visit. Have fun!";

        Map<String, Integer> map = new HashMap<>();
        String[] words = text.split("[\\s+\\p{P}]");

        for (String word : words) {
            String key = word.toLowerCase();
            if (key.length() > 0) {
                map.put(key, map.getOrDefault(key, 0) + 1);
            }
        }

        List<WordOccurrence> list = new ArrayList<>();
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            list.add(new WordOccurrence(entry.getKey(), entry.getValue()));
        }

        Collections.sort(list);

        System.out.println("Word\tCount");
        System.out.println("----------------");
        for (WordOccurrence wo : list) {
            System.out.println(wo.word + "\t" + wo.count);
        }
    }
}