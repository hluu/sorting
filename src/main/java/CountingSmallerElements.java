import org.junit.Assert;

import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;

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
    }

    private static void test(int[] nums, List<Integer> expected) {
        System.out.printf("nums: %s\n", Arrays.toString(nums));

        List<Integer> actual = bruteForce(nums);

        System.out.printf("expected: %s, actual: %s\n", expected, actual);

        Assert.assertArrayEquals(expected.stream().mapToInt(Integer::intValue).toArray(),
                actual.stream().mapToInt(Integer::intValue).toArray());
    }

    private static List<Integer> usingMergeSort(int[] nums){
        return null;
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
