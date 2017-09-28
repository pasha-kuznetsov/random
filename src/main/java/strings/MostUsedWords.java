package strings;

import java.util.*;

public class MostUsedWords {
    public static List<String> mostUsedWords(String[] text, int n) {

        HashMap<String, Integer> wordCounts = new HashMap<>();
        for (String word : text)
            wordCounts.compute(word, (w, c) -> c == null ? 1 : c + 1);

        // TODO: rewrite with a Priority queue
        TreeMap<Integer, List<String>> mostUsed = new TreeMap<>();
        for (Map.Entry<String, Integer> kv : wordCounts.entrySet()) {
            mostUsed.compute(kv.getValue(), (c, l) -> {
                if (l == null) l = new ArrayList<>();
                l.add(kv.getKey());
                return l;
            } );
        }

        List<String> words = new ArrayList<>();
        Integer[] counts = mostUsed.keySet().toArray(new Integer[mostUsed.size()]);
        for (int i = counts.length - 1; i >= 0; i--) {
            if (words.size() == n)
                return words;
            for (String word : mostUsed.get(counts[i])) {
                if (words.size() == n)
                    return words;
                words.add(word);
            }
        }

        return words;
    }
}
