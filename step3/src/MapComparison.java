import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class MapComparison {

    public static void main(String[] args) throws InterruptedException {
        System.out.println("=== Map.of vs ConcurrentHashMap Performance Comparison with Virtual Threads ===\n");

        // Test cases with different workload sizes
        int[] workloadSizes = {1_000, 10_000, 100_000, 1_000_000};
        int threadCount = 10_000;

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
        // Create shared immutable map with small size (not counted in timing)
        Map<Integer, String> sharedMap = Map.of(
            1, "value1",
            2, "value2",
            3, "value3"
        );

        Thread[] threads = new Thread[threadCount];

        // Initialize threads (not counted in timing)
        for (int i = 0; i < threadCount; i++) {
            threads[i] = Thread.ofVirtual().unstarted(() -> doWork(sharedMap, workload));
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

        System.out.println("Map.of (immutable): " + duration + " ms");
    }

    private static void testConcurrentHashMap(int threadCount, int workload) throws InterruptedException {
        // Create shared HashMap with small size (not counted in timing)
        Map<Integer, String> sharedMap = new ConcurrentHashMap<>();
        sharedMap.put(1, "value1");
        sharedMap.put(2, "value2");
        sharedMap.put(3, "value3");

        Thread[] threads = new Thread[threadCount];

        // Initialize threads (not counted in timing)
        for (int i = 0; i < threadCount; i++) {
            threads[i] = Thread.ofVirtual().unstarted(() -> doWork(sharedMap, workload));
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

        System.out.println("HashMap (mutable):  " + duration + " ms");
    }

    private static void doWork(Map<Integer, String> map, int iterations) {
        for (int i = 0; i < iterations; i++) {
            // Read from shared map (small size: 3 entries)
            String value = map.get((i % 3) + 1);
        }
    }
}
