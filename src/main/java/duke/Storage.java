package duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

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
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return new ArrayList<>();
    }

}
