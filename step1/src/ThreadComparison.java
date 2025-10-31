public class ThreadComparison {

    // Prevent JIT from eliminating dead code
    private static volatile long globalResult = 0;

    public static void main(String[] args) throws InterruptedException {
        System.out.println("=== Platform Thread vs Virtual Thread Performance Comparison ===\n");

        // Test cases with different workload sizes
        int[] workloadSizes = {1_000, 10_000, 100_000, 1_000_000};
        int threadCount = 10_000;

        for (int workload : workloadSizes) {
            System.out.println("--- Workload: " + workload + " iterations per thread ---");

            // Test Platform Threads
            testPlatformThreads(threadCount, workload);

            // Test Virtual Threads
            testVirtualThreads(threadCount, workload);

            System.out.println();
        }
    }

    private static void testPlatformThreads(int threadCount, int workload) throws InterruptedException {
        Thread[] threads = new Thread[threadCount];

        // Initialize threads (not counted in timing)
        for (int i = 0; i < threadCount; i++) {
            threads[i] = new Thread(() -> {
                long result = doWork(workload);
                globalResult += result;  // Prevent JIT optimization
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

        System.out.println("Platform Threads (" + threadCount + " threads): " + duration + " ms");
    }

    private static void testVirtualThreads(int threadCount, int workload) throws InterruptedException {
        Thread[] threads = new Thread[threadCount];

        // Initialize threads (not counted in timing)
        for (int i = 0; i < threadCount; i++) {
            threads[i] = Thread.ofVirtual().unstarted(() -> {
                long result = doWork(workload);
                globalResult += result;  // Prevent JIT optimization
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

        System.out.println("Virtual Threads  (" + threadCount + " threads): " + duration + " ms");
    }

    private static long doWork(int iterations) {
        long sum = 0;
        for (int i = 0; i < iterations; i++) {
            sum += i;  // Actual computation that JIT can't eliminate
        }
        return sum;
    }
}
