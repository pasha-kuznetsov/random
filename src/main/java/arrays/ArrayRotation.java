package arrays;

// Given an image represented by an NxN matrix, where each pixel in the image is 4
// bytes, write a method to rotate the image by 90 degrees. Can you do this in place?
public class ArrayRotation {
    //  [[1, 2, 3],
    //   [4, 5, 6],
    //   [7, 8, 9]] | [[7, 4, 1],
    //                 [8, 5, 2],
    //                 [9, 6, 3]]
    static void rotate(int[][] image) {
        int n = image.length - 1;
        for (int i = 0; i < (n + 1) / 2; ++i) {
            for (int j = i; j < n - i; ++j) {
                int tmp = image[i][j];
                image[i][j] = image[n - j][i];
                image[n - j][i] = image[n - i][n - j];
                image[n - i][n - j] = image[j][n - i];
                image[j][n - i] = tmp;
            }
        }
    }
}
