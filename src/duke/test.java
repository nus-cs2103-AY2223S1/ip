package duke;

import duke.task.Task;
import duke.task.Todo;

import java.awt.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;

public class test {

    public static final String DEFAULT_STORAGE_FILE = "tasklist.txt";
    public static final String DEFAULT_STORAGE_SOURCE_FOLDER = "src";
    public static final String DEFAULT_STORAGE_DUKE_FOLDER = "duke";
    public static final String CURRENT_DIRECTORY = "user.dir";
    public static final String DIVIDER = " | ";
    public static void main(String[] args) throws IOException {

        String currentDir = System.getProperty(CURRENT_DIRECTORY);
        Path path = Paths.get(currentDir, DEFAULT_STORAGE_SOURCE_FOLDER, DEFAULT_STORAGE_DUKE_FOLDER,DEFAULT_STORAGE_FILE);
        PrintWriter pw = new PrintWriter(path.toFile());
        pw.println("T" + DIVIDER + "0" + DIVIDER + "hahhh" + DIVIDER + null);
        pw.println("D" + DIVIDER + "0" + DIVIDER + "dfdfdfdf" + DIVIDER + "June 6th");

        pw.flush();
        pw.close();
        List<String> str = Files.readAllLines(path);
        System.out.println(str);


    }
}
