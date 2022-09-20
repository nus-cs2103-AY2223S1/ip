package amanda.manager;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

import java.util.Scanner;
import java.util.StringTokenizer;

import amanda.exception.InvalidDataException;
import amanda.task.Deadline;
import amanda.task.Event;
import amanda.task.Task;
import amanda.task.TaskState;
import amanda.task.Todo;
import amanda.ui.Ui;

/**
 * StoreManager manages the storage of the task list.
 */
public class StoreManager {

    protected String path;

    /**
     * Constructor for StoreManger class.
     * @param filepath the path to the file which acts as storage for the task list.
     */
    public StoreManager(String filepath) {
        this.path = filepath;
    }

    /**
     * Load the task list stored in the storage file into a new task list.
     */
    public void load() {
        File storage = new File(path);
        if (!storage.exists()) { // if file given in the provided path in the constructor does not exist.
            File directory = new File(storage.getParent());
            directory.mkdir(); // create the parent directories.
        }
        try {
            storage.createNewFile(); // create the file
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        try {
            TaskList.resetList();
            Scanner read = new Scanner(storage); // use scanner to read from the file
            String curr = "";
            try {
                curr = read.nextLine();
            } catch (Exception e) {
                System.out.println("no next line");
            }
            if (curr.isEmpty()) {
                return;
            }
            /*
            Iterate through every line of the storage file and create corresponding
            tasks to add to a new task list. Thus recreating the task list from the
            previous time when amanda was used.
             */
            while (true) {
                StringTokenizer tokens = new StringTokenizer(curr, "/");
                String token = tokens.nextToken();
                switch (token) {
                case "T": {
                    boolean isDone = tokens.nextToken().equals("1");
                    Task task = new Todo(tokens.nextToken());
                    if (task.getDesc().isEmpty()) {
                        throw new InvalidDataException();
                    }
                    if (isDone) {
                        task.doTask();
                    }
                    TaskList.getList().add(task);
                    break;
                }
                case "D": {
                    boolean isDone = tokens.nextToken().equals("1");
                    Task task = new Deadline(tokens.nextToken(), tokens.nextToken());
                    if (task.getDesc().isEmpty()) {
                        throw new InvalidDataException();
                    }
                    if (isDone) {
                        task.doTask();
                    }
                    TaskList.getList().add(task);
                    break;
                }
                case "E": {
                    boolean isDone = tokens.nextToken().equals("1");
                    Task task = new Event(tokens.nextToken(), tokens.nextToken());
                    if (task.getDesc().isEmpty()) {
                        throw new InvalidDataException();
                    }
                    if (isDone) {
                        task.doTask();
                    }
                    TaskList.getList().add(task);
                    break;
                }
                }
                if (!read.hasNextLine()) {
                    break;
                }
                curr = read.nextLine();
            }
            read.close();
        } catch (FileNotFoundException | InvalidDataException e) {
            Ui.addResponse(e.getMessage());
        }
    }

    /**
     * Store the current task list into the storage file.
     */
    public void store() {
        try {
            FileWriter writer = new FileWriter(path); // create PrintWriter object to write to the storage file.

            // Iterate through the current task list and update the storage file with the newest state of the task list
            for (Task t : TaskList.getList()) {
                String state = t.getState() == TaskState.DONE ? "1" : "0";
                String curr = t.getType() + "/" + state + "/" + t.getDesc();
                if (!t.getType().equals("T")) {
                    curr += "/" + t.getTime();
                }
                writer.write(curr + "\n");
            }
            writer.close();
        } catch (IOException e) {
            Ui.addResponse(e.getMessage());
        }
    }
}
