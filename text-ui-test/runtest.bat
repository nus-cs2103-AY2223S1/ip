@ECHO OFF

REM create bin directory if it doesn't exist
if not exist ..\bin mkdir ..\bin

REM delete output from previous run
if exist ACTUAL.TXT del ACTUAL.TXT

REM compile the code into the bin folder
javac  -cp ..\src\main\java -Xlint:none -d ..\bin ..\src\main\java\duke\exception\*.java
javac  -cp ..\src\main\java -Xlint:none -d ..\bin ..\src\main\java\duke\task\*.java
javac  -cp ..\src\main\java -Xlint:none -d ..\bin ..\src\main\java\duke\ui\*.java
javac  -cp ..\src\main\java -Xlint:none -d ..\bin ..\src\main\java\duke\list\*.java
javac  -cp ..\src\main\java -Xlint:none -d ..\bin ..\src\main\java\duke\storage\*.java
javac  -cp ..\src\main\java -Xlint:none -d ..\bin ..\src\main\java\duke\command\*.java
javac  -cp ..\src\main\java -Xlint:none -d ..\bin ..\src\main\java\duke\parser\*.java
javac  -cp ..\src\main\java -Xlint:none -d ..\bin ..\src\main\java\duke\*.java

IF ERRORLEVEL 1 (
    echo ********** BUILD FAILURE **********
    exit /b 1
)
REM no error here, errorlevel == 0

REM run the program, feed commands from input.txt file and redirect the output to the ACTUAL.TXT
java -classpath ..\bin Duke < input.txt > ACTUAL.TXT

REM compare the output to the expected output
FC ACTUAL.TXT EXPECTED.TXT
