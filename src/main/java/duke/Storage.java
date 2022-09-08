package duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static duke.DukeConstants.KEY_SEPARATOR;

/**
 * This class handles features related to storing tasks.
 */
public class Storage {
    private String filePath;
    private boolean isSaved;

    /**
     * Constructor for the Storage class.
     *
     * @param filePath Filepath to store tasks in.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Writes the tasklist into the file.
     *
     * @param taskList Tasklist to be written.
     * @param ui Ui for display.
     * @return True if data has saved to file and false otherwise.
     */
    public boolean save(TaskList taskList, Ui ui) {
        try {
            FileWriter fw = new FileWriter(filePath, false);
            fw.write(taskList.formatTasks());
            fw.close();
            isSaved = true;
        } catch (IOException e) {
            isSaved = false;
        } finally {
            return isSaved;
        }
    }

    /**
     * Loads the tasklist from the file.
     *
     * @return list of tasks from the file.
     * @throws FileNotFoundException
     */
    public List<Task> load() throws FileNotFoundException {
        File file = new File(filePath);
        Scanner scanner = new Scanner(file);
        List<Task> tasks = new ArrayList<>();
        while(scanner.hasNext()) {
            String dataStr = scanner.nextLine();
            String[] taskStrings = dataStr.split(KEY_SEPARATOR);
            Task task;
            task = populateTask(taskStrings);
            tasks.add(task);
        }
        scanner.close();
        return tasks;
    }

    private Task populateTask(String[] taskStrings) {
        Task task;
        switch (taskStrings[0]) {
            case "T":
                task = new Todo(taskStrings[2]);
                break;
            case "D":
                task = new Deadline(taskStrings[2], LocalDate.parse(taskStrings[3]));
                break;
            case "E":
                task = loadEvent(taskStrings);
                break;
            default:
                task = new Task(taskStrings[2]);
                break;
        }
        if (Integer.parseInt(taskStrings[1]) == 1) {
            task.markAsDone();
        } else {
            task.markAsUndone();
        }
        return task;
    }

    private Task loadEvent(String[] taskDescriptions) {
        Task task;
        String[] timeDescs = taskDescriptions[3].split(" ", 2);
        if(timeDescs.length > 1) {
            LocalDate date = LocalDate.parse(timeDescs[0]);
            task = new Event(taskDescriptions[2], date, timeDescs[1]);
        } else {
            LocalDate date = LocalDate.parse(taskDescriptions[3]);
            task = new Event(taskDescriptions[2], date);
        }
        return task;
    }

    public boolean getSavedStatus() {
        return isSaved;
    }
}
