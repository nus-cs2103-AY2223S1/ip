package duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * Defines <Code>Storage</Code> class.
 * <p>In charge of reading and writing files from disk.</p>
 */
public class Storage {
    /**
     * Keep track of the last-saved version of <Code>TaskList</Code> in its
     * <Code>String</Code> form.
     */
    private String previousSave;

    /** <Code>File</Code> object to save <Code>TaskList</Code>. */
    private final File file;


    /**
     * Constructor for <Code>Storage</Code> object.
     * @param directory Directory to save task list.
     * @param filename  Name of output file.
     * @param taskList  <Code>TaskList</Code> object to save to file.
     */
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
     * Writes given <Code>TaskList</Code> to disk.
     * <p>
     * Compares if there were changes since the last save before writing
     * the file.
     * </p>
     * @param taskList     <Code>TaskList</Code> object to write to disk.
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
     * Adds tasks from lines in file into <Code>Duke</Code>'s
     * <Code>TaskList</Code>.
     * @param data             Single line of data in file.
     * @param lineNumberInFile Line number of current line.
     * @param taskList         <Code>TaskList</Code> object to add tasks to.
     */
    private static void addLineFromFile(String data, int lineNumberInFile,
                                        TaskList taskList) {
        String[] dataArgs = data.split("\\|");
        boolean isDeadlineEntry =
                dataArgs[0].equals("deadline") && dataArgs.length == 5;
        boolean isEventEntry =
                dataArgs[0].equals("event") && dataArgs.length == 5;
        boolean isTodoEntry =
                dataArgs[0].equals("todo") && dataArgs.length == 4;
        boolean hasPriority =
                dataArgs[3].equals("LOW")
                || dataArgs[3].equals("MEDIUM")
                || dataArgs[3].equals("HIGH");
        if (hasPriority && (isDeadlineEntry || isEventEntry || isTodoEntry)) {
            // Only add task if format in file is correct.
            taskList.addFromFile(dataArgs);
        } else {
            System.out.printf("Line %d: Error in format of saved file!%n"
                    + "Line will be ignored.%n%n", lineNumberInFile);
        }
    }
}
