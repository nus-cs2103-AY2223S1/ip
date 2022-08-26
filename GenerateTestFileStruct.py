# file used to generate test file system 

from genericpath import isdir
import os 

sample_header = '''
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class {} {{

}}
'''

source_folder = "./src/main/java/"
test_folder = "./src/test/java/"

for root, dirs, files in os.walk(source_folder):
    curr_path_in_test = f"{test_folder}{root[len(source_folder):]}"
    print(f"generating dir at {curr_path_in_test}")
    for dir in dirs:
        curr_path = os.path.join(curr_path_in_test, dir)
        if not os.path.isdir(curr_path):
            os.mkdir(curr_path)

    for file in files:
        if (not file.endswith(".java")):
            continue

        with open(os.path.join(root, file)) as f:
            pkg = f.readline();    

        tmp_name = f"{file[:-5]}Test.java"
        curr_path = os.path.join(curr_path_in_test, tmp_name)

        if not os.path.exists(file):
            with open(curr_path, 'w+') as f:
                f.write(pkg)
                f.write(sample_header.format(tmp_name[:-5]))



