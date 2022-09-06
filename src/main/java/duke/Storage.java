package duke;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import duke.task.Task;

/**
 * Class to encapsulate the Storage of the program.
 */
public class Storage {

    /**
     * Loads the file containing task list.
     * If file not found, create new file.
     *
     */
    public void loadStorage(String filePath, TaskList taskList) {
        File f = new File(filePath);
        Parser p = new Parser();
        try {
            Scanner sc = new Scanner(f);
            while (sc.hasNext()) {
                Task task = p.fileLoadParser(sc.nextLine());
                taskList.add(task, false);
            }
        } catch (IOException e) {
            System.out.println("file not found, we will create one for you");
        }
    }

    /**
     * Writes the list of tasks to the output file.
     * Checks if directory to file exists, create it if directory does not exist.
     *
     * @param filePath The path to the file to write to.
     * @param taskList The task list temporarily storing all tasks.
     */
    public void writeToTaskList(String filePath, TaskList taskList) {
        try {
            FileWriter writer = new FileWriter(filePath);
            int i = 0;
            while (i < taskList.getsize()) {
                writer.write(taskList.get(i).tofileString());
                writer.write(System.getProperty("line.separator"));
                i++;
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("Directory does not exist, creating new directory now");
            File f = new File("data");
            assert f.mkdir() : "mkdir should return true";
            writeToTaskList(filePath, taskList);
        }
    }
}
