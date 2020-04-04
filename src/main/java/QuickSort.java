import org.testng.Assert;

import java.util.Arrays;


/**
 * General approach
 * - pick a pivot value (could be random, left, right, middle value of (left, right, mid)
 * - partition the array into two partitions
 *   - (orange) where the values of first partition is smaller than the pivot value
 *   - (green) where the values of second partition is smaller than the pivot value
 * - move the pivot value into place
 *   - the right side of the orange region
 */
public class QuickSort {
    public static void main(String[] args) {
        System.out.println("QuickSort.main");

        int[] tmpArr = SortingUtil.generate(10, 50);

        test(tmpArr);
    }

    private static void test(int[] arr) {
        System.out.println("input:         " + Arrays.toString(arr));

        int[] quickSortArr = Arrays.copyOf(arr, arr.length);
        quickSort(quickSortArr);

        Arrays.sort(arr);

        System.out.println("arr:           " + Arrays.toString(arr));
        System.out.println("quickSortArr:  " + Arrays.toString(quickSortArr));

        Assert.assertTrue(SortingUtil.isTheSame(arr, quickSortArr));

    }

    /**
     * Main entry point, need a helper to recursion
     *
     * @param arr
     */
    public static void quickSort(int[] arr) {

        quickSortHelper(arr, 0, arr.length-1);
    }

    /**
     * Quicksort helper
     *
     * @param arr
     * @param left
     * @param right
     */
    private static void quickSortHelper(int[] arr, int left, int right) {
        // base case
        if (left >= right) {
            return;
        }
        // recursive case
        // randomly pick a pivot between left and right
        int pivotIdx = SortingUtil.randomSelect(left, right);

        // move the element at pivotIdx to left
        SortingUtil.swap(arr, left, pivotIdx);
        int orangeIdx = left;
        for (int greenIdx = left+1; greenIdx <= right; greenIdx++) {
            // swap when encounter a number less than the pivot value
            if (arr[greenIdx] < arr[left]) {
                // swap with orangeIdx++
                orangeIdx++;
                SortingUtil.swap(arr, orangeIdx, greenIdx);
            }
        }
        // what must be true at this point?
        // move the pivot into its correct position at orangeIdx
        SortingUtil.swap(arr, left, orangeIdx);

        // sort left side
        quickSortHelper(arr, left, orangeIdx-1);
        // sort right side
        quickSortHelper(arr, orangeIdx+1, right);
    }


}
