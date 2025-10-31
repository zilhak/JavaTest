import java.util.Random;
import java.util.Arrays;

public class ArrayOperation {

    public static void main(String[] args) throws InterruptedException {
        System.out.println("=== Array Operation Performance Test: For-loop vs Virtual Threads ===\n");

        int arraySize = 1_000_000;

        // Prepare input array with random values (1~100)
        int[] input = new int[arraySize];
        Random random = new Random(42); // Fixed seed for reproducibility
        for (int i = 0; i < arraySize; i++) {
            input[i] = random.nextInt(100) + 1;
        }

        System.out.println("Input array prepared with " + arraySize + " random values (1~100)");
        System.out.println("Operation: divide each value by 2\n");

        // Test with different thread counts
        int[] threadCounts = {1_000, 10_000, 100_000, 1_000_000};

        for (int threadCount : threadCounts) {
            System.out.println("=== Processing " + threadCount + " elements ===");

            // Method 1: Simple for-loop
            double[] outputForLoop = testForLoop(input, threadCount);

            // Method 2: Virtual Threads
            double[] outputVirtualThread = testVirtualThreads(input, threadCount);

            // Verify results are the same
            boolean identical = Arrays.equals(outputForLoop, outputVirtualThread);
            System.out.println("Results identical: " + identical);
            System.out.println();
        }
    }

    private static double[] testForLoop(int[] input, int count) {
        double[] output = new double[input.length];

        // Start timing
        long startTime = System.nanoTime();

        // Complex computation for each element
        for (int i = 0; i < count; i++) {
            output[i] = Math.sqrt(input[i] * input[i] + i) + Math.sin(input[i]);
        }

        // End timing
        long endTime = System.nanoTime();
        long duration = (endTime - startTime) / 1_000_000; // Convert to milliseconds

        System.out.println("For-loop: " + duration + " ms");

        return output;
    }

    private static double[] testVirtualThreads(int[] input, int threadCount) throws InterruptedException {
        double[] output = new double[input.length];

        // Start timing from thread initialization
        long startTime = System.nanoTime();

        Thread[] threads = new Thread[threadCount];

        // Create threads - each thread handles its index
        for (int i = 0; i < threadCount; i++) {
            final int index = i;
            threads[i] = Thread.ofVirtual().unstarted(() -> {
                // Complex computation for each element
                output[index] = Math.sqrt(input[index] * input[index] + index) + Math.sin(input[index]);
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

        System.out.println("Virtual Threads: " + duration + " ms");

        return output;
    }
}
