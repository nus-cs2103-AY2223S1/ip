package duke.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Scanner;

import duke.exceptions.CorruptedFileException;
import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasks.Todo;

/**
 * Handles all the storage-related tasks.
 *
 * @author sikai00
 */
public class Storage {
    private static final String CORRUPT_POSTFIX = "-corrupt";

    private final String folderDirectory;
    private final String fileDirectory;
    private final String fileName;

    /**
     * Initializes a new Storage instance.
     * @param folderDirectory Directory of the persistent storage file
     * @param fileName Name of the persistent storage file
     */
    public Storage(String folderDirectory, String fileName) {
        this.folderDirectory = folderDirectory;
        this.fileName = fileName;
        // All persistent storage files are in '.txt' format
        this.fileDirectory = folderDirectory + "/" + fileName + ".txt";
    }

    /**
     * Reads and parses the task list from persistent storage and returns it.
     * If no existing storage file is found, this method returns a new empty TaskList.
     *
     * @return TaskList from persistent storage
     */
    public TaskList readFromStorage() {
        TaskList taskList = new TaskList();
        try {
            File f = new File(this.fileDirectory);
            Scanner sc = new Scanner(f);
            while (sc.hasNextLine()) {
                String taskStr = sc.nextLine();
                taskList.addTask(parseTask(taskStr));
            }
        } catch (CorruptedFileException e) {
            duplicateCorruptFile();
        } catch (FileNotFoundException e) {
            // Allow KarenBot to continue operation on a clean slate
            return new TaskList();
        }
        return taskList;
    }

    /**
     * Parses and returns a Task based on its string representation.
     *
     * @param taskStr String representation of a Task
     * @return Task based on its string representation
     * @throws CorruptedFileException if the data file is corrupted
     */
    private Task parseTask(String taskStr) throws CorruptedFileException {
        String[] tokens = taskStr.split("\\|");
        try {
            String taskType = tokens[0];
            String taskDescription = tokens[1];
            boolean isTaskDone = tokens[2].equals("0") ? false : true;

            switch (taskType) {
            case Todo.TASK_WORD:
                return new Todo(taskDescription, isTaskDone);
            case Deadline.TASK_WORD:
                LocalDateTime by = LocalDateTime.parse(tokens[3]);
                return new Deadline(taskDescription, isTaskDone, by);
            case Event.TASK_WORD:
                LocalDateTime at = LocalDateTime.parse(tokens[3]);
                return new Event(taskDescription, isTaskDone, at);
            default:
                break;
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            // Missing separator usually, causing array index out of bounds
            throw new CorruptedFileException("Corrupted data in storage file!");
        }
        // Other unrelated errors
        throw new CorruptedFileException("Corrupted data in storage file!");
    }

    /**
     * Makes a copy of the storage file and rename it as corrupt.
     * This is to allow the user to fix the storage file manually in the case of corruption,
     * while allowing functionality of KarenBot on a clean state upon file corruption.
     */
    private void duplicateCorruptFile() {
        File originalFile = new File(fileDirectory);
        int counter = 1;
        String corruptFileDirectory = folderDirectory + "/" + this.fileName + CORRUPT_POSTFIX + counter + ".txt";
        File corruptFile = new File(corruptFileDirectory);
        while (corruptFile.exists()) {
            counter++;
            corruptFileDirectory = folderDirectory + "/" + this.fileName + CORRUPT_POSTFIX + counter + ".txt";
            corruptFile = new File(corruptFileDirectory);
        }
        originalFile.renameTo(corruptFile);
    }

    /**
     * Creates a new, blank persistent storage file and writes the input TaskList into the file.
     *
     * @param taskList Input TaskList to read from and write into the persistent storage file
     */
    public void writeAllToStorage(TaskList taskList) {
        File folderDirectory = new File(this.folderDirectory);
        if (!folderDirectory.exists()) {
            folderDirectory.mkdirs();
        }
        try {
            FileWriter fw = new FileWriter(this.fileDirectory, false);
            int lenTaskList = taskList.size();
            for (int i = 0; i < lenTaskList; i++) {
                Task task = taskList.getTask(i);
                String strRepresentation = Storage.taskStrRepresentation(task);
                fw.append(strRepresentation);
            }
            fw.close();
        } catch (IOException e) {
            // TODO: Fix, user will never see this
            assert false;
            System.out.println("Something went wrong: " + e.getMessage());
        }
    }

    /**
     * Appends a new Task to persistent storage.
     */
    public void appendTaskToStorage(Task task) {
        File folderDirectory = new File(this.folderDirectory);
        if (!folderDirectory.exists()) {
            folderDirectory.mkdirs();
        }
        try {
            FileWriter fw = new FileWriter(this.fileDirectory, true);
            String strRepresentation = Storage.taskStrRepresentation(task);
            fw.write(strRepresentation);
            fw.close();
        } catch (IOException e) {
            // TODO: Fix, user will never see this
            assert false;
            System.out.println("Something went wrong: " + e.getMessage());
        }
    }

    /**
     * Returns a string representation of a task safe for use in persistent storage file.
     *
     * @param task Task to be converted into its storage-safe string representation
     * @return Storage-safe string representation of task
     */
    private static String taskStrRepresentation(Task task) {
        String taskType = task.getTaskWord();
        String taskDescription = task.getDescription();
        String taskDone = task.getIsDone() ? "1" : "0";
        String taskTime = task.getTime().map(LocalDateTime::toString).orElse(" ");
        return taskType + "|" + taskDescription + "|" + taskDone + "|" + taskTime + "\n";
    }
}
