package duke;

import java.time.LocalDateTime;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Deals with loading tasks from the file and saving tasks in the file.
 */
public class Storage {

    private String filePath;

    /**
     * Constructor of Storage class.
     *
     * @param filePath String of file to read from and save to.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Saves tasks to file.
     *
     * @param tasklist Tasklist to be saved.
     * @throws DukeException If file is unable to be saved.
     */
    public void save(TaskList tasklist) throws DukeException {
        try {
            FileWriter fw = new FileWriter(this.filePath);
            for (int i = 0; i < tasklist.getSize(); i++) {
                fw.write(tasklist.getTask(i).printSavedData());
            }
            fw.close();
        } catch (IOException e) {
            throw new DukeException("Oh no! I'm unable to save the file.");
        }
    }

    /**
     * Loads tasks from file.
     *
     * @return Tasklist containing tasks.
     * @throws DukeException If file cannot be created.
     * @throws FileNotFoundException  If file cannot be found.
     */
    public TaskList load() throws DukeException, FileNotFoundException {
        ArrayList<Task> taskArr = new ArrayList<>();
        int tdCount = 0;
        int dCount = 0;
        int eCount = 0;
        File file = new File(this.filePath);
        try {
            if (!file.exists()) {
                file.getParentFile().mkdirs();
            }
            if (file.createNewFile()) {
                System.out.println("File created");
            }
        } catch (Exception de) {
            throw new DukeException("New file can't be created");
        }

        try {
            File dukeFile = new File(filePath);
            Scanner sc = new Scanner(dukeFile);

            while (sc.hasNextLine()) {
                String[] strArr = sc.nextLine().split("\\|");
                if (strArr[0].contains("T")) {
                    tdCount++;
                    String subStr = strArr[2].substring(1, strArr[2].length() - 1);
                    ToDos todo = new ToDos(subStr);
                    checkIfDone(strArr[1], todo);
                    taskArr.add(todo);
                }
                if (strArr[0].contains("E")) {
                    String str = strArr[3];
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern(" dd MMM yyyy HH:mm");
                    LocalDateTime ldt = LocalDateTime.parse(str, formatter);
                    eCount++;
                    String subStr = strArr[2].substring(1, strArr[2].length() - 1);
                    Events event = new Events(subStr, ldt);
                    checkIfDone(strArr[1], event);
                    taskArr.add((event));
                }
                if (strArr[0].contains("D")) {
                    String str = strArr[3];
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern(" dd MMM yyyy HH:mm");
                    LocalDateTime ldt = LocalDateTime.parse(str, formatter);
                    dCount++;
                    String subStr = strArr[2].substring(1, strArr[2].length() - 1);
                    Deadlines deadline = new Deadlines(subStr, ldt);
                    checkIfDone(strArr[1], deadline);
                    taskArr.add(deadline);
                }
            }
            sc.close();

            return new TaskList(taskArr, tdCount, dCount, eCount);
        } catch (FileNotFoundException fe) {
            throw new FileNotFoundException("Oops! No file is found.");
        }
    }

    /**
     * Checks if task is marked as done.
     *
     * @param str String of whether task is done.
     * @param task Task being checked if done.
     */
    public static void checkIfDone(String str, Task task) {
        if (str.contains("X")) {
            task.isDone = true;
        } else {
            task.isDone = false;
        }
    }

}
