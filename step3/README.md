# Step3: Map.of vs ConcurrentHashMap 성능 비교 (작은 맵 크기)

## 설명
Java 25.0.1의 Virtual Thread 환경에서 Map.of (불변)과 ConcurrentHashMap (가변)의 성능을 비교합니다.

**작은 맵 크기**: 두 맵 모두 **3개의 엔트리**만 포함합니다.

**Map.of의 장점**:
- 작은 크기(1~10개)에 특화된 최적화
- 단순한 배열 기반 구조로 캐시 친화적
- ConcurrentHashMap의 해싱/동기화 오버헤드 없음

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
