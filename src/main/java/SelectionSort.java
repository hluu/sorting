import org.testng.Assert;

import java.util.Arrays;

/**
 * An in-place and not stable sorting algorithm
 * - going from left to right
 *   - find a smallest value starting at left position
 *   - swap the smallest value with the left position
 *   - increment the left position
 */
public class SelectionSort {
    public static void main(String[] args) {
        System.out.println("SelectionSort.main");

        int[] tmpArr = SortingUtil.generate(10, 50);

        test(tmpArr);
    }

    private static void test(int[] arr) {
        System.out.println("input:         " + Arrays.toString(arr));


        int[] selectSortArr = Arrays.copyOf(arr, arr.length);
        selectionSort(selectSortArr);

        Arrays.sort(arr);

        System.out.println("arr:           " + Arrays.toString(arr));
        System.out.println("selectSortArr: " + Arrays.toString(selectSortArr));

        Assert.assertTrue(SortingUtil.isTheSame(arr, selectSortArr));

    }


    public static void selectionSort(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            int minIdx = i;

            for (int j = i; j < arr.length; j++) {
                if (arr[j] < arr[minIdx]) {
                    minIdx = j;
                }
            }

            SortingUtil.swap(arr, i, minIdx);
        }
    }
}
