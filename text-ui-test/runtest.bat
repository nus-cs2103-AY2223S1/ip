@ECHO OFF

REM create bin directory if it doesn't exist
REM if not exist ..\bin mkdir ..\bin

REM delete output from previous run
if exist ACTUAL.TXT del ACTUAL.TXT

REM compile the code into the bin folder
REM javac  -cp ..\src\main\java -Xlint:none -d ..\bin ..\src\main\java\*.java
REM IF ERRORLEVEL 1 (
REM     echo ********** BUILD FAILURE **********
REM     exit /b 1
REM )
REM no error here, errorlevel == 0

REM run the program, feed commands from input.txt file and redirect the output to the ACTUAL.TXT
java -classpath ..\out\production\ip Duke < input.txt > ACTUAL.TXT

REM compare the output to the expected output
FC ACTUAL.TXT EXPECTED.TXT
