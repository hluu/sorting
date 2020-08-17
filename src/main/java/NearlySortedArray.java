import org.junit.Assert;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * https://www.geeksforgeeks.org/nearly-sorted-algorithm/
 *
 * Input : arr[] = {6, 5, 3, 2, 8, 10, 9} k = 3
 * Output : arr[] = {2, 3, 5, 6, 8, 9, 10}
 *
 * Input : arr[] = {10, 9, 8, 7, 4, 70, 60, 50} k = 4
 * Output : arr[] = {4, 7, 8, 9, 10, 50, 60, 70}
 *
 * Observation:
 * arr[] = {6, 5, 3, 2, 8, 10, 9} k = 3
 * arr[] = {6, 6, 6, 6}
 *       = {5, 5, 5, 5, 5}
 *       = {3, 3, 3, 3, 3, 3}
 *       = {2, 2, 2, 2, 2, 2, 2}
 *       = {   8, 8, 8, 8, 8, 8}
 *       = {     10,10,10,10,10}
 *       = {         9, 9, 9, 9}
 *
 * Approach:
 *  - explore the different locations that each element can exists
 *  - observer the pattern
 *  - create a min heap of size k
 *  - add the first k elements to the heap
 *    - because element at k+1 can appear every single slot from there to 0 index
 *  - iterate from k+1 to the end of the array
 *    - for each element
 *    - initialize variable k = 0
 *    - extract from the heap and assign to arr[k++] = top of the heap
 *    - add element at arrp[j] to heap
 */
public class NearlySortedArray {
    public static void main(String[] args) {
        System.out.println("NearlySrtedArray.main");

        test(new int[] {2, 6, 3, 12, 56, 8}, 3);
        test(new int[] {6, 5, 3, 2, 8, 10, 9}, 3);
        test(new int[] {10, 9, 8, 7, 4, 70, 60, 50}, 4);
    }

    private static void test(int[] nums, int k) {
        System.out.printf("\nk: %d, nums: %s\n", k, Arrays.toString(nums));

        nearlySortArray(nums, k);

        System.out.printf("after nearlySortArray: %s\n", Arrays.toString(nums));

        for (int i = 1; i < nums.length; i++) {
            Assert.assertTrue(nums[i-1] <= nums[i]);
        }
    }

    private static void nearlySortArray(int[] nums, int k) {
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        // prime the heap
        for (int i = 0; i <= k; i++) {
            minHeap.add(nums[i]);
        }

        int idx = 0;
        for (int i = k+1; i < nums.length; i++) {
            nums[idx++] = minHeap.remove();
            minHeap.add(nums[i]);
        }

        // extract the remaining from the heap
        while (!minHeap.isEmpty()) {
            nums[idx++] = minHeap.remove();
        }
    }


}
