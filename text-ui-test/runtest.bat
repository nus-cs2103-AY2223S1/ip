@ECHO OFF
pushd %~dp0

cd ..
call gradlew clean shadowJar
IF ERRORLEVEL 1 (
    echo ********** BUILD FAILURE **********
    exit /b 1
)

cd build\libs
for /f "tokens=*" %%a in (
    'dir /b *.jar'
) do (
    set jarloc=%%a
)

cd ..\..\text-ui-test

REM delete data folder from previous run
if exist data rmdir /s /q data

java -jar ..\build\libs\%jarloc% -cli < input.txt > ACTUAL.TXT

REM compare the output to the expected output
FC ACTUAL.TXT EXPECTED.TXT
