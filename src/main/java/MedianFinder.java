import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * https://leetcode.com/problems/find-median-from-data-stream/
 *
 * Median is the middle value in an ordered integer list. If the size of the list is even, there is no middle value. So the median is the mean of the two middle value.
 *
 * For example,
 * [2,3,4], the median is 3
 *
 * [2,3], the median is (2 + 3) / 2 = 2.5
 *
 * Design a data structure that supports the following two operations:
 *
 * void addNum(int num) - Add a integer number from the data stream to the data structure.
 * double findMedian() - Return the median of all elements so far.
 *
 *
 * Example:
 *
 * addNum(1)
 * addNum(2)
 * findMedian() -> 1.5
 * addNum(3)
 * findMedian() -> 2
 *
 *
 * Follow up:
 *
 * If all integer numbers from the stream are between 0 and 100, how would you optimize it?
 * If 99% of all integer numbers from the stream are between 0 and 100, how would you optimize it?
 */
public class MedianFinder {
    public static void main(String[] args) {
        System.out.println("MedianFinder.main");

       // test(new int[] {2,3,4}, 3);
       // test(new int[] {2,3}, 2.5);
       // test(new int[] {1,2}, 1.5);
        test(new int[] {0,0}, 0);
    }

    private static void test(int[] nums, double expected) {
        System.out.printf("nums: %s, expected: %.2f\n", Arrays.toString(nums), expected);
        MedianFinder mf = new MedianFinder();
        for (int v : nums) {
            mf.addNum(v);
        }

        System.out.printf("expected: %.2f, actual: %.2f\n", expected, mf.findMedian());
    }

    private static class DescendingOrderComparator implements Comparator<Integer> {
        public int compare(Integer o1, Integer o2) {
            return o2.compareTo(o1);
        }
    }

    private PriorityQueue<Integer> maxHeap = new PriorityQueue<>(new DescendingOrderComparator());
    private PriorityQueue<Integer> minHeap = new PriorityQueue<>();

    public MedianFinder() {

    }

    public void addNum(int num) {
        // adding part
        if (maxHeap.isEmpty()) {
            maxHeap.add(num);
        } else if (num <= maxHeap.peek()) {
            maxHeap.add(num);
        } else {
            minHeap.add(num);
        }

        // rebalancing part
        if (maxHeap.size() - minHeap.size() == 2) {
            minHeap.add(maxHeap.poll());
        } else if (minHeap.size() - maxHeap.size() == 2) {
            maxHeap.add(minHeap.poll());
        }

    }

    public double findMedian() {
        if (maxHeap.size() == minHeap.size()) {
            // even size case
            return ((double)maxHeap.peek() + minHeap.peek()) / 2;
        } else if (maxHeap.size() > minHeap.size()) {
            // odd case
            return maxHeap.peek();
        } else {
            // odd case
            return minHeap.peek();
        }
    }
}
