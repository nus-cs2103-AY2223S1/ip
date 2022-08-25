package storage;

import duke.DukeException;
import parser.Parser;
import task.Task;
import task.Todo;
import task.Deadline;
import task.Event;
import task.TaskList;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileNotFoundException;

import java.util.Scanner;

/**
 * Stores the task list in a text file.
 */
public class Storage {

    protected final String filePath;
    protected final Parser parser;

    /**
     * Constructs a {@code Storage} object.
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
     * Converts the {@code inputLine} into a {@code Task} object.
     *
     * @param inputLine String line that the user inputs.
     * @return One of the 3 {@code Task} object subclass.
     * @throws DukeException If inputted date is not of the accepted format.
     */
    public Task createTask(String inputLine) throws DukeException {
        String[] split = inputLine.split("\\|\\|");
        String command = split[0];
        boolean isDone = Boolean.parseBoolean(split[1]);
        Task task = null;
        switch (command) {
        case "T":
            task = new Todo(split[2], isDone);
            break;
        case "D":
            task = new Deadline(split[2], parser.parseTime(split[3]), isDone);
            break;
        case "E":
            task = new Event(split[2], parser.parseTime(split[3]), isDone);
            break;
        }
        return task;
    }

    /**
     * Reads the stored {@code TaskList} object.
     *
     * @return The last saved {@code TaskList} object.
     * @throws DukeException If inputted date is not of the accepted format.
     */
    public TaskList readFile() throws DukeException {
        TaskList taskList = new TaskList();
        File file = new File(this.filePath);
        try {
            Scanner sc = new Scanner(file);
            while (sc.hasNext()) {
                String line = sc.nextLine();
                taskList.addTask(createTask(line));
            }
        } catch (FileNotFoundException e) {
            throw DukeException.DukeFileNotFoundException();
        }
        return taskList;
    }

    /**
     * Saves the latest version of the {@code taskList}.
     *
     * @param taskList List of {@code Task} objects to be saved to the file.
     */
    public void writeFile(TaskList taskList) {
        String text = "";
        for (int i=0; i<taskList.getSize(); i++) {
            Task task = taskList.getTask(i+1);
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