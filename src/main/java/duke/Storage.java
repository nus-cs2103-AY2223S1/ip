package duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import duke.task.Task;


/**
 * Contains file with specified pathname and related methods.
 */
public class Storage {

    private final File file;

    /**
     * Constructor for storage.
     * @param filePath of stored file.
     */
    public Storage(String filePath) {
        this.file = new File(filePath);
    }

    /**
     * Writes tasks into storage file.
     * @param tasklist TaskList to be written.
     */
    public static void listToFile(ArrayList<Task> tasklist) {
        try {
            FileWriter fw = new FileWriter("./data/dukedata.txt");
            for (Task t : tasklist) {
                fw.write(t.taskToFileString() + System.lineSeparator());
            }
            fw.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Creates parent directories if it does not exist.
     * @param parentFolder parent directory of file that needs to be created.
     */
    public void makeParentDirectories(File parentFolder) {
        File parentFile = parentFolder.getParentFile();
        if (parentFile.exists()) {
            parentFolder.mkdir();
        } else {
            makeParentDirectories(parentFile);
            parentFolder.mkdir();
        }
    }

    /**
     * Initialises the file by checking for and creating missing directories and the file.
     * @return TaskList from reading the file.
     */
    public ArrayList<Task> setFile() {
        File parentFolder = this.file.getParentFile();
        if (parentFolder.exists()) {
            if (this.file.exists()) {
                return scanExistingFile();
            } else {
                return createNewFile();
            }
        } else {
            makeParentDirectories(parentFolder);
            return createNewFile();
        }
    }

    /**
     * Scans existing file for tasks.
     * @return ArrayList containing the tasks.
     */
    public ArrayList<Task> scanExistingFile() {
        ArrayList<Task> tempTaskList = new ArrayList<>();
        try {
            Scanner sc = new Scanner(file);
            while (sc.hasNext()) {
                String curr = sc.nextLine();
                Task addTask = Parser.fileStringToTask(curr);
                if (addTask != null) {
                    tempTaskList.add(addTask);
                }
            }
            return tempTaskList;
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Creates a new file at file object's file path.
     * @return Empty ArrayList for TaskList.
     */
    public ArrayList<Task> createNewFile() {
        try {
            this.file.createNewFile();
            createSampleTasks();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return scanExistingFile();
    }

    /**
     * Populates a file wih sample tasks.
     */
    public void createSampleTasks() {
        try {
            FileWriter fw = new FileWriter(file);
            fw.write(" T | 0 | this is a sample task" + System.lineSeparator());
            fw.write(" D | 1 | this is a sample deadline | 09/10/2022 1000");
            fw.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
