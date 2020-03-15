import org.testng.Assert;

import java.util.Arrays;

/**
 * Bubble sorting:
 * - bubble either
 *   - large value to the right
 *   - small value to the left
 */
public class BubbleSort {
    public static void main(String[] args) {
        System.out.println("BubbleSort.main");

        int[] tmpArr = SortingUtil.generate(20, 50);

        test(tmpArr);
    }

    private static void test(int[] arr) {
        System.out.println("input:       " + Arrays.toString(arr));


        int[] bubbleLargeArr = Arrays.copyOf(arr, arr.length);
        int[] bubbleSmallArr = Arrays.copyOf(arr, arr.length);

        Arrays.sort(arr);

        bubbleLarge(bubbleLargeArr);
        bubbleSmall(bubbleSmallArr);

        System.out.println("arr:         " + Arrays.toString(arr));
        System.out.println("bubbleLarge: " + Arrays.toString(bubbleLargeArr));
        System.out.println("bubbleSmall: " + Arrays.toString(bubbleSmallArr));

        Assert.assertTrue(SortingUtil.isTheSame(arr, bubbleLargeArr));
        Assert.assertTrue(SortingUtil.isTheSame(arr, bubbleSmallArr));
    }

    /**
     * Bubble large values front to back
     *
     * @param arr
     */
    public static void bubbleLarge(int[] arr) {
        for (int right = arr.length-1; right > 1; right--) {

            for (int left = 0; left < right; left++) {
                if (arr[left] > arr[left+1]) {
                    SortingUtil.swap(arr, left, left+1);
                }
            }
        }
    }

    /**
     * Bubble the small values from back to front
     * @param arr
     */
    public static void bubbleSmall(int[] arr) {
        for (int left = 0; left < arr.length; left++) {

            for (int right = arr.length-1; right > left; right--) {
                if (arr[right] < arr[right-1]) {
                    SortingUtil.swap(arr, right, right-1);
                }
            }
        }
    }
    
}
