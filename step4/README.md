# Step4: 순차/플랫폼 쓰레드/가상 쓰레드 병렬 처리 비교

## 코드
백만개 요소 배열에 대해 복잡한 CPU 연산을 수행합니다.

- **For-loop**: 순차 처리 (싱글 스레드)
- **Platform Thread**: 32개 스레드로 배열을 나눠서 병렬 처리
- **Virtual Thread**: 10,000개 가상 스레드로 배열을 나눠서 병렬 처리

각 요소는 `Math.sqrt()`, `Math.sin()`, `Math.cos()`, `Math.log()` 연산을 포함합니다.

## 실행 방법
```bash
./run.sh    # 리눅스/맥
run.bat     # 윈도우
```
