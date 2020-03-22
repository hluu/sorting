import java.util.Random;

public class SortingUtil {
    public static int[] generate(int size, int bound) {
        Random rand = new Random(System.currentTimeMillis());

        int[] tmpArr = new int[size];

        for (int i = 0; i < size; i++) {
            tmpArr[i] = rand.nextInt(bound);
        }

        return tmpArr;
    }

    public static void swap(int[] arr, int left, int right) {
        int tmp = arr[left];

        arr[left] = arr[right];
        arr[right] = tmp;
    }

    public static boolean isTheSame(int[] arr1, int[] arr2) {
        if (arr1 == null || arr2 == null) {
            return false;
        }

        if (arr1.length != arr2.length) {
            return  false;
        }

        for (int i = 0; i < arr1.length; i++) {
            if (arr1[i] != arr2[i]) {
                return false;
            }
        }

        return true;
    }

    public static int randomSelect(int left, int right) {
        Random rand = new Random();
        int randValue = rand.nextInt(right-left+1);

        return left + randValue;
    }
}
