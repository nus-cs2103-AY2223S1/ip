package duke;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

import duke.task.Task;

/**
 * Implementation of StorageInterface that interacts with a text file for
 * storage.
 */
public class Storage implements StorageInterface {
    private Path filePath;

    /**
     * Constructor for Storage.
     *
     * @param filePath path of storage file.
     */
    public Storage(Path filePath) {
        this.filePath = filePath;
    }

    /**
     * Constructor for Storage.
     *
     * @param filePath path of storage file.
     */
    public Storage(String filePath) {
        this(Paths.get(filePath));
    }

    @Override
    public List<Task> readFile() throws DukeException {
        if (!isFileExist()) {
            throw new DukeException("Storage file is empty!");
        }
        try {
            List<String> taskStringList = Files.readAllLines(filePath);
            List<Task> taskList = new ArrayList<>();
            for (String taskString : taskStringList) {
                taskList.add(StorageParser.parse(taskString));
            }
            return taskList;
        } catch (IOException e) {
            throw new DukeException("Error occured when reading storage file!", e);
        }
    }

    @Override
    public void save(Task task) throws DukeException {
        String toAppend = task.toStorageFormat();
        appendLine(toAppend);
    }

    @Override
    public void updateLine(int lineIndex, String updatedLine) throws DukeException {
        try {
            List<String> lines = Files.readAllLines(filePath, StandardCharsets.UTF_8);
            lines.set(lineIndex, updatedLine);
            Files.write(filePath, lines, StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new DukeException("Error occured when updating storage file!", e);
        }
    }

    @Override
    public void deleteLine(int lineIndex) throws DukeException {
        try {
            List<String> lines = Files.readAllLines(filePath, StandardCharsets.UTF_8);
            lines.remove(lineIndex);
            Files.write(filePath, lines, StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new DukeException("Error occured when updating storage file!", e);
        }
    }

    private boolean isFileExist() {
        return Files.exists(filePath);
    }

    private void createFile() throws DukeException {
        try {
            Files.createDirectories(filePath.getParent());
            Files.createFile(filePath);
        } catch (IOException e) {
            throw new DukeException("Error occured when creating storage file!", e);
        }
    }

    /**
     * Appends line to end of storage file.
     *
     * @param line line of text to append.
     * @throws DukeException when error updating file.
     */
    private void appendLine(String line) throws DukeException {
        if (!isFileExist()) {
            createFile();
        }
        try {
            line += System.lineSeparator();
            Files.write(filePath, line.getBytes(), StandardOpenOption.APPEND);
        } catch (IOException e) {
            throw new DukeException("Error occured when writing to storage file!", e);
        }
    }
}
