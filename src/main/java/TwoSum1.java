import org.testng.Assert;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class TwoSum1 {
    public static void main(String[] args) {
        int[] nums = {2,4,5,7,8};

        test(nums, 7, true);
        test(nums, 10, true);
        test(nums, 8, false);
        test(nums, 50, false);

    }

    private static void test(int[] nums, int target, boolean expected) {
        System.out.printf("nums: %s, target: %d\n", Arrays.toString(nums), target);

        boolean actual = twoSum(nums, target);

        System.out.printf("actual: %b, expected: %b\n", actual, expected);

        Assert.assertEquals(actual, expected);
    }

    public static boolean twoSum(int[] nums, int target) {
        Set<Integer> seenSoFar = new HashSet<>();

        for (int val : nums) {
            int other = target - val;
            if (seenSoFar.contains(other)) {
                return true;
            }

            seenSoFar.add(val);
        }
        return false;
    }
}
