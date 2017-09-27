package strings;

class StringCompression {
    // String compression and decompression.
    // "aaabbbcc" to "a3b3c2" (compress),
    static String compress(String str) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < str.length(); ) {
            sb.append(str.charAt(i++));
            int n = 1;
            for (; i < str.length() && str.charAt(i) == str.charAt(i - 1); ++i)
                n++;
            switch (n) {
                case 2: sb.append(str.charAt(i - 1));
                case 1: break;
                default: sb.append(n);
            }
        }
        return sb.toString();
    }

    // then decompress back to original string.
    static String decompress(String str) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < str.length(); ) {
            char c = '\0';
            for (; i < str.length() && !Character.isDigit(str.charAt(i)); ++i)
                sb.append(c = str.charAt(i));
            int n = 0;
            for (; i < str.length() && Character.isDigit(str.charAt(i)); ++i)
                n = n * 10 + str.charAt(i) - '0';
            for (; n > 1; n--)
                sb.append(c);
        }
        return sb.toString();
    }
}
