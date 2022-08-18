@ECHO OFF

REM create bin directory if it doesn't exist
if not exist ..\bin mkdir ..\bin

REM delete output from previous run
if exist ACTUAL.TXT del ACTUAL.TXT

REM compile the code into the bin folder
::javac  -cp ..\src\main\java\Utilities -Xlint:none -d ..\bin ..\src\main\java\Utilities\*.java
::javac  -cp ..\src\main\java\DukeProgram -Xlint:none -d ..\bin ..\src\main\java\DukeProgram\*.java
pause
IF ERRORLEVEL 1 (
    echo ********** BUILD FAILURE **********
    exit /b 1
	pause >nul
)
REM no error here, errorlevel == 0

REM run the program, feed commands from input.txt file and redirect the output to the ACTUAL.TXT
java -classpath ..\out\production\ip\ DukeProgram.Duke < input.txt > ACTUAL.TXT

REM compare the output to the expected output
FC ACTUAL.TXT EXPECTED.TXT
pause >nul