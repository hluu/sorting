import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Intersect {
    public static void main(String[] args) {
        int[] m = {1,3,5,7,9};
        int[] n = {2,3,4,7,8,10, 12};

        int[] result = intersect(m,n);

        System.out.printf("result: %s\n", Arrays.toString(result));
    }

    public static int[] intersect(int[] m, int[] n) {
        List<Integer> output = new ArrayList<>();
        int i = 0;
        int j = 0;

        while (i < m.length && j < n.length) {
            if (m[i] == n[j]) {
                output.add(m[i]);
                i++; j++;
            } else if (m[i] < n[j]) {
                i++;
            } else {
                j++;
            }
        }
        return output.stream().mapToInt(Integer::intValue).toArray();
    }

}
