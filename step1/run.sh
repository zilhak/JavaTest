#!/bin/bash
echo "컴파일 중..."
mkdir -p bin
javac src/ThreadComparison.java -d bin

echo ""
echo "실행 중..."
java -cp bin ThreadComparison

read -p "계속하려면 Enter를 누르세요..."
