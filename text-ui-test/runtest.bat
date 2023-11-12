@ECHO OFF

REM create bin directory if it doesn't exist
if not exist ..\bin mkdir ..\bin

REM delete output from previous run
if exist ACTUAL.TXT del ACTUAL.TXT

REM compile the code into the bin folder
javac  -cp ..\src\main\java -Xlint:none -d ..\bin ..\src\main\java\anthea\*.java ..\src\main\java\anthea\exception\*.java ..\src\main\java\anthea\note\*.java ..\src\main\java\anthea\task\*.java
IF ERRORLEVEL 1 (
    echo ********** BUILD FAILURE **********
    exit /b 1
)
REM no error here, errorlevel == 0

REM delete data
if exist anthea.txt del anthea.txt
if exist antheaNotes.txt del antheaNotes.txt

REM run the program, feed commands from input.txt file and redirect the output to the ACTUAL.TXT
java -classpath ..\bin anthea.Anthea < input.txt > ACTUAL.TXT

REM compare the output to the expected output
FC ACTUAL.TXT EXPECTED.TXT

REM delete output from previous run
if exist ACTUAL1.TXT del ACTUAL1.TXT

REM run the program, feed commands from input.txt file and redirect the output to the ACTUAL1.TXT
java -classpath ..\bin anthea.Anthea < input.txt > ACTUAL1.TXT

REM compare the output to the expected output
FC ACTUAL1.TXT EXPECTED1.TXT
