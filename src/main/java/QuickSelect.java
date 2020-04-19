import java.util.Arrays;

/**
 * QuickSelect is a variation of QuickSort to find the kth largest,
 * kth smallest, or the median of an unsorted array
 * - it leverages the insight from QuickSort when moving the pivot value
 *   into its global location
 * - then it determines which partitions to go after to find the kth
 *   element
 */
public class QuickSelect {
    public static void main(String[] args) {
        System.out.println("QuickSelect.main");

        int[] tmpArr1 = SortingUtil.generate(10, 50);
        testLargest(tmpArr1, 2);

        int[] tmpArr2 = SortingUtil.generate(10, 50);
        testSmallest(tmpArr2, 3);

    }


    private static void testSmallest(int[] arr, int k) {
        System.out.println("========= test smallest ==========");
        int[] forFinding = Arrays.copyOf(arr, arr.length);

        System.out.println("tmpArr:     " + Arrays.toString(arr));
        Arrays.sort(arr);
        System.out.println("sortedArr:  " + Arrays.toString(arr));


        int expectedKthLargest = arr[k-1];

        int actual = quickSelectSmallest(forFinding, k);

        System.out.printf("result: k: %d, expected: %d, actual: %d",
                k, expectedKthLargest, actual);

        System.out.println("\n");

    }

    public static int quickSelectSmallest(int[] arr, int k) {
        quickSelectHelper(arr, 0, arr.length-1, k-1);

        return arr[k-1];

    }

    private static void testLargest(int[] arr, int k) {
        System.out.println("========= test largest ==========");
        int[] forFinding = Arrays.copyOf(arr, arr.length);

        System.out.println("tmpArr:     " + Arrays.toString(arr));
        Arrays.sort(arr);
        System.out.println("sortedArr:  " + Arrays.toString(arr));


        int expectedKthLargest = arr[arr.length - k];

        int actual = quickSelectLargest(forFinding, k);

        System.out.printf("result: k: %d, expected: %d, actual: %d",
                k, expectedKthLargest, actual);

        System.out.println("\n");
    }


    /**
     * It is guaranteed that the kth largest element exists in the arr,
     * ask long as k is less than the size of the array length
     *
     * @param arr
     * @param k
     */
    public static int quickSelectLargest(int[] arr, int k) {
        quickSelectHelper(arr, 0, arr.length-1, arr.length-k);

        return arr[arr.length-k];

    }

    private static void quickSelectHelper(int[] arr,
                                          int left, int right, int k) {
        // given that we will never have the empty partition
        if (left == right) {
            return;
        }

        // now do the partitioning similar to QuickSort
        // randomly pick a pivot between left and right
        int pivotIdx = SortingUtil.randomSelect(left, right);

        SortingUtil.swap(arr, left, pivotIdx);
        int pivotValue = arr[left];

        // boundary of where the elements are less than pivot value
        int orangeIdx = left;
        for (int greenIdx = left+1; greenIdx <= right; greenIdx++) {
            // swap when encounter a number less than the pivot value
            if (arr[greenIdx] < pivotValue) {
                // swap with orangeIdx++
                orangeIdx++;
                SortingUtil.swap(arr, orangeIdx, greenIdx);
            }
        }
        // move the pivot into its correct position
        SortingUtil.swap(arr, left, orangeIdx);

        // now decide what to do base on the orangeIdx and k
        // there are 3 possible choices
        if (k == orangeIdx) {
            // we found what we are looking for
            return;
        } else if (k < orangeIdx) {
            // go after the partition to the left of orangeIdx
            quickSelectHelper(arr, left, orangeIdx-1, k);
        } else {
            // go after the partition to the right of organgeIdx
            quickSelectHelper(arr, orangeIdx+1, right, k);
        }
    }
}
