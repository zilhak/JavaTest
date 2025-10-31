# Step2: Map.of vs HashMap Performance Comparison with Virtual Threads

## Description
This project compares the performance of Map.of (immutable) and ConcurrentHashMap (mutable) when used with Virtual Threads in Java 25.0.1.

Both maps contain 10 entries (the maximum for Map.of).

**JIT Optimization Prevention**: Uses `volatile` global variable and actual hashCode() computation to ensure JIT compiler doesn't eliminate the map reads.

## Test Cases
- 1,000 iterations per thread
- 10,000 iterations per thread
- 100,000 iterations per thread
- 1,000,000 iterations per thread

Each test runs 10,000 virtual threads performing read operations on the maps.

## How to Run

### Compile
```bash
javac src/MapComparison.java -d bin
```

### Run
```bash
java -cp bin MapComparison
```

### Or use the batch file (Windows)
```bash
run.bat
```
