package arrays;

import java.util.ArrayList;

// You are given a vector of integers. You have to delete the odd numbers from it.
// Expected complexity is O(N) Time and O(1) space
public class ArrayRemoveOdd {
    public static void removeOdd(ArrayList<Integer> array) {
        int n = 0;
        for (int i = 0; i < array.size(); ++i) {
            if (array.get(i) % 2 == 0) {
                array.set(n++, array.get(i));
            }
        }
        array.subList(n, array.size()).clear();
    }
}
