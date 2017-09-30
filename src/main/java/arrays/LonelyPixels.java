package arrays;

// Lonely Pixels
// Given an N x M image with black pixels and white pixels, if a pixel is the only one
// in its color throughout its entire row and column, then it is a lonely pixel.
// Find the number of lonely pixels in black from the image. (O(NM))
public class LonelyPixels {
    public static int countLonelyBlackPixels(int[][] image) {
        int lonelyPixels = 0;
        int[] columnState = new int[image.length == 0 ? 0 : image[0].length];
        for (int i = 0; i < image.length; i++) {
            int rowState = 0;
            int firstColumn = -1;
            for (int j = 0; j < image[0].length; j++) {
                if (image[i][j] != 1)
                    continue;
                switch (rowState) {
                    case 0: firstColumn = j; rowState = columnState[j] + 1; break;
                    case 1: rowState++; columnState[firstColumn]++; break;
                }
                switch (columnState[j]) {
                    case 0: columnState[j] = rowState; break;
                    case 1: if (columnState[j]++ == 1) lonelyPixels--; break;
                }
            }
            if (rowState == 1)
                ++lonelyPixels;
        }
        return lonelyPixels;
    }
}
