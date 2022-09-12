package duke;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.Todo;

/**
 * Represents an encapsulation that deals with loading tasks from the save file
 * and saving tasks in the file
 */
public class Storage {
    private static final String SEPARATOR = ",";

    private File file;

    /**
     * Initialises the storage object.
     *
     * @param filePath Path to the save file
     */
    public Storage(String filePath) {
        this.file = new File(filePath);
    }

    /**
     * Creates a new file.
     *
     * @return file A newly created {@link File}
     */
    private File createFile() throws DukeException {
        if (!file.exists()) {
            try {
                file.getParentFile().mkdir();
                file.createNewFile();
            } catch (IOException e) {
                throw new DukeException("I/O error");
            } catch (SecurityException e) {
                throw new DukeException("Cannot write. No permissions");
            } catch (Exception e) {
                throw new DukeException("Exception. Don't know the specific type");
            }
        }
        return this.file;
    }

    private void parseLine(String line, TaskList taskList) {
        String[] strArray = line.split(",");
        switch (strArray[0]) {
        case "Todo": {
            Task task = new Todo(strArray[1], Boolean.parseBoolean(strArray[2]));
            taskList.add(task);
            break;
        }
        case "Event": {
            Task task = new Event(strArray[1], Boolean.parseBoolean(strArray[2]), strArray[3]);
            taskList.add(task);
            break;
        }
        case "Deadline": {
            Task task = new Deadline(strArray[1], Boolean.parseBoolean(strArray[2]), strArray[3]);
            taskList.add(task);
            break;
        }
        default:
            break;
        }
    }

    /**
     * Saves TaskList to a file.
     *
     * @param taskList TaskList
     */
    public void saveFile(TaskList taskList) throws DukeException {
        File file = createFile();
        assert file.exists() : "File should exists";
        PrintWriter writer;
        try {
            writer = new PrintWriter(file);
        } catch (FileNotFoundException e) {
            throw new DukeException("File not found");
        } catch (Exception e) {
            throw new DukeException("Exception. Don't know the specific type");
        }
        for (int i = 0; i < taskList.size(); ++i) {
            Task task = taskList.get(i);
            String[] data = task.getPrintRepresentation();
            writer.println(Arrays.stream(data).reduce("", (x, y) -> x + SEPARATOR + y).substring(1));
        }
        writer.close();
    }

    /**
     * Reads save file and returns TaskList.
     *
     * @return TaskList where its tasks are those in the save file
     */
    public TaskList readFile() throws DukeException {
        File file = createFile();
        assert file.exists() : "File should exists";
        TaskList taskList = new TaskList();
        BufferedReader reader;

        try {
            FileReader fr = new FileReader(this.file);
            reader = new BufferedReader(fr);
            String line;

            while ((line = reader.readLine()) != null) {
                parseLine(line, taskList);
            }
            reader.close();
        } catch (Exception e) {
            throw new DukeException("Error");
        }
        return taskList;
    }
}
