@ECHO OFF

REM create bin directory if it doesn't exist
if not exist ..\bin mkdir ..\bin

REM delete output from previous run
if exist ACTUAL.txt del ACTUAL.txt

REM delete savefile from previous run
if exist data\save.txt del data\save.txt

REM compile the code into the bin folder
javac -cp ..\src\main\java -Xlint:none -d ..\bin -encoding utf8 ..\src\main\java\duke\*.java ..\src\main\java\duke\command\*.java ..\src\main\java\duke\parser\*.java ..\src\main\java\duke\storage\*.java ..\src\main\java\duke\task\*.java ..\src\main\java\duke\ui\*.java
IF ERRORLEVEL 1 (
    echo ********** BUILD FAILURE **********
    exit /b 1
)
REM no error here, errorlevel == 0

REM run the program, feed commands from input.txt file and redirect the output to the ACTUAL.TXT
java -Dfile.encoding=UTF-8 -classpath ..\bin duke.Duke < input.txt > ACTUAL.txt

REM compare the output to the expected output
FC ACTUAL.TXT EXPECTED.TXT
