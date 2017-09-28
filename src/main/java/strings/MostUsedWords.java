package strings;

import java.util.*;

public class MostUsedWords {
    public static List<String> mostUsedWords(String[] text, int n) {
        HashMap<String, Integer> wordCounts = new HashMap<>();
        for (String word : text)
            wordCounts.compute(word, (w, c) -> c == null ? 1 : c + 1);

        PriorityQueue<String> mostUsed = new PriorityQueue<>(new MostUsedComparator(wordCounts));
        mostUsed.addAll(wordCounts.keySet());

        List<String> words = new ArrayList<>();
        for (int i = 0; i < n && !mostUsed.isEmpty(); ++i)
            words.add(mostUsed.poll());
        return words;
    }

    private static class MostUsedComparator implements Comparator<String> {
        private final HashMap<String, Integer> wordCounts;

        MostUsedComparator(HashMap<String, Integer> wordCounts) {
            this.wordCounts = wordCounts;
        }

        @Override
        public int compare(String word1, String word2) {
            return wordCounts.get(word2).compareTo(wordCounts.get(word1));
        }
    }
}
