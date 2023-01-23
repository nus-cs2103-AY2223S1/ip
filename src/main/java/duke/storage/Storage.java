package duke.storage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

import duke.DukeException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.ToDo;

/**
 * The Storage class encapsulates the file used as a local database and its associated methods.
 */
public class Storage {
    private final String filePath;
    private final File file;

    /**
     * Initializes an instance of Storage.
     * The filePath argument specify the file used as a local database.
     *
     * @param filePath File path to the local database.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
        this.file = new File(filePath);
    }

    /**
     * Retrieves saved tasks from the {@link #file}.
     *
     * @return Task list.
     * @throws DukeException If unable to find, access, or read data from file.
     */
    public TaskList load() throws DukeException {
        TaskList taskList = new TaskList();
        try {
            Scanner sc = new Scanner(file);
            while (sc.hasNextLine()) {
                String currLine = sc.nextLine();

                if (currLine.equals("")) {
                    break;
                }

                taskList.addTask(parseLineFromStorage(currLine));
            }
            sc.close();
            return taskList;
        } catch (FileNotFoundException e) {
            this.createFile();
            return new TaskList();
        } catch (DateTimeParseException e) {
            throw new DukeException("Date in saved file is corrupted.");
        }
    }


    private Task parseLineFromStorage(String line) throws DukeException {
        String[] taskSplit = line.split("\\|", 4);
        Task newTask;
        String taskType = taskSplit[0].trim();
        boolean isDone = taskSplit[1].trim().equals("1");
        String taskDesc = taskSplit[2].trim();
        String taskDate = taskSplit.length == 4 ? taskSplit[3].trim() : "";
        switch (taskType) {
        case "T":
            newTask = new ToDo(taskDesc, isDone);
            break;
        case "D":
            newTask = new Deadline(taskDesc, LocalDate.parse(taskDate), isDone);
            break;
        case "E":
            newTask = new Event(taskDesc, LocalDate.parse(taskDate), isDone);
            break;
        default:
            newTask = null;
            throw new DukeException("Corrupted storage.");
        }
        return newTask;
    }

    /**
     * Creates a new directory and/or a new file for the {@link #filePath}.
     *
     * @throws DukeException If unable to create new file or/and new directory.
     */
    public void createFile() throws DukeException {
        try {
            file.getParentFile().mkdirs();
            file.createNewFile();
        } catch (IOException e) {
            throw new DukeException("An error occurred when creating new file.");
        } catch (SecurityException e) {
            throw new DukeException("Duke does not have access to write to file.");
        }
    }

    /**
     * Overwrites the text stored in the {@link #file}.
     *
     * @param textToReplace Replacement text.
     */
    public void overwriteFile(String textToReplace) {
        try {
            FileWriter fw = new FileWriter(filePath);
            fw.write(textToReplace);
            fw.close();
        } catch (IOException e) {
            // handle exception
            System.out.println("An error occurred when saving the changes to file.");
        }
    }
}
