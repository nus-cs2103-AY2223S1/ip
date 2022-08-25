@ECHO OFF

REM create bin directory if it doesn't exist
if not exist ..\bin mkdir ..\bin

REM delete output from previous run
if exist ACTUAL.TXT del ACTUAL.TXT
if exist .\data\tasks.txt del .\data\tasks.txt

REM compile the code into the bin folder
javac -cp ..\src\main\java -Xlint:none -d ..\bin ..\src\main\java\duke\command\*.java
javac -cp ..\src\main\java -Xlint:none -d ..\bin ..\src\main\java\duke\task\*.java
javac -cp ..\src\main\java -Xlint:none -d ..\bin ..\src\main\java\duke\*.java

IF ERRORLEVEL 1 (
    echo ********** BUILD FAILURE **********
    exit /b 1
)
REM no error here, errorlevel == 0

REM run the program, feed commands from input1.txt file and redirect the output to the ACTUAL.TXT
java -classpath ..\bin Duke < input1.txt > ACTUAL.TXT

REM compare the output data to the expected output data
FC .\data\tasks.txt .\data\EXPECTED_DATA1.TXT

REM run the program, feed commands from input2.txt file and append the output to the ACTUAL.TXT
java -classpath ..\bin Duke < input2.txt >> ACTUAL.TXT

REM compare the final output to the expected final output
FC ACTUAL.TXT EXPECTED.TXT
FC .\data\tasks.txt .\data\EXPECTED_DATA2.TXT

pause
