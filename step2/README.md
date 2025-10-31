# Step2: Map.of vs ConcurrentHashMap 성능 비교 (Virtual Threads)

## 설명
Java 25.0.1의 Virtual Thread 환경에서 Map.of (불변)과 ConcurrentHashMap (가변)의 성능을 비교합니다.

두 맵 모두 10개의 엔트리를 포함합니다 (Map.of의 최대 크기).

**JIT 최적화 방지**: `volatile` 전역 변수와 hashCode() 연산을 사용하여 JIT 컴파일러가 맵 읽기를 제거하지 못하도록 합니다.

## 테스트 케이스
- 쓰레드당 1,000번 반복
- 쓰레드당 10,000번 반복
- 쓰레드당 100,000번 반복
- 쓰레드당 1,000,000번 반복

각 테스트는 10,000개의 Virtual Thread를 생성하여 맵 읽기 연산을 수행합니다.

## 실행 방법

### 컴파일
```bash
javac src/MapComparison.java -d bin
```

### 실행
```bash
java -cp bin MapComparison
```

### Windows 배치 파일 사용
```bash
run.bat
```
