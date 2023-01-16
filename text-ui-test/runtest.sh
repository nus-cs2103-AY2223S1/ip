#!/usr/bin/env bash

# change to script directory
cd "${0%/*}" || exit

cd ..
if ! ./gradlew clean shadowJar
then
    echo "********** BUILD FAILURE **********"
    exit 1
fi

cd text-ui-test || exit

# delete data folder from previous run
if [ -e "./data" ]
then
    rm -r data
fi

java -jar "$(find ../build/libs/ -mindepth 1 -print -quit)" -cli < input.txt > ACTUAL.TXT

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
