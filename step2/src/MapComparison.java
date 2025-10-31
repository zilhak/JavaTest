import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class MapComparison {

    public static void main(String[] args) throws InterruptedException {
        System.out.println("=== Map.of vs ConcurrentHashMap Performance Comparison with Virtual Threads ===\n");

        // Test cases with different workload sizes
        int[] workloadSizes = {1_000, 10_000, 100_000, 1_000_000};
        int threadCount = 100_000;

        for (int workload : workloadSizes) {
            System.out.println("--- Workload: " + workload + " iterations per thread ---");

            // Test Map.of
            testMapOf(threadCount, workload);

            // Test ConcurrentHashMap
            testConcurrentHashMap(threadCount, workload);

            System.out.println();
        }
    }

    private static void testMapOf(int threadCount, int workload) throws InterruptedException {
        // Create shared immutable map (not counted in timing)
        Map<Integer, String> sharedMap = Map.of(
            1, "value1",
            2, "value2",
            3, "value3",
            4, "value4",
            5, "value5",
            6, "value6",
            7, "value7",
            8, "value8",
            9, "value9",
            10, "value10"
        );

        Thread[] threads = new Thread[threadCount];
        double[] results = new double[threadCount];

        // Initialize threads (not counted in timing)
        for (int i = 0; i < threadCount; i++) {
            final int index = i;
            threads[i] = Thread.ofVirtual().unstarted(() -> {
                results[index] = doWork(sharedMap, workload);
            });
        }

        // Start timing
        long startTime = System.nanoTime();

        // Start all threads
        for (Thread thread : threads) {
            thread.start();
        }

        // Wait for all threads to complete
        for (Thread thread : threads) {
            thread.join();
        }

        // End timing
        long endTime = System.nanoTime();
        long duration = (endTime - startTime) / 1_000_000; // Convert to milliseconds

        // Calculate sum
        double sum = 0;
        for (double result : results) {
            sum += result;
        }

        System.out.println("Map.of (immutable): " + duration + " ms, sum: " + sum);
    }

    private static void testConcurrentHashMap(int threadCount, int workload) throws InterruptedException {
        // Create shared HashMap (not counted in timing)
        Map<Integer, String> sharedMap = new ConcurrentHashMap<>();
        sharedMap.put(1, "value1");
        sharedMap.put(2, "value2");
        sharedMap.put(3, "value3");
        sharedMap.put(4, "value4");
        sharedMap.put(5, "value5");
        sharedMap.put(6, "value6");
        sharedMap.put(7, "value7");
        sharedMap.put(8, "value8");
        sharedMap.put(9, "value9");
        sharedMap.put(10, "value10");

        Thread[] threads = new Thread[threadCount];
        double[] results = new double[threadCount];

        // Initialize threads (not counted in timing)
        for (int i = 0; i < threadCount; i++) {
            final int index = i;
            threads[i] = Thread.ofVirtual().unstarted(() -> {
                results[index] = doWork(sharedMap, workload);
            });
        }

        // Start timing
        long startTime = System.nanoTime();

        // Start all threads
        for (Thread thread : threads) {
            thread.start();
        }

        // Wait for all threads to complete
        for (Thread thread : threads) {
            thread.join();
        }

        // End timing
        long endTime = System.nanoTime();
        long duration = (endTime - startTime) / 1_000_000; // Convert to milliseconds

        // Calculate sum
        double sum = 0;
        for (double result : results) {
            sum += result;
        }

        System.out.println("HashMap (mutable):  " + duration + " ms, sum: " + sum);
    }

    private static double doWork(Map<Integer, String> map, int iterations) {
        double result = 0;

        for (int i = 0; i < iterations; i++) {
            // Read from shared map
            String value = map.get((i % 10) + 1);

            // Perform complex operations that JIT cannot eliminate
            int hashCode = value.hashCode();
            int length = value.length();
            result += Math.sqrt(hashCode * length + i);
        }

        return result;
    }
}
