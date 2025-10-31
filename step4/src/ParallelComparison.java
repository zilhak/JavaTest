import java.util.Random;

public class ParallelComparison {

    public static void main(String[] args) throws InterruptedException {
        System.out.println("=== Parallel Processing Performance Comparison ===\n");

        int arraySize = 1_000_00000;
        int platformThreadCount = 32;

        // Prepare input array with random values (1~100)
        System.out.println("Preparing input array with " + arraySize + " random values...");
        int[] input = new int[arraySize];
        Random random = new Random(42); // Fixed seed for reproducibility
        for (int i = 0; i < arraySize; i++) {
            input[i] = random.nextInt(100) + 1;
        }
        System.out.println("Array prepared. Starting tests...\n");

        // Test 1: Sequential for-loop
        testForLoop(input);

        // Test 2: Platform Threads (32 threads)
        testPlatformThreads(input, platformThreadCount);

        // Test 3: Virtual Threads
        testVirtualThreads(input);
    }

    private static void testForLoop(int[] input) {
        double[] output = new double[input.length];

        // Start timing
        long startTime = System.nanoTime();

        // Sequential processing
        for (int i = 0; i < input.length; i++) {
            output[i] = complexComputation(input[i], i);
        }

        // End timing
        long endTime = System.nanoTime();
        long duration = (endTime - startTime) / 1_000_000; // Convert to milliseconds

        // Calculate sum
        double sum = 0;
        for (double value : output) {
            sum += value;
        }

        System.out.println("For-loop (sequential):     " + duration + " ms, sum: " + sum);
    }

    private static void testPlatformThreads(int[] input, int threadCount) throws InterruptedException {
        double[] output = new double[input.length];
        Thread[] threads = new Thread[threadCount];
        int chunkSize = (input.length + threadCount - 1) / threadCount; // Ceiling division

        // Start timing
        long startTime = System.nanoTime();

        // Create threads - each thread processes a chunk
        for (int t = 0; t < threadCount; t++) {
            final int threadIndex = t;
            final int startIdx = threadIndex * chunkSize;
            final int endIdx = Math.min(startIdx + chunkSize, input.length);

            threads[t] = new Thread(() -> {
                for (int i = startIdx; i < endIdx; i++) {
                    output[i] = complexComputation(input[i], i);
                }
            });
        }

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
        for (double value : output) {
            sum += value;
        }

        System.out.println("Platform Threads (32):     " + duration + " ms, sum: " + sum);
    }

    private static void testVirtualThreads(int[] input) throws InterruptedException {
        double[] output = new double[input.length];
        int virtualThreadCount = 10_000; // Create 10,000 virtual threads
        int chunkSize = (input.length + virtualThreadCount - 1) / virtualThreadCount;
        Thread[] threads = new Thread[virtualThreadCount];

        // Start timing
        long startTime = System.nanoTime();

        // Create virtual threads - each thread processes a small chunk
        for (int t = 0; t < virtualThreadCount; t++) {
            final int threadIndex = t;
            final int startIdx = threadIndex * chunkSize;
            final int endIdx = Math.min(startIdx + chunkSize, input.length);

            threads[t] = Thread.ofVirtual().unstarted(() -> {
                for (int i = startIdx; i < endIdx; i++) {
                    output[i] = complexComputation(input[i], i);
                }
            });
        }

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
        for (double value : output) {
            sum += value;
        }

        System.out.println("Virtual Threads (10,000):  " + duration + " ms, sum: " + sum);
    }

    private static double complexComputation(int value, int index) {
        // Complex CPU-bound computation that cannot be optimized away by JIT
        double result = 0;
        result += Math.sqrt(value * value + index);
        result += Math.sin(value) * Math.cos(index);
        result += Math.log(value + 1);
        return result;
    }
}
