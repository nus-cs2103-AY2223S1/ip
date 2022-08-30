package storage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import bocil.BocilException;
import parser.Parser;
import task.Deadline;
import task.Event;
import task.Task;
import task.TaskList;
import task.Todo;

/**
 * Stores the task list in a text file.
 */
public class Storage {

    private final String filePath;
    private final Parser parser;

    /**
     * Constructs a {@link Storage} object.
     *
     * @param fileDirectoryString Folder directory to put the storage in.
     * @param fileName File name of the storage.
     */
    public Storage(String fileDirectoryString, String fileName) {
        this.parser = new Parser();
        File fileDirectory = new File(fileDirectoryString);
        if (!fileDirectory.exists()) {
            fileDirectory.mkdir();
        }
        this.filePath = fileDirectoryString + fileName;
        File file = new File(this.filePath);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    /**
     * Converts the {@code inputLine} into a {@link Task} object.
     *
     * @param inputLine String line that the user inputs.
     * @return One of the 3 {@link Task} object's subclasses.
     * @throws BocilException If inputted date is not of the accepted format.
     */
    public Task createTask(String inputLine) throws BocilException {
        String[] split = inputLine.split("\\|\\|");
        String command = split[0];
        boolean isDone = Boolean.parseBoolean(split[1]);
        Task task;
        switch (command) {
        case "T":
            task = new Todo(split[2], isDone);
            break;
        case "D":
            task = new Deadline(split[2], parser.parseTime(split[3]), isDone);
            break;
        default:
            task = new Event(split[2], parser.parseTime(split[3]), isDone);
        }
        return task;
    }

    /**
     * Reads the stored {@link TaskList} object.
     *
     * @return The last saved {@link TaskList} object.
     * @throws BocilException If inputted date is not of the accepted format.
     */
    public TaskList readFile() throws BocilException {
        TaskList taskList = new TaskList();
        File file = new File(this.filePath);
        try {
            Scanner sc = new Scanner(file);
            while (sc.hasNext()) {
                String line = sc.nextLine();
                taskList.addTask(createTask(line));
            }
        } catch (FileNotFoundException e) {
            throw BocilException.dukeFileNotFoundException();
        }
        return taskList;
    }

    /**
     * Saves the latest version of the {@link TaskList} object.
     *
     * @param taskList List of {@link Task} objects to be saved to the file.
     */
    public void writeFile(TaskList taskList) {
        String text = "";
        for (int i = 0; i < taskList.getSize(); i++) {
            Task task = taskList.getTask(i + 1);
            String line = task.toFileString();
            text = String.format("%s%s\n", text, line);
        }
        try {
            FileWriter fw = new FileWriter(this.filePath);
            fw.write(text);
            fw.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
