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
    private String archiveFilePath;

    /**
     * Constructor for the Storage class.
     *
     * @param filePath Filepath to store tasks in.
     */
    public Storage(String filePath, String archiveFilePath) {
        this.filePath = filePath;
        this.archiveFilePath = archiveFilePath;
    }

    /**
     * Writes the tasklist into the main data file.
     *
     * @param taskList Tasklist to be written.
     * @return True if data has saved to file and false otherwise.
     */
    public boolean saveData(TaskList taskList) {
        return save(taskList, filePath, false);
    }

    /**
     * Writes the tasklist into the archive file.
     *
     * @param taskList Tasklist to be written.
     * @return True if data has saved to file and false otherwise.
     */
    public boolean saveToArchive(TaskList taskList) {
        return save(taskList, archiveFilePath, true);
    }

    /**
     * Writes the task into the archive file.
     *
     * @param task Task to be written.
     * @return True if data has saved to file and false otherwise.
     */
    public boolean saveTaskToArchive(Task task) {
        TaskList taskList = new TaskList();
        taskList.addTask(task);
        return save(taskList, archiveFilePath, true);
    }

    private boolean save(TaskList taskList, String fileName, boolean append) {
        try {
            FileWriter fw = new FileWriter(fileName, append);
            fw.write(taskList.formatTasks());
            fw.close();
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    /**
     * Loads the tasklist from the main data file.
     *
     * @return list of tasks from the file.
     * @throws FileNotFoundException when there is an error with the file.
     */
    public List<Task> loadData() throws FileNotFoundException {
        return load(filePath);
    }

    /**
     * Loads the tasklist from the archive.
     *
     * @return list of tasks from the file.
     * @throws IOException when there is an i/o exception.
     */
    public List<Task> loadFromArchive() throws IOException {
        List<Task> tasks = load(archiveFilePath);
        clearData(archiveFilePath);
        return tasks;
    }

    private List<Task> load(String fileName) throws FileNotFoundException {
        File file = new File(fileName);
        Scanner scanner = new Scanner(file);
        List<Task> tasks = new ArrayList<>();
        while (scanner.hasNext()) {
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
        if (timeDescs.length > 1) {
            LocalDate date = LocalDate.parse(timeDescs[0]);
            task = new Event(taskDescriptions[2], date, timeDescs[1]);
        } else {
            LocalDate date = LocalDate.parse(taskDescriptions[3]);
            task = new Event(taskDescriptions[2], date);
        }
        return task;
    }

    /**
     * Clears the data in the file.
     *
     * @param fileName File to be cleared.
     * @throws IOException when there is an IO Exception.
     */
    public void clearData(String fileName) throws IOException {
        FileWriter fw = new FileWriter(fileName, false);
        fw.close();
    }

}
