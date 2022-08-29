#!/usr/bin/env bash

# create bin directory if it doesn't exist
if [ ! -d "../bin" ]
then
    mkdir ../bin
fi

# delete output from previous run
if [ -e "./ACTUAL.TXT" ]
then
    rm ACTUAL.TXT
fi

if [ -e "./ACTUAL_DATA.TXT" ]
then
    rm ACTUAL_DATA.TXT
fi

# compile the code into the bin folder, terminates if error occurred
if ! javac -cp ../src/main/java -Xlint:none -d ../bin ../src/main/java/**/*.java
then
    echo "********** BUILD FAILURE **********"
    exit 1
fi

# Creates a data directory and loads data.txt
mkdir data
cp data.txt data/tasks.txt

# run the program, feed commands from input.txt file and redirect the output to the ACTUAL.TXT
java -classpath ../bin john.John < input.txt > ACTUAL.TXT

# copies the final saved data
cp data/tasks.txt ACTUAL_DATA.txt
cp EXPECTED_DATA.TXT EXPECTED_DATA-UNIX.TXT

# convert to UNIX format
cp EXPECTED.TXT EXPECTED-UNIX.TXT
#dos2unix ACTUAL.TXT EXPECTED-UNIX.TXT

# delete data directory and files
rm -rf data/

# compare the output to the expected output
diff ACTUAL.TXT EXPECTED-UNIX.TXT
if [ $? -eq 0 ]
then
    echo "Test result: PASSED"
else
    echo "Test result: FAILED"
fi

diff ACTUAL_DATA.TXT EXPECTED_DATA-UNIX.TXT
if [ $? -eq 0 ]
then
    echo "Data test result: PASSED"
    exit 0
else
    echo "Data test result: FAILED"
    exit 1
fi