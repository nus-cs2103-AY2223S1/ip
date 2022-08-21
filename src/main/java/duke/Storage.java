package main.java.duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Storage {

    /**
     * Keep track of the last-saved version of `TaskList` in its String form.
     */
    private String previousSave;

    /**
     * Path of the file.
     */
    private final File file;


    public Storage(String directory, String filename, TaskList taskList) {
        int lineNumberInFile = 1;
        file = new File(directory + "/" + filename);

        try {
            // Try to read task list from file.
            Scanner sc = new Scanner(file);
            while (sc.hasNextLine()) {
                String data = sc.nextLine();
                if (!data.equals("")) {
                    // Parse non-empty lines and add the tasks to 'taskList'.
                    addLineFromFile(data, lineNumberInFile, taskList);
                }
                lineNumberInFile++;
            }
            previousSave = taskList.toString();
            sc.close();
        } catch (FileNotFoundException e) {
            // If file does not exist, check if directory exists. If
            // directory does not exist, create the directory.
            File outDir = new File(directory);
            if (!outDir.exists()) {
                outDir.mkdir();
            }
        }
    }


    /**
     * Method to write `taskList` to disk.
     * @param taskList     `TaskList` object to write to disk.
     * @throws IOException Thrown if error occurs while writing file.
     */
    public void writeToFile(TaskList taskList) throws IOException {
        if (taskList.toString().equals(previousSave)) {
            // Only write to file if there was a change.
            return;
        }
        FileWriter fw = new FileWriter(file);
        fw.write(taskList.toFile());
        fw.close();
    }

    /**
     * Method to add tasks from lines in file.
     * @param data             Single line of data in file.
     * @param lineNumberInFile Line number of current line.
     * @param taskList         `TaskList` object to add tasks to.
     */
    private static void addLineFromFile(String data, int lineNumberInFile,
                                        TaskList taskList) {
        String[] dataArgs = data.split("\\|");
        if ((dataArgs[0].equals("deadline") && dataArgs.length == 4) ||
                (dataArgs[0].equals("event") && dataArgs.length == 4) ||
                (dataArgs[0].equals("todo") && dataArgs.length == 3)) {
            // Only add task if format in file is correct.
            taskList.addFromFile(dataArgs);
        } else {
            System.out.printf("Line %d: Error in format of saved file!%n" +
                    "Line will be ignored.%n%n", lineNumberInFile);
        }
    }
}
