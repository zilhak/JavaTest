@echo off
echo Compiling...
if not exist bin mkdir bin
javac src/MapComparison.java -d bin

echo.
echo Running...
java -cp bin MapComparison

pause
