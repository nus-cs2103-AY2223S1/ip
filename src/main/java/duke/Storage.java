package duke;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Class for loading and saving tasks.
 *
 * @author dexter-sim
 * @version 0.1
 */
public class Storage {
    private String filePath;
    private File file;

    /**
     * Creates a storage object of the specified file path.
     *
     * @param filePath Path from root directory to the file.
     * @throws DukeException If unable to create file.
     */
    public Storage(String filePath) throws DukeException {
        this.filePath = filePath;
        file = new File(this.filePath);
        try {
            // Does not overwrite directory or file if it already exists.
            file.getParentFile().mkdir();
            file.createNewFile();
        } catch (IOException e) {
            throw new DukeException("Directory or file cannot be located. New file is created.");
        }
    }

    /**
     * Returns a list of tasks from storage.
     *
     * @return A list of tasks.
     * @throws DukeException If file is inaccessible.
     */
    public List<Task> load() throws DukeException {
        List<Task> tasks = new ArrayList<>();
        try {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNext()) {
                tasks.add(parse(scanner.nextLine()));
            }
            scanner.close();
            return tasks;
        } catch (FileNotFoundException e) {
            throw new DukeException("File is inaccessible");
        }
    }

    /**
     * Saves specified list of tasks to local file.
     *
     * @param tasks
     */
    public void save(List<Task> tasks) {
        assert tasks != null;
        List<String> data = new ArrayList<>();
        for (Task task : tasks) {
            data.add(task.stringify());
        }

        try {
            FileWriter fw = new FileWriter(filePath);
            fw.write(String.join(System.lineSeparator(), data));
            fw.close();
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
    }

    private Task parse(String str) throws DukeException {
        try {
            String[] entry = str.split(" \\| ");
            if (entry[0].equals("T")) {
                return new ToDo(entry[2], entry[1].equals("X"));
            }
            if (entry[0].equals("D")) {
                return new Deadline(entry[2], entry[1].equals("X"), LocalDate.parse(entry[3]));
            }
            if (entry[0].equals("E")) {
                return new Event(entry[2], entry[1].equals("X"), LocalDate.parse(entry[3]));
            }
        } catch (DateTimeParseException | ArrayIndexOutOfBoundsException e) {
            throw new DukeException("Invalid string in file.");
        }
        throw new DukeException("Invalid string in file.");
    }
}
