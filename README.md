# Java 25.0.1 성능 테스트

Java 25.0.1의 가상 쓰레드 성능 테스트 프로젝트입니다.

## 프로젝트 구조

```
JavaTest/
├── step1/          # 플랫폼 쓰레드 vs 가상 쓰레드
├── step2/          # Map.of vs ConcurrentHashMap (??????)(여러번 실행해보세요)
├── step3/          # 반복문 vs 가상 쓰레드
├── step4/          # 순차/플랫폼/가상 쓰레드 병렬 처리 비교
├── runner.bat      # 전체 실행 (윈도우)
└── runner.sh       # 전체 실행 (리눅스/맥)
```

## 실행 방법

### 전체 실행
```bash
./runner.sh    # 리눅스/맥
runner.bat     # 윈도우
```

### 개별 실행
```bash
cd step1
./run.sh       # 리눅스/맥
run.bat        # 윈도우
```

## 요구사항
- Java 25.0.1 이상
