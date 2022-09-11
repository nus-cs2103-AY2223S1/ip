@ECHO OFF

REM create bin directory if it doesn't exist
if not exist ..\bin mkdir ..\bin

REM delete output from previous run
if exist ACTUAL.TXT del ACTUAL.TXT
if exist data\tasks.txt del data\tasks.txt

REM compile the code
call "..\gradlew.bat" build
IF ERRORLEVEL 1 (
   echo ********** BUILD FAILURE **********
   exit /b 1
)
REM no error here, errorlevel == 0

REM run the program, feed commands from input.txt file and redirect the output to the ACTUAL.TXT
REM idea adapted from https://github.com/nus-cs2103-AY2223S1/forum/issues/36
REM idea also adapted from https://github.com/nus-cs2103-AY2223S1/forum/issues/129
REM idea also adapted from https://stackoverflow.com/questions/24722757/how-to-call-a-batch-file-that-is-one-level-up-from-the-current-directory
call "..\gradlew.bat" run -p .. --args console-test -q < input.txt > ACTUAL.TXT
call "..\gradlew.bat" run -p .. --args console-test -q < input2.txt >> ACTUAL.TXT

REM compare the output to the expected output
FC ACTUAL.TXT EXPECTED.TXT
