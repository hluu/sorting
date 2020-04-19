import org.testng.Assert;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * https://leetcode.com/problems/kth-largest-element-in-a-stream/
 *
 * int k = 3;
 * int[] arr = [4,5,8,2];
 * KthLargest kthLargest = new KthLargest(3, arr);
 * kthLargest.add(3);   // returns 4
 * kthLargest.add(5);   // returns 5
 * kthLargest.add(10);  // returns 5
 * kthLargest.add(9);   // returns 8
 * kthLargest.add(4);   // returns 8
 */
public class KthLargestElementInStream {

    private int k;
    private PriorityQueue<Integer> pQueue = new PriorityQueue<>();

    public static void main(String[] args) {
        System.out.println("KthLargestElementInStream.main");

        test(3, new int[] {4,5,8,2}, new int[] {3,5,10,9,4}, new int[] {4,5,5,8,8});
    }

    private static void test(int k, int[] nums, int[] toAdd, int[] expected) {
        System.out.println("======= testing =========");
        System.out.printf("k: %d, nums: %s, toAdd:%s\n", k, Arrays.toString(nums),
                            Arrays.toString(toAdd));

        KthLargestElementInStream target = new KthLargestElementInStream(k , nums);

        int[] actual = new int[expected.length];
        for (int idx  = 0; idx < toAdd.length; idx++) {
            actual[idx] =  target.add(toAdd[idx]);
        }

        System.out.printf("expected: %s, actual: %s\n",
                Arrays.toString(expected), Arrays.toString(actual));

        Assert.assertEquals(Arrays.toString(actual), Arrays.toString(expected) );
    }

    public KthLargestElementInStream(int k, int[] nums) {
        this.k = k;

        for (int val : nums) {
            pQueue.add(val);
            if (pQueue.size() > k) {
                pQueue.poll();
            }
        }
    }

    public int add(int val) {
        if (pQueue.size() < k) {
            pQueue.add(val);
        } else if (pQueue.peek() < val) {
            pQueue.poll();
            pQueue.add(val);
        }

        return pQueue.peek();
    }
}
