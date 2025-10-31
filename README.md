# Java 25.0.1 Performance Test Projects

이 프로젝트는 Java 25.0.1의 Virtual Thread 기능을 테스트하기 위한 독립적인 프로젝트들을 포함합니다.

## 프로젝트 구조

```
JavaTest/
├── step1/          # Platform Thread vs Virtual Thread 성능 비교
│   ├── src/
│   │   └── ThreadComparison.java
│   ├── README.md
│   └── run.bat
│
├── step2/          # Map 성능 비교 (10개 엔트리)
│   ├── src/
│   │   └── MapComparison.java
│   ├── README.md
│   └── run.bat
│
└── step3/          # Map 성능 비교 (3개 엔트리)
    ├── src/
    │   └── MapComparison.java
    ├── README.md
    └── run.bat
```

## 프로젝트 설명

### Step1: Platform Thread vs Virtual Thread
- 일반 플랫폼 쓰레드와 가상 쓰레드의 성능을 비교합니다
- 10,000개의 쓰레드를 생성하여 간단한 정수 증가 연산을 수행합니다
- 다양한 부하(1천, 1만, 10만, 100만 반복)에서 테스트합니다

### Step2: Map.of vs ConcurrentHashMap (10개 엔트리)
- 가상 쓰레드 환경에서 불변 맵(Map.of)과 동시성 맵(ConcurrentHashMap)의 성능을 비교합니다
- **맵 크기**: 10개 엔트리 (Map.of의 최대 크기)
- **JIT 활성화**: 실제 운영 환경과 동일한 최적화 적용
- 10,000개의 가상 쓰레드에서 맵 읽기 연산을 수행합니다
- ConcurrentHashMap의 JIT 최적화 효과를 확인할 수 있습니다

### Step3: Map.of vs ConcurrentHashMap (3개 엔트리)
- 가상 쓰레드 환경에서 불변 맵(Map.of)과 동시성 맵(ConcurrentHashMap)의 성능을 비교합니다
- **맵 크기**: 3개 엔트리 (작은 크기)
- **JIT 활성화**: 실제 운영 환경과 동일한 최적화 적용
- 10,000개의 가상 쓰레드에서 맵 읽기 연산을 수행합니다
- Map.of의 작은 크기 최적화 장점을 확인할 수 있습니다

## 실행 방법

각 step 디렉토리로 이동한 후:

### Windows
```bash
run.bat
```

### 수동 실행
```bash
# 컴파일
javac src/*.java -d bin

# 실행
java -cp bin [MainClassName]
```

## 요구사항
- Java 25.0.1 이상

## 주의사항
- 타이밍 측정은 쓰레드 초기화 이후부터 시작됩니다
- 모든 쓰레드가 작업을 완료한 후 시간을 측정합니다
- 결과는 밀리초(ms) 단위로 출력됩니다
