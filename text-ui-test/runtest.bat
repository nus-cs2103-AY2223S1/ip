@ECHO OFF

SET MY_PATH=C:\Users\Shawn Kok\Documents\CS2100 ip\ip

REM create bin directory if it doesn't exist
if not exist "%MY_PATH%\bin" mkdir "%MY_PATH%\bin"

REM delete output from previous run
if exist ACTUAL.TXT del ACTUAL.TXT

REM compile the code into the bin folder
javac  -cp "%MY_PATH%/src/main/java" -Xlint:none -d "%MY_PATH%\bin" "%MY_PATH%/src/main/java/Sakura/Sakura.java"
IF ERRORLEVEL 1 (
    echo ********** BUILD FAILURE **********
    exit /b 1
)
REM no error here, errorlevel == 0

REM run the program, feed commands from input.txt file and redirect the output to the ACTUAL.TXT
java -classpath "%MY_PATH%\bin" Sakura.Sakura < input.txt > ACTUAL.TXT

REM compare the output to the expected output
FC ACTUAL.TXT EXPECTED.TXT
