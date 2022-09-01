package dukepro.handlers;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

import dukepro.tasks.Task;

/**
 * Class for Storage.
 */
public class Storage {
    private String line = "_______________________________________";

    private File directory;
    private File tasklist;
    private FileWriter fileWriter;
    private PrintWriter printWriter;

    /**
     * Creates a Storage class.
     * Creates director "data" amd file "tasklist.txt"
     * if not present.
     *
     * @return A Storage.
     */
    public Storage() { // creates new files if needed
        try {
            directory = new File("data");
            if (!directory.exists()) {
                directory.mkdir();
            }

            tasklist = new File("data/tasklist.txt");
            if (!tasklist.exists()) {
                tasklist.createNewFile();
            }
            fileWriter = new FileWriter(this.tasklist, true);
            printWriter = new PrintWriter(fileWriter);
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    /**
     * Reads data from "data/tasklist/txt" into
     * the taskmanager.
     *
     * @param tasksManager The Tasksmanager to read data into.
     * @return A boolean on whether reading was successful.
     */
    public boolean readfile(TasksManager tasksManager) {
        try {
            Scanner sc = new Scanner(this.tasklist);
            while (sc.hasNextLine()) {
                String data = sc.nextLine();
                tasksManager.addTaskNoPrint(Decoder.parseFromFile(data));
            }
            sc.close();
            return true;
        } catch (FileNotFoundException e) {
            System.out.println("error reading file");
            return false;
        }
    }

    /**
     * Writes a task to data/tasklist.txt.
     *
     * @param task Task to be recorded.
     * @return void.
     */
    public void addTask(Task task) {
        printWriter.println(task.fileForm());
    }

    /**
     * Rewrites all files in the printwriter to
     * reflect latest tasks in taskmanager.
     *
     * @param tasks An Arraylist containing the
     *              most updated tasks.
     * @return A boolean.
     */
    public boolean rewriteFile(ArrayList<Task> tasks) {
        //delete all file contents
        printWriter.flush();
        try {
            printWriter = new PrintWriter(new FileWriter(this.tasklist, false));
            printWriter.close();
        } catch (IOException e) {
            System.out.println(e);
        }

        printWriter = new PrintWriter(fileWriter);
        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            printWriter.println(task.fileForm());
        }
        return true;
    }

    /**
     * Closes printwriter and writes all data to
     * the data/tasklist.txt file.
     *
     * @return void.
     */
    public void end() {
        this.printWriter.close();
    }
}
