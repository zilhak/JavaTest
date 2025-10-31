@echo off
echo Compiling...
if not exist bin mkdir bin
javac src/ArrayOperation.java -d bin

echo.
echo Running...
java -cp bin ArrayOperation

pause
