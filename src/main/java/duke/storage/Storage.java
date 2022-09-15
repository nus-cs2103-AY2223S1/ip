package duke.storage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import duke.exceptions.DukeException;
import duke.exceptions.StorageException;
import duke.parser.Parser;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

/**
 * Represents a storage place that deals with loading tasks
 * from the file and saving tasks in the file.
 */
public class Storage {

    private final String filePath;

    private final Path path;

    /**
     * Constructs a Storage instance with a specified file path.
     *
     * @param filePath File path in local disk.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
        path = Paths.get(filePath);
    }

    /**
     * Appends the given text to the file.
     *
     * @param textToAppend Text to be appended.
     * @throws StorageException If the specified file cannot be written to.
     */
    public void appendToFile(String textToAppend) throws StorageException {
        try {
            FileWriter fw = new FileWriter(filePath, true);
            fw.write(textToAppend + "\n");
            fw.close();
        } catch (IOException e) {
            throw new StorageException("Error occurs when writing to local file!");
        }
    }

    /**
     * Overwrites the file with content stored in the task list.
     *
     * @param tasks Task list whose content is used in overriding.
     * @throws StorageException If the specified file cannot be written to.
     */
    public void overwriteFile(TaskList tasks) throws StorageException {
        try {
            FileWriter fw = new FileWriter(filePath);
            StringBuilder textToAdd = new StringBuilder();
            for (Task task : tasks.getTasks()) {
                textToAdd.append(task.toString()).append(System.lineSeparator());
            }
            fw.write(textToAdd.toString());
            fw.close();
        } catch (IOException e) {
            throw new StorageException("Error occurs when writing to local file!");
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
            initialise();
            File file = new File(filePath);
            Scanner scanner = new Scanner(file);
            List<Task> tasks = new ArrayList<>();
            while (scanner.hasNext()) {
                String str = scanner.nextLine();
                Task task = interpretFileContent(str);
                tasks.add(task);
            }
            return tasks;
        } catch (IOException e) {
            throw new StorageException("Error occurs when loading the file!");
        }
    }

    /**
     * Checks whether a directory or file.
     *
     * @throws IOException If the target file cannot be created.
     */
    private void initialise() throws IOException {
        // @@author TZL0-reused
        // The following two methods are reused from
        // https://tinyurl.com/github-TZL0
        // with minor modifications
        checkDirectory();
        checkFile();
        // @@author
    }


    /**
     * Checks whether directory exists.
     * Creates one if it does not exist.
     */
    private void checkDirectory() {
        File file = path.getParent().toFile();
        if (!file.exists()) {
            file.mkdirs();
        }
    }

    /**
     * Checks whether file exists.
     * Creates one if it does not exist.
     *
     * @throws IOException If the target file cannot be created.
     */
    private void checkFile() throws IOException {
        File file = path.toFile();
        if (!file.exists()) {
            file.createNewFile();
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
    public Task interpretFileContent(String str) throws DukeException {

        Task task;
        boolean isFormatWithDate = str.matches(
                "^\\[[DET]]+\\[[X| ]]+ +(\\w+ )+\\([by|at]+: +[A-Z]+[a-z]{2}+ \\d{2}+ \\d{4}+\\)");
        boolean isFormatWithoutDate = str.matches("^\\[[DET]]+\\[[X| ]]+( \\w+)+");

        if (isFormatWithDate) {
            String message = str.substring(str.indexOf("]", str.indexOf("]") + 1) + 2,
                    str.indexOf(" ("));
            String dateString = str.substring(str.indexOf(":") + 2, str.indexOf(")"));
            LocalDate localDate = Parser.parseDateFormats(dateString);
            if (str.contains("[D]")) {
                task = new Deadline(message, localDate);
            } else {
                task = new Event(message, localDate);
            }
        } else if (isFormatWithoutDate) {
            String description = str.split("] ", 2)[1];
            task = new ToDo(description);
        } else {
            throw new StorageException("File corrupted! What's wrong with you?"
                    + System.lineSeparator() + "Delete the data/duke.txt file!");
        }

        if (str.contains("[X]")) {
            task.markAsDone();
        }

        return task;
    }

}
