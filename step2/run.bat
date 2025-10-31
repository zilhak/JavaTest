@echo off
echo 컴파일 중...
if not exist bin mkdir bin
javac src/MapComparison.java -d bin

echo.
echo JIT 활성화 상태로 실행 중...
java -cp bin MapComparison

pause
