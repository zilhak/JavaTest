#!/bin/bash
echo "컴파일 중..."
mkdir -p bin
javac src/MapComparison.java -d bin

echo ""
echo "JIT 활성화 상태로 실행 중..."
java -cp bin MapComparison

read -p "계속하려면 Enter를 누르세요..."
