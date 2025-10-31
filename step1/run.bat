@echo off
echo 컴파일 중...
if not exist bin mkdir bin
javac src/ThreadComparison.java -d bin

echo.
echo 실행 중...
java -cp bin ThreadComparison

pause
