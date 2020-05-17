import org.testng.Assert;

import java.util.Arrays;

/**
 * Classic MergeSort
 */
public class MergeSort {
    public static void main(String[] args) {

        test(new int[] {20, 35, -15, 7, 55, 1, -22});
        test(new int[] {1,2,3,4,5,6});
        test(new int[] {6,5,4,3,2,1});
        test(new int[] {6,6,6,6,6,6});
        test(new int[] {6,1,5,2,4,3});
    }

    private static void test(int[] nums) {
        System.out.printf("nums: %s\n", Arrays.toString(nums));

        int[] numTemp = Arrays.copyOf(nums, nums.length);

        mergeSort(numTemp, 0, numTemp.length-1);

        System.out.printf("numTemp: %s\n", Arrays.toString(numTemp));

        Arrays.sort(nums);
        System.out.printf("nums: %s\n", Arrays.toString(nums));

        Assert.assertEquals(nums, numTemp);
    }

    private static void mergeSort(int[] nums, int left, int right) {
        if (left >= right) {
            return;
        }

        int mid = (left + right) / 2;

        mergeSort(nums, left, mid);
        mergeSort(nums, mid+1, right);
        merge(nums, left, mid, right);
    }

    private static void merge(int[] nums, int left, int mid, int right) {
        int[] leftSide = Arrays.copyOfRange(nums, left, mid+1);
        int[] rightSide = Arrays.copyOfRange(nums, mid+1, right+1);

        int lIdx = 0; int rIdx = 0;
        int nIdx = left;

        while (lIdx < leftSide.length && rIdx < rightSide.length) {
            if  (leftSide[lIdx] <= rightSide[rIdx]) {
                nums[nIdx++] = leftSide[lIdx++];
            } else {
                nums[nIdx++] = rightSide[rIdx++];
            }
        }

        while (lIdx  < leftSide.length) {
            nums[nIdx++] = leftSide[lIdx++];
        }

        while (rIdx  < rightSide.length) {
            nums[nIdx++] = rightSide[rIdx++];
        }

    }
}
