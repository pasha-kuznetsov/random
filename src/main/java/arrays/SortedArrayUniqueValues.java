package arrays;

import java.util.ArrayList;
import java.util.List;

// Find all unique values of a sorted integer array
public class SortedArrayUniqueValues {
    static List<Integer> uniqueValues(int[] array) {
        List<Integer> unique = new ArrayList<>();
        for (int i = 0; i < array.length; ) {
            if (i + 1 == array.length || array[i] != array[i + 1]) {
                unique.add(array[i++]);
            } else {
                int n = array[i++];
                while (i < array.length && array[i] == n)
                    ++i;
            }
        }
        return unique;
    }
}
