@ECHO OFF

REM create bin directory if it doesn't exist
if not exist ..\bin mkdir ..\bin

REM delete output from previous run
if exist ACTUAL.TXT del ACTUAL.TXT

REM compile the code into the bin folder
cd ..\src\main\java
javac -cp . -Xlint:none -d ../../../bin stashy/*.java
IF ERRORLEVEL 1 (
    echo ********** BUILD FAILURE **********
    exit /b 1
)
REM no error here, errorlevel == 0

REM run the program, feed commands from input.txt file and redirect the output to the ACTUAL.TXT
cd ..\..\..\text-ui-test
SET FILE = "src\main\data\tasks.txt"
IF EXIST %FILE% DEL /F %FILE%
java -classpath ..\bin stashy.Stashy < input.txt > ACTUAL.TXT

REM compare the output to the expected output
FC ACTUAL.TXT EXPECTED.TXT
