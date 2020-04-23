import java.util.*;

/**
 * https://leetcode.com/problems/top-k-frequent-elements/
 */
public class TopKFrequentElements {
    public static void main(String[] args) {
        System.out.println("TopKFrequentElements.main");

        test(new int[] {1,1,1,2,2,3}, 2, new int[] {1,2});

    }

    private static void test(int[] nums, int k, int[] expected) {
        System.out.printf("nums:%s, k: %d\n", Arrays.toString(nums), k);

        int[] actual = topKFrequent(nums, k);

        System.out.printf("expected: %s, actual: %s\n",
                Arrays.toString(expected), Arrays.toString(actual));

    }

    public static int[] topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> numFreq = new HashMap<>();

        for (int v : nums) {
            numFreq.put(v, numFreq.getOrDefault(v, 0) + 1);
        }

        System.out.println("numFreq: " + numFreq);

        // min Heap
        PriorityQueue<Integer> q = new
                PriorityQueue<>((n1, n2) -> numFreq.get(n1) - numFreq.get(n2));

        // maintain q of size k
        // remove the smallest one if size > k

        for (Integer v : numFreq.keySet()) {
            q.add(v);
            if (q.size() > k) {
                q.poll();
            }
        }

        ArrayList<Integer> result = new ArrayList<>();
        while (!q.isEmpty()) {
            result.add(q.poll());
        }

        Collections.reverse(result);

        int[] topK = result.stream().mapToInt(Integer::intValue).toArray();


        return topK;
    }
}
