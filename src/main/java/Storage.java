import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Storage {
    private String filePath;
    private Ui ui;

    public Storage(String filePath) {
        this.filePath = filePath;
        this.ui = ui;
    }

    public ArrayList<Task> load() throws DukeException {
        ArrayList<Task> tasks = new ArrayList<Task>();
        File dataFile = new File(this.filePath);
        try {
            if (dataFile.exists()) {
                Scanner scanner = new Scanner(dataFile);
                while (scanner.hasNext()) {
                    Task currentTask;
                    String[] taskLine = scanner.nextLine().split(Pattern.quote(" | "));
                    String taskDescription = taskLine[2];
                    switch (taskLine[0]) {
                        case "[T]":
                            currentTask = new Todo(taskDescription);
                            tasks.add(currentTask);
                            break;
                        case "[E]":
                            currentTask = new Event(taskDescription, taskLine[3]);
                            tasks.add(currentTask);
                            break;
                        case "[D]":
                            currentTask = new Deadline(taskDescription, taskLine[3]);
                            tasks.add(currentTask);
                            break;
                    }
                }
            }
        } catch (IOException e) {
            throw new DukeException("Loading Error!");
        }
        return tasks;
    }

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
