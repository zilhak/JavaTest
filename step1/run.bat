@echo off
echo Compiling...
if not exist bin mkdir bin
javac src/ThreadComparison.java -d bin

echo.
echo Running...
java -cp bin ThreadComparison

pause
