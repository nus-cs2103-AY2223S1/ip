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

# compile the code into the bin folder, terminates if error occurred
if ! javac -cp ../src/main/java -Xlint:none -d ../bin ../src/main/java/duke/*.java ../src/main/java/duke/task/*.java
then
    echo "********** BUILD FAILURE **********"
    exit 1
fi

# delete data
if [ -e "./duke.txt" ]
then
    rm duke.txt
fi
if [ -e "./dukeNotes.txt" ]
then
    rm dukeNotes.txt
fi

# run the program, feed commands from input.txt file and redirect the output to the ACTUAL.TXT
java -classpath ../bin duke.Duke < input.txt > ACTUAL.TXT

# convert to UNIX format
cp EXPECTED.TXT EXPECTED-UNIX.TXT
dos2unix ACTUAL.TXT EXPECTED-UNIX.TXT

# delete output from previous run
if [ -e "./ACTUAL1.TXT" ]
then
    rm ACTUAL1.TXT
fi

# run the program, feed commands from input.txt file and redirect the output to the ACTUAL1.TXT
java -classpath ../bin duke.Duke < input.txt > ACTUAL1.TXT

# convert to UNIX format
cp EXPECTED1.TXT EXPECTED1-UNIX.TXT
dos2unix ACTUAL1.TXT EXPECTED1-UNIX.TXT

# compare the output to the expected output
diff ACTUAL.TXT EXPECTED-UNIX.TXT
if [ $? -eq 0 ]
then
    # compare the output to the expected output
    diff ACTUAL1.TXT EXPECTED1-UNIX.TXT
    if [ $? -eq 0 ]
    then
        echo "Test result: PASSED"
        exit 0
    else
        echo "Test result: FAILED SECOND"
        exit 1
    fi
else
    echo "Test result: FAILED"
    exit 1
fi