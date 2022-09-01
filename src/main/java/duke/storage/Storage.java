package duke.storage;

import duke.others.DukeException;
import duke.parser.Parser;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Represents a storage place that deals with loading tasks
 * from the file and saving tasks in the file.
 */
public class Storage {

    private String filePath;

    /**
     * Constructs a Storage instance with a specified file path.
     *
     * @param filePath File path in local disk.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Appends the given text to the file.
     *
     * @param textToAppend Text to be appended.
     * @throws DukeException If the specified file cannot be written to.
     */
    public void appendToFile(String textToAppend) throws DukeException {
        try {
            FileWriter fw = new FileWriter(filePath, true);
            fw.write(textToAppend + "\n");
            fw.close();
        } catch (IOException e) {
            throw new DukeException("☹ Error occurs when writing to local file");
        }
    }

    /**
     * Overwrites the file with content stored in the task list.
     *
     * @param tasks Task list whose content is used in overriding.
     * @throws DukeException If the specified file cannot be written to.
     */
    public void overwriteFile(TaskList tasks) throws DukeException {
        try {
            FileWriter fw = new FileWriter(filePath);
            StringBuilder textToAdd = new StringBuilder();
            for (Task task : tasks.getTasks()) {
                textToAdd.append(task.toString()).append(System.lineSeparator());
            }
            fw.write(textToAdd.toString());
            fw.close();
        } catch (IOException e) {
            throw new DukeException("☹ Error occurs when writing to local file");
        }
    }

    /**
     * Loads content of the specified file in the local disk into an ArrayList,
     * and returns the ArrayList with parsed tasks inside.
     *
     * @return ArrayList that contains tasks stored in the local file.
     * @throws DukeException If the specified file is not found in local disk.
     */
    public List<Task> load() throws DukeException {
        try {
            File file = new File(filePath);
            Scanner s = new Scanner(file);
            List<Task> tasks = new ArrayList<>();
            while (s.hasNext()) {
                String str = s.nextLine();
                Task task = fileInterpreter(str);
                tasks.add(task);
            }
            return tasks;
        } catch (FileNotFoundException e) {
            throw new DukeException();
        }
    }

    /**
     * Interprets the information stored in the local file.
     * Converts the information into a task.
     * Returns the task.
     *
     * @param str A line in the local file.
     * @return A task instance.
     * @throws DukeException If information in the local file cannot be understood.
     */
    public Task fileInterpreter(String str) throws DukeException {
        Task task;
        String description = str.split("] ", 2)[1];
        if (str.contains("[T]")) {
            task = new ToDo(description);
        } else {
            String message = str.substring(str.indexOf("]", str.indexOf("]") + 1) + 2,
                    str.indexOf(" ("));
            String dateString = str.substring(str.indexOf(":") + 2, str.indexOf(")"));
            LocalDate localDate = Parser.parseDateFormats(dateString);
            if (str.contains("[D]")) {
                task = new Deadline(message, localDate);
            } else {
                task = new Event(message, localDate);
            }
        }
        if (str.contains("[X]")) {
            task.markAsDone();
        }
        return task;
    }

}
