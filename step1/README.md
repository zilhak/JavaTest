# Step1: Platform Thread vs Virtual Thread 성능 비교

## 설명
Java 25.0.1의 Platform Thread와 Virtual Thread의 성능을 비교합니다.

## 테스트 케이스
- 쓰레드당 1,000번 반복
- 쓰레드당 10,000번 반복
- 쓰레드당 100,000번 반복
- 쓰레드당 1,000,000번 반복

각 테스트는 10,000개의 쓰레드를 생성하여 정수 합산 연산을 수행합니다.

**JIT 최적화 방지**: `volatile` 전역 변수를 사용하여 JIT 컴파일러가 연산을 제거하지 못하도록 합니다.

## 실행 방법

### 컴파일
```bash
javac src/ThreadComparison.java -d bin
```

### 실행
```bash
java -cp bin ThreadComparison
```

### Windows 배치 파일 사용
```bash
run.bat
```
