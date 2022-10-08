package luffy;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Pattern;

/**
 * Storage class to handle save file.
 *
 * @author Silas Tay (A0233425M)
 */
public class Storage {
    private String filePath;
    private Ui ui;

    /**
     * Constructor for Storage class.
     *
     * @param filePath File path of save file
     */
    public Storage(String filePath) {
        this.filePath = filePath;
        this.ui = ui;
    }

    /**
     * Loads data from save file if exists.
     *
     * @return ArrayList of Tasks from save file
     * @throws DukeException When there is an error loading the file
     */
    public ArrayList<Task> load() throws DukeException {
        ArrayList<Task> tasks = new ArrayList<Task>();

        File dataFolder = new File("./data");
        if (!dataFolder.exists()) {
            dataFolder.mkdir();
        }

        File dataFile = new File(this.filePath);
        try {
            if (dataFile.exists()) {
                Scanner scanner = new Scanner(dataFile);
                while (scanner.hasNext()) {
                    Task currentTask = null;
                    String[] taskLine = scanner.nextLine().split(Pattern.quote(" | "));
                    boolean isTaskDone = taskLine[1].equals("1");
                    String taskDescription = taskLine[2];
                    switch (taskLine[0]) {
                    case "[T]":
                        currentTask = new Todo(taskDescription);
                        tasks.add(currentTask);
                        break;
                    case "[E]":
                        LocalDate at = LocalDate.parse(taskLine[3]);
                        currentTask = new Event(taskDescription, at);
                        tasks.add(currentTask);
                        break;
                    case "[D]":
                        LocalDate by = LocalDate.parse(taskLine[3]);
                        currentTask = new Deadline(taskDescription, by);
                        tasks.add(currentTask);
                        break;
                    default:
                        break;
                    }
                    if (isTaskDone) {
                        currentTask.markCompleted();
                    }
                }
            }
        } catch (IOException e) {
            throw new DukeException("Loading Error!");
        }
        return tasks;
    }

    /**
     * Updates save file.
     *
     * @param tasks Tasklist
     * @param filePath File path to save file
     */
    public void updateSaveFile(TaskList tasks, String filePath) {
        try {
            PrintWriter pw = new PrintWriter(this.filePath);
            pw.print("");
            for (int i = 0; i < tasks.getSize(); i++) {
                Task currentTask = tasks.getTask(i);
                File dataFile = new File(filePath);
                dataFile.createNewFile();
                FileWriter fw = new FileWriter(this.filePath, true);
                String dataLine = currentTask.type + " | "
                        + (currentTask.isDone ? "1" : "0") + " | "
                        + currentTask.description
                        + (currentTask.by != null ? " | " + currentTask.by : "")
                        + (currentTask.at != null ? " | " + currentTask.at : "");
                fw.write(dataLine + "\n");
                fw.close();
            }
            pw.close();
        } catch (IOException e) {
            ui.printErrorMessage("Error saving data file.");
        }
    }
}
