#!/usr/bin/env bash
cd "$(dirname "$0")"

# delete output from previous run
if [ -e "./ACTUAL.TXT" ]
then
    rm ACTUAL.TXT
fi

if [ -e "./data" ]
then
    rm -r ./data
fi

# run the program, feed commands from input.txt file and redirect the output to the ACTUAL.TXT
cd ..
./gradlew runcli < text-ui-test/input.txt | head -n -3 | tail -n +6 > text-ui-test/ACTUAL.TXT
cd text-ui-test

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
