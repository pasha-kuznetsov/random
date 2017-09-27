package numeric;

import java.util.ArrayDeque;
import java.util.Collections;

// Accept an integer as input and output the corresponding column
// the number is represented in in Excel. So, 1 is A, 27 is AA, etc.
class ExcelColumnNumber {
    final static char first = 'A';
    final static char last = 'Z';
    final static int radix = 'Z' - 'A' + 1;

    static String toString(int n) {
        StringBuilder result = new StringBuilder();
        for (; n > 0; n /= radix) {
            int d = n % radix;
            if (d == 0) {
                result.append(last);
                n--;
            }
            else {
                result.append((char)(first + d - 1));
            }
        }
        return result.reverse().toString();
    }
}
