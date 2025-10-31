# Step1: Platform Thread vs Virtual Thread Performance Comparison

## Description
This project compares the performance of Platform Threads and Virtual Threads in Java 25.0.1.

## Test Cases
- 1,000 iterations per thread
- 10,000 iterations per thread
- 100,000 iterations per thread
- 1,000,000 iterations per thread

Each test runs 10,000 threads performing actual computation (sum of integers).

**JIT Optimization Prevention**: Uses `volatile` global variable to ensure JIT compiler doesn't eliminate the computation.

## How to Run

### Compile
```bash
javac src/ThreadComparison.java -d bin
```

### Run
```bash
java -cp bin ThreadComparison
```

### Or use the batch file (Windows)
```bash
run.bat
```
