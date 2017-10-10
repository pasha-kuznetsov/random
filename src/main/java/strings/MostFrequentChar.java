package strings;

import java.util.HashMap;

// return maximum occurring character in the input string
// e.g., if input string is “test” then function should return ‘t’.
public class MostFrequentChar {
    static char mostFrequentChar(String str) {
        int maxCount = 0;
        char mostFrequentChar = '\0';
        HashMap<Character, Integer> charCount = new HashMap<>();
        for (int i = 0; i < str.length(); i++) {
            int count = charCount.compute(str.charAt(i), (c, n) -> n == null ? 1 : n + 1);
            if (count > maxCount) {
                maxCount = count;
                mostFrequentChar = str.charAt(i);
            }
        }
        return mostFrequentChar;
    }
}
