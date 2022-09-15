package duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.Scanner;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

/**
 * Storage loads and saves data from a specific path.
 */
public class Storage {
    protected File file;

    /**
     * Creates a new Storage from the file path.
     * @param filePath The path to the file that data will be stored in.
     */
    public Storage(String filePath) {
        assert !filePath.isEmpty() : "File path cannot be empty!";
        this.file = new File(filePath);
    }

    /**
     * Loads data from the savefile.
     * @return List of tasks.
     * @throws DukeException
     */
    public TaskList getData() throws DukeException {
        TaskList tasks = new TaskList();
        Scanner sc;
        try {
            sc = new Scanner(file);
        } catch (FileNotFoundException e) {
            throw new DukeException("File not found: Unable to retrieve data.");
        }
        while (sc.hasNext()) {
            try {
                Task task = getTaskFromLine(sc.nextLine());
                tasks.add(task);
            } catch (NoSuchElementException e) {
                sc.close();
                throw new DukeException("File content is not in the correct format.");
            }
        }
        sc.close();
        return tasks;
    }

    private Task getTaskFromLine(String line) throws DukeException {
        String[] splitStrings = line.split("( \\| )");
        if (splitStrings.length < 2 && splitStrings.length > 3) {
            throw new DukeException("File content is not in the correct format.");
        }
        String type = splitStrings[0];
        String status = splitStrings[1];
        String description = splitStrings[2];
        Task task;
        switch (type) {
        case "T":
            task = new Todo(description);
            break;
        case "D":
            task = new Deadline(description, splitStrings[3]);
            break;
        case "E":
            task = new Event(description, splitStrings[3]);
            break;
        default:
            throw new DukeException("Invalid task type: %s", type);
        }
        if (status.equals("1")) {
            task.markAsDone();
        } else if (!status.equals("0")) {
            throw new DukeException("Invalid task status: %s", status);
        }
        return task;
    }

    /**
     * Saves data into the savefile.
     * @param tasks List of tasks.
     * @throws DukeException
     */
    public void saveData(TaskList tasks) throws DukeException {
        try {
            FileWriter fileWriter = new FileWriter(file);
            String content = tasks.getTasks().stream()
                    .map(x -> x.getSaveFormat() + "\n")
                    .reduce("", (x, y) -> x + y);
            fileWriter.write(content);
            fileWriter.close();
        } catch (IOException e) {
            throw new DukeException("Unable to save data.");
        }
    }
}
