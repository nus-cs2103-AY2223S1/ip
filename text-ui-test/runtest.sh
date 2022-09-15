#!/usr/bin/env bash

# create bin directory if it doesn't exist
if [ ! -d "../bin" ]
then
    mkdir ../bin
fi

# delete output from previous run
if [ -e "./text-ui-test/ACTUAL.TXT" ]
then
    rm ./text-ui-test/ACTUAL.TXT
fi

# compile the code into the bin folder, terminates if error occurred
if ! ./gradlew assemble
then
    echo "********** BUILD FAILURE **********"
    exit 1
fi

cd ./text-ui-test/

# delete old storage file
rm data/duke.txt

# run the program and generate storage file
java -classpath ../bin Duke < preinput.txt > /dev/null

# run the program, feed commands from input.txt file and redirect the output to the ACTUAL.TXT
java -classpath ../bin Duke < input.txt > ACTUAL.TXT

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