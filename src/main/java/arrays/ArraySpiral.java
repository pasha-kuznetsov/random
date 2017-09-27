package arrays;

// Fill in a 2D array with numbers in an inward spiraling pattern
public class ArraySpiral {
    public static int[][] fill(int rows, int columns) {
        int n = 0;
        int r0 = -1, c0 = -1;
        int rn = rows, cn = columns;
        int[][] array = new int[rows][columns];
        while (r0 < rn - 1 && c0 < cn - 1) {
            if (++r0 < rn) for (int c = c0 + 1; c < cn; ++c) array[r0][c] = ++n;
            if (--cn > c0) for (int r = r0 + 1; r < rn; ++r) array[r][cn] = ++n;
            if (--rn > r0) for (int c = cn - 1; c > c0; --c) array[rn][c] = ++n;
            if (++c0 < cn) for (int r = rn - 1; r > r0; --r) array[r][c0] = ++n;
        }
        return array;
    }
}
