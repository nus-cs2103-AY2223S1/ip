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
if [ -e "data\tasks.txt" ]
then
    rm data\tasks.txt
fi

# compile the code into the bin folder, terminates if error occurred
# @@author cheeheng-reused
# Solution adapted from https://stackoverflow.com/questions/31011069/executing-a-script-from-a-parent-directory
if ! (cd .. && gradlew build)
then
    echo "********** BUILD FAILURE **********"
    exit 1
fi

# run the program, feed commands from input.txt file and redirect the output to the ACTUAL.TXT
gradlew run -p .. --args console-test -q < input.txt > ACTUAL.TXT
gradlew run -p .. --args console-test -q < input2.txt >> ACTUAL.TXT

# convert to UNIX format
cp EXPECTED.TXT EXPECTED-UNIX.TXT
dos2unix ACTUAL.TXT EXPECTED-UNIX.TXT

# compare the output to the expected output
diff ACTUAL.TXT EXPECTED-UNIX.TXT
if [ $? -eq 0 ]
then
    echo "Test result: PASSED"
    exit 0
else
    echo "Test result: FAILED"
    exit 1
fi