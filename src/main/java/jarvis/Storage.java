package jarvis;

import jarvis.task.*;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * Wraps the methods and information for Jarvis to interact
 * with database
 */
public class Storage {
    static File dir = new File("data");
    static File taskListFile = new File(dir, "task_list.txt");
    private File dataFile;

    public Storage(String file_path) {
        File file = new File(file_path);
        file.getParentFile().mkdir();
        this.dataFile = file;
        try {
            dataFile.createNewFile();
        } catch (IOException e) {
            System.out.println("Exception in Storage when creating data file");
        }

    }

    public static void checkFileExists() throws IOException {
        // System.out.println(dir.exists());
        if (! dir.exists()) {
            dir.mkdir();
        }
        if (! taskListFile.exists()) {
            taskListFile.createNewFile();
        }
    }

    /**
     * Save the added task to database
     * @param task The task to store
     * @throws IOException If something went wrong when writing file
     */
    public void saveAddedTask(Task task) throws IOException {
        FileWriter fw = new FileWriter(dataFile, true);
        fw.write(task.toDataForm());
        fw.close();
    }

    /**
     * Rewrite the whole datalist to data file again,
     * this is needed when a task is modified or deleted
     * @param taskList The whole task list to store
     * @throws IOException If something went wrong when writing file
     */
    public void saveTaskList(TaskList taskList) throws IOException {
        FileWriter fw = new FileWriter(dataFile);
        for (int i = 0; i < taskList.getTaskCount(); ++i) {
            fw.write(taskList.getTask(i).toDataForm());
        }
        fw.close();
    }

    /**
     * Load the task list from database
     * @return The task list stored in the database
     * @throws JarvisException If something went wrong when loading
     */
    public TaskList loadTaskList() throws JarvisException {
        TaskList taskList = new TaskList(this);
        try {
            //checkFileExists();

            Scanner taskScanner = new Scanner(dataFile);
            while (taskScanner.hasNextLine()) {
                String taskStr = taskScanner.nextLine();
                if (taskStr.equals("")) {
                    break;
                }
                String[] taskInfoArr = taskStr.split("\\|");
                boolean isDone = taskInfoArr[1].equals("1");
                if (taskInfoArr[0].equals("T")) {
                    taskList.appendLoadedTask(new Todo(taskInfoArr[2], isDone));
                } else if (taskInfoArr[0].equals("E")) {
                    taskList.appendLoadedTask(new Event(taskInfoArr[2], taskInfoArr[3], isDone));
                } else if (taskInfoArr[0].equals("D")) {
                    taskList.appendLoadedTask(new Deadline(taskInfoArr[2], taskInfoArr[3], isDone));
                } else {
                    throw new RuntimeException("Something wrong with task types in data file");
                }
            }
        } catch (Exception e) {
            System.out.println("Error when loading data" + e);
            throw new JarvisException(e.getMessage());
        }
        return taskList;
    }
}
