#!/usr/bin/env bash

# delete output from previous run
if [ -e "./ACTUAL.TXT" ]
then
    rm ACTUAL.TXT
fi

# delete output from previous run
if [ -e "./dummyData.txt" ]
then
    rm ./dummyData.txt
fi


# run the program, feed commands from input.txt file and redirect the output to the ACTUAL.TXT
java -jar ../build/libs/duke.jar  -ng -s ./dummyData.txt < input.txt > ACTUAL.TXT

# convert to UNIX format
# cp EXPECTED.TXT EXPECTED-UNIX.TXT
# dos2unix EXPECTED.TXT EXPECTED-UNIX.TXT

exitState=0

# compare the output to the expected output
diff ACTUAL.TXT EXPECTED-UNIX.TXT
if [ $? -eq 0 ]
then
    echo "Test result: PASSED"
else
    echo "Test result: FAILED"
    exitState=1
fi

# convert to UNIX format
cp SAVEFILEEXPECTED.TXT SAVEFILEEXPECTED-UNIX.TXT
dos2unix SAVEFILEEXPECTED-UNIX.TXT SAVEFILEEXPECTED-UNIX.TXT

# compare the output to the expected output
diff dummyData.txt SAVEFILEEXPECTED-UNIX.TXT
if [ $? -eq 0 ]
then
    echo "Test result: PASSED"
else
    echo "Test result: FAILED"
    exitState=1
fi

if [ $exitState -eq 1 ]
then
    exit 1
else
    exit 0
fi
