#!/bin/bash

echo "컴파일 중..."
mkdir -p bin
javac src/ParallelComparison.java -d bin

echo
echo "실행 중..."
java -cp bin ParallelComparison
