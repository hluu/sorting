import org.junit.Assert;

import java.util.Arrays;

/**
 * Inversion Count for an array indicates – how far (or close) the array is from being sorted.
 * If array is already sorted then inversion count is 0.
 * If array is sorted in reverse order that inversion count is the maximum.
 *
 * Formally speaking, two elements a[i] and a[j] form an inversion if a[i] > a[j] and i < j
 *
 * Example:
 *
 * Input: arr[] = {8, 4, 2, 1}
 * Output: 6
 *
 * Explanation: Given array has six inversions:
 * (8,4), (4,2),(8,2), (8,1), (4,1), (2,1).
 *
 *
 * Input: arr[] = {3, 1, 2}
 * Output: 2
 *
 * Explanation: Given array has two inversions:
 * (3, 1), (3, 2)
 *
 * Approaches:
 * - brute force would use the 2 for loops: O(n^2)
 * - using merge sort - O(nlogn)
 *   - during the merge step we can compute the # of inversions
 *
 * https://www.geeksforgeeks.org/counting-inversions/
 * https://www.hackerrank.com/challenges/ctci-merge-sort/problem
 */
public class CountingInversions {
    public static void main(String[] args) {
        System.out.println("CountingInversions.main");

        test(new int[] {1,2,3,4}, 0 );
        test(new int[] {2,1,3,4}, 1 );
        test(new int[] {1,2,4,3}, 1 );
        test(new int[] {1,3,2,4}, 1 );
        test(new int[] {1,5,4,2,3}, 5 );

       test(new int[] {8, 4, 2, 1}, 6 );
       test(new int[] {2, 1, 3, 1, 2}, 4 );

        test(new int[] { 1, 20, 6, 4, 5 }, 5 );
    }

    private static void test(int[] nums, int expected) {
        System.out.printf("nums: %s\n", Arrays.toString(nums));

        int actual = usingMergeSort(nums);
        System.out.printf("expected: %d, actual: %d\n", expected, actual);

        System.out.printf("sorted nums: %s\n\n", Arrays.toString(nums));

        Assert.assertEquals(expected, actual);

    }

    private static int usingMergeSort(int[] nums) {
        return divideStep(nums, 0, nums.length-1);
    }

    private static int divideStep(int[] nums, int start, int end) {
        if (start >= end) {
            // no inversions when there is 1 element
            return 0;
        }
        int mid = (start + end) / 2;

        int count = divideStep(nums, start, mid);
        count += divideStep(nums, mid+1, end);

        count += mergeStep(nums, start, mid, end);
        return count;
    }

    private static int mergeStep(int[] nums, int start, int mid, int end) {

        // since we are going to update nums in place, therefore
        // make a copy of left and right side in the nums

        // Arrays.copyOfRange - the right index is exclusive
        int[] left = Arrays.copyOfRange(nums, start, mid+1);
        int[] right = Arrays.copyOfRange(nums, mid+1, end+1);

        int lIdx = 0, rIdx = 0;
        int inversionCnt = 0;
        int nIdx = start;

        // merging - invariant - both arrays are already sorted
        while (lIdx < left.length && rIdx < right.length) {
            if (left[lIdx] <= right[rIdx]) {
                nums[nIdx++] = left[lIdx++];
            } else {
                // this is when numbers are out of order (inversion)
                nums[nIdx++] = right[rIdx++];
                // the total number of inversions is the remaining numbers
                // from lIdx to the end of the left array, why?
                // because right[rIdx] is smaller than left[lIdx] and all the elements to the
                // right of lIdx
                inversionCnt += left.length - lIdx;
            }
        }

        while (lIdx < left.length) {
            nums[nIdx++] = left[lIdx++];
        }

        while (rIdx < right.length) {
            nums[nIdx++] = right[rIdx++];
        }

        return inversionCnt;
    }
}

