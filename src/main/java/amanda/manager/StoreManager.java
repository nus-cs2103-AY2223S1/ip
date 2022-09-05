package main.java.amanda.manager;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.StringTokenizer;

import main.java.amanda.task.Deadline;
import main.java.amanda.task.Event;
import main.java.amanda.task.Task;
import main.java.amanda.task.Todo;

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
     * @param taskList the new task list.
     */
    public void load(TaskList taskList) {
        if (Files.notExists(Paths.get(path))) { // if file given in the provided path in the constructor does not exist.
            File file = new File(this.path);
            file.getParentFile().mkdirs(); // create the parent directories.
            try {
                file.createNewFile(); // create the file
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            File file = new File(this.path);
            Scanner read = null;
            try {
                read = new Scanner(file); // use scanner to read from the file
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            assert read != null;
            String curr = read.nextLine();
            /*
            Iterate through every line of the storage file and create corresponding
            tasks to add to a new task list. Thus recreating the task list from the
            previous time when amanda was used.
             */
            while (true) {
                StringTokenizer tokens = new StringTokenizer(curr, "/");
                String token = tokens.nextToken();
                if (token.equals("T")) {
                    boolean isDone = tokens.nextToken().equals("1");
                    Task task = new Todo(tokens.nextToken());
                    if (isDone) {
                        task.doTask();
                    }
                    taskList.getList().add(task);
                } else if (token.equals("D")) {
                    boolean isDone = tokens.nextToken().equals("1");
                    Task task = new Deadline(tokens.nextToken(), tokens.nextToken());
                    if (isDone) {
                        task.doTask();
                    }
                    taskList.getList().add(task);
                } else if (token.equals("E")) {
                    boolean isDone = tokens.nextToken().equals("1");
                    Task task = new Event(tokens.nextToken(), tokens.nextToken());
                    if (isDone) {
                        task.doTask();
                    }
                    taskList.getList().add(task);
                }
                if (!read.hasNextLine()) {
                    break;
                }
                curr = read.nextLine();
            }
            read.close();
        }
    }

    /**
     * Store the current task list into the storage file.
     * @param taskList the current task list.
     */
    public void store(TaskList taskList) {
        PrintWriter writer = null;
        try {
            writer = new PrintWriter(this.path, "UTF-8"); // create PrintWriter object to write to the storage file.
        } catch (FileNotFoundException | UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        // Iterate through the current task list and update the storage file with the newest state of the task list
        for (Task t : taskList.getList()) {
            String curr = t.getType() + "/" + t.getState() + "/" + t.getTask();
            if (!t.getType().equals("T")) {
                curr += "/" + t.getTime();
            }
            assert writer != null;
            writer.println(curr);
        }
        assert writer != null;
        writer.close();
    }
}
