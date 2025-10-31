# Step3: For-loop vs Virtual Threads 성능 비교

## 설명
동일한 배열 연산을 단순 for문과 Virtual Thread로 처리했을 때 성능을 비교합니다.

## 테스트 방식
1. **Input 배열**: 1~100 사이의 랜덤값 100만개
2. **연산**: 각 요소를 2로 나눔
3. **방법 1 (For-loop)**: 단순 for문으로 순차 처리 → output1 배열
4. **방법 2 (Virtual Threads)**: Virtual Thread로 병렬 처리 → output2 배열
5. **검증**: 두 output 배열이 동일한지 확인

## 테스트 케이스
- 1,000개 요소 처리
- 10,000개 요소 처리
- 100,000개 요소 처리
- 1,000,000개 요소 처리

## 비교 목적
- **For-loop**: 순차 처리의 단순함과 캐시 효율성
- **Virtual Threads**: 병렬 처리의 오버헤드 vs 잠재적 성능 향상
- 연산이 가벼울 때 멀티스레딩의 오버헤드 확인

## 실행 방법

### 컴파일
```bash
javac src/ArrayOperation.java -d bin
```

### 실행
```bash
java -cp bin ArrayOperation
```

### Windows 배치 파일 사용
```bash
run.bat
```
