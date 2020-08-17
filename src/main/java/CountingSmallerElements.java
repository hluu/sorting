import org.junit.Assert;

import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;
import java.util.stream.Collectors;

/**
 * You are given an integer array nums and you have to return a new counts array.
 * The counts array has the property where counts[i] is the number of smaller elements to the right of nums[i].
 *
 * Example:
 *
 * Input: [5,2,6,1]
 * Output: [2,1,1,0]
 * Explanation:
 * To the right of 5 there are 2 smaller elements (2 and 1).
 * To the right of 2 there is only 1 smaller element (1).
 * To the right of 6 there is 1 smaller element (1).
 * To the right of 1 there is 0 smaller element.
 *
 * https://leetcode.com/problems/count-of-smaller-numbers-after-self/
 */
public class CountingSmallerElements {

    public static void main(String[] args) {
        System.out.println("CountingInversion.main");

        test(new int[]{5, 2, 6, 1}, Arrays.asList(2, 1, 1, 0));
        test(new int[]{5, 2, 6, 1, 3}, Arrays.asList(3, 1, 2, 0, 0));
    }

    private static void test(int[] nums, List<Integer> expected) {
        System.out.printf("nums: %s\n", Arrays.toString(nums));

        List<Integer> actual = bruteForce(nums);


        int[] expectedIntArr = expected.stream().mapToInt(Integer::intValue).toArray();
        Assert.assertArrayEquals(expectedIntArr,
                actual.stream().mapToInt(Integer::intValue).toArray());

        List<Integer> actual2 = usingMergeSort(nums);

        System.out.printf("expected: %s, actual: %s, actual2: %s\n", expected, actual, actual2);

        Assert.assertArrayEquals(expectedIntArr,
                actual2.stream().mapToInt(Integer::intValue).toArray());


    }

    private static class Pair{
        int val;
        int idx;
        public Pair(int val, int idx) {
            this.val = val;
            this.idx = idx;
        }
    }

    /**
     * How to leverage the merge step to figure out the number of smaller values
     * while merging.
     *
     * Need an object to represent a pair of (elm value and elm index)
     *
     * @param nums
     * @return
     */
    private static List<Integer> usingMergeSort(int[] nums){
        Pair[] pairNums = new Pair[nums.length];
        for (int i = 0; i < nums.length; i++) {
            pairNums[i] = new Pair(nums[i], i);
        }

        int result[] = new int[nums.length];
        divideStep(pairNums, 0, pairNums.length-1, result);

        List<Integer> finalResult = Arrays.stream(result).boxed().collect(Collectors.toList());
        return finalResult;
    }

    private static void divideStep(Pair[] pairNums, int start, int end, int[] result) {
        if (start >= end) {
            return;
        }
        int mid = (start + end) / 2;

        divideStep(pairNums, start, mid, result);
        divideStep(pairNums, mid+1, end, result);

        mergeStep(pairNums, start, mid, end, result);
    }

    private static void mergeStep(Pair[] pairNums, int start, int mid, int end, int[] result) {
        // Arrays.copyOfRange - the right index is exclusive
        Pair[] left = Arrays.copyOfRange(pairNums, start, mid+1);
        Pair[] right = Arrays.copyOfRange(pairNums, mid+1, end+1);

        int lIdx = 0, rIdx = 0;
        int nIdx = start;

        while (lIdx < left.length && rIdx < right.length) {
            if (left[lIdx].val <= right[rIdx].val) {
                // move an element from left into its position
                // the rIdx indicates # of elements that are smaller it
                pairNums[nIdx++] = left[lIdx];
                result[left[lIdx].idx] += rIdx;
                lIdx++;
            } else {
                pairNums[nIdx++] = right[rIdx++];
            }
        }

        // the remaining elements
        while (lIdx  < left.length) {
            pairNums[nIdx++] = left[lIdx];
            result[left[lIdx].idx] += rIdx;
            lIdx++;
        }

        while (rIdx  < right.length) {
            pairNums[nIdx++] = right[rIdx++];
        }
    }


    private static List<Integer> bruteForce(int[] nums)  {
        List<Integer> output = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            int cnt = 0;
            for (int j=i+1; j < nums.length; j++) {
                cnt += (nums[i] > nums[j]) ? 1 : 0;
            }
            output.add(cnt);
        }
        return output;
    }
}
