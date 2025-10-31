@echo off
echo 컴파일 중...
if not exist bin mkdir bin
javac src/ArrayOperation.java -d bin

echo.
echo 실행 중...
java -cp bin ArrayOperation

pause
