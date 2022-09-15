package duke.storage;

import duke.exceptions.DukeException;
import duke.tasklist.TaskList;
import duke.tasks.Task;
import duke.tasks.Deadline;
import duke.tasks.Todo;
import duke.tasks.Event;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class Storage {

    String filePath;

    /**
     * Standard constructor for Duke's Storage tool.
     *
     * @param filePath Location of Duke's data.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads data from file into a new TaskList.
     *
     * @return Duke's TaskList initialised with task data.
     * @throws DukeException If data could not be read.
     */
    public TaskList load() throws DukeException {
        // Base: If filepath is null, there is no file to read
        if (filePath == null) {
            throw new DukeException("Error - No file path specified for Storage to retrieve from!");
        }

        // Create new file, if exists, doesn't do anything
        try {
            File newFile = new File(filePath);
            newFile.createNewFile();
        } catch (Exception e) {
            throw new DukeException("An error occurred when trying to create a new file...");
        }

        // Init arrayList
        List<Task> newList = new ArrayList<>();

        // Prep file for reading
        try {
            File dukeData = new File(filePath);
            Scanner sc = new Scanner(dukeData);
            while (sc.hasNextLine()) {
                String data = sc.nextLine();
                String[] parsedData = data.split(",,");

                if (parsedData.length != 4) {
                    throw new DukeException("Error reading from file: " + filePath + ". Perhaps " +
                            "the data is stored in an incorrect manner?");
                }
                String type = parsedData[0];
                boolean isDone;
                try {
                    isDone = Boolean.parseBoolean(parsedData[1]);
                } catch (Exception e) {
                    throw new DukeException("Error reading from file: " + filePath + ": Unknown " +
                            "boolean format for stored tasks");
                }

                // TODO: More error checking for description and datetimeinfo?
                String description = parsedData[2];
                String dateTimeInfo = parsedData[3];

                switch (type) {
                case "TODO":
                    newList.add(new Todo(description, isDone));
                    break;
                case "DEADLINE":
                    newList.add(new Deadline(description, isDone, dateTimeInfo));
                    break;
                case "EVENT":
                    newList.add(new Event(description, isDone, dateTimeInfo));
                    break;
                default:
                    throw new DukeException("Error reading from file: " + filePath + ": Unknown " +
                            "task type");
                }
            }
            sc.close();
        } catch (FileNotFoundException e) {
            throw new DukeException(e.getMessage());
        }
        return new TaskList(newList);
    }

    /**
     * Saves taskList to file on drive.
     *
     * @param taskList List to fetch save data from
     * @throws DukeException if saving data fails
     */
    public void save(TaskList taskList) throws DukeException {
        try {
            FileWriter fileWriter = new FileWriter(filePath);
            fileWriter.write(taskList.getWriteString());
            fileWriter.close();
        } catch (IOException e) {
            throw new DukeException("An error occurred when writing data out :(");
        }
    }

    @Override
    public String toString() {
        return "Duke Storage currently loaded with file at " + filePath;
    }
}
