#!/usr/bin/env fish

begin
# create bin directory if it doesn't exist
if [ ! -d "../bin" ]
    mkdir ../bin
end

# delete output from previous run
if [ -e "./ACTUAL.TXT" ]
    rm ACTUAL.TXT
end

# compile the code into the bin folder, terminates if error occurred
if ! javac -cp ../src/main/java -Xlint:none -d ../bin ../src/main/java/**.java
    echo "********** BUILD FAILURE **********"
    exit 1
end

# run the program, feed commands from input.txt file and redirect the output to the ACTUAL.TXT
java -classpath ../bin duke.Main < input.txt > ACTUAL.TXT

# convert to UNIX format
cp EXPECTED.TXT EXPECTED-UNIX.TXT
dos2unix ACTUAL.TXT EXPECTED-UNIX.TXT

# compare the output to the expected output
diff ACTUAL.TXT EXPECTED-UNIX.TXT
if [ $status -eq 0 ]
    echo "Test result: PASSED"
    exit 0
else
    echo "Test result: FAILED"
    exit 1
end
end