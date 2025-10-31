@echo off
echo ===================================
echo 모든 단계 순차 실행
echo ===================================
echo.

echo [1단계] 플랫폼 쓰레드 vs 가상 쓰레드
echo -----------------------------------
cd step1
call run.bat
cd ..
echo.

echo [2단계] Map.of vs ConcurrentHashMap (??????)(여러번 실행해보세요)
echo -----------------------------------
cd step2
call run.bat
cd ..
echo.

echo [3단계] 반복문 vs 가상 쓰레드
echo -----------------------------------
cd step3
call run.bat
cd ..
echo.

echo [4단계] 순차/플랫폼/가상 쓰레드 병렬 처리 비교
echo -----------------------------------
cd step4
call run.bat
cd ..
echo.

echo ===================================
echo 모든 단계 완료!
echo ===================================
pause
