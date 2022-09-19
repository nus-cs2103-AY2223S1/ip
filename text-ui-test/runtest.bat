@ECHO OFF

REM delete output from previous run
if exist ACTUAL.TXT del ACTUAL.TXT

REM delete output from previous run
if exist dummyData.txt del dummyData.txt

REM run the program, feed commands from input.txt file and redirect the output to the ACTUAL.TXT
java -jar ../build/libs/duke.jar  -ng -s ./dummyData.txt < input.txt > ACTUAL.TXT

REM compare the output to the expected output
FC ACTUAL.TXT EXPECTED.TXT

REM compare the output to the expected output
FC dummyData.txt SAVEFILEEXPECTED.TXT
