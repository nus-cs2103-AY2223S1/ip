package bloop;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Deals with files.
 */
public class Storage {

    private static final String FOLDER_PATH = "./data/";
    private static final String FILE_NAME = "bloopData.text";
    private static final String FILE_PATH = FOLDER_PATH + FILE_NAME;

    /**
     * Creates a file if not already created.
     *
     * @param tasks List of tasks.
     */
    public void makeFile(ArrayList<Task> tasks) {
        try {
            File folder = new File(FOLDER_PATH);
            File file = new File(FILE_PATH);
            if (!folder.exists()) {
                folder.mkdir();
            }
            boolean isCreated = file.createNewFile();
            if (!isCreated) {
                addPrevTasks(tasks);
            }
        } catch (IOException e) {
            System.out.println("Couldn't create file");
            System.exit(0);
        }
    }

    /**
     * Adds tasks to the file.
     *
     * @param task Task to be added to the file.
     * @throws IOException If there is a problem writing to the file.
     */
    public void writeToFile(Task task) throws IOException {
        FileWriter fw = new FileWriter(FILE_PATH, true);
        fw.write(task.getType() + "-"
                + (task.getStatus() ? "1" : "0") + "-" + task.getTask() + "-" + task.getBy() + "\n");
        fw.close();
    }

    /**
     * Updates the file with the changed list.
     *
     * @param tasks List of tasks.
     * @throws IOException If there is a problem writing to the file.
     */
    public void rewriteFile(ArrayList<Task> tasks) throws IOException {
        FileWriter fw = new FileWriter(FILE_PATH);
        fw.write("");
        for (Task task : tasks) {
            writeToFile(task);
        }
        fw.close();
    }

    private void addPrevTasks(ArrayList<Task> tasks) throws FileNotFoundException {
        File file = new File(FILE_PATH);
        Scanner sc = new Scanner(file);
        while (sc.hasNext()) {
            String[] taskArr = sc.nextLine().split("-");
            String type = taskArr[0];
            Task task;
            if (type.equals("T")) {
                task = new ToDo(taskArr[2]);
            } else if (type.equals("E")) {
                task = new Event(taskArr[2], taskArr[3]);
            } else {
                task = new Deadline(taskArr[2], taskArr[3]);
            }
            if (Integer.parseInt(taskArr[1]) == 1) {
                task.mark();
            }
            tasks.add(task);
        }
    }
}
