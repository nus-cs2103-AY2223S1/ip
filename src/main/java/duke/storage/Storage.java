package duke.storage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

import duke.DukeException;
import duke.parser.DateParser;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.Todo;

/**
 * Reads the given file to get the stored tasks and store ongoing tasks after exiting duke.
 */
public class Storage {
    private ArrayList<Task> storedTasks;
    private String filePath = "./data/sheep.txt";

    /**
     * Constructor for <code>Storage</code>.
     * @param filePath
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Read the tasks from a given file and return a task list with those tasks.
     * @return An ArrayList of Task
     * @throws DukeException
     */
    public ArrayList<Task> load() throws DukeException {
        File file = new File(filePath);
        try {
            Scanner sc = new Scanner(file);
            ArrayList<Task> tasks = new ArrayList<>();
            while (sc.hasNext()) {
                String storedTask = sc.nextLine();
                String[] splitTask = storedTask.split("\\|", 5);
                Task task;
                String symbol = splitTask[0].trim();
                if (symbol.equals("T")) {
                    String content = splitTask[2].trim();
                    if (splitTask[3].trim().length() > 0) {
                        String tag = splitTask[3].trim();
                        task = new Todo(content, tag);
                    } else {
                        task = new Todo(content);
                    }
                } else if (symbol.equals("D")) {
                    String content = splitTask[2].trim();
                    LocalDate date = DateParser.convertToLocalDate(splitTask[4].trim());
                    if (splitTask[3].trim().length() > 0) {
                        String tag = splitTask[3].trim();
                        task = new Deadline(content, tag, date);
                    } else {
                        task = new Deadline(content, date);
                    }
                } else {
                    String content = splitTask[2].trim();
                    String time = splitTask[4].trim();
                    if (splitTask[3].trim().length() > 0) {
                        String tag = splitTask[3].trim();
                        task = new Event(content, tag, time);
                    } else {
                        task = new Event(content, time);
                    }
                }
                if (splitTask[1].equals("1")) {
                    task.markDone();
                }
                tasks.add(task);
            }
            return tasks;
        } catch (FileNotFoundException e) {
            throw new DukeException("Cannot find the given file.");
        }
    }

    /**
     * Store ongoing tasks to a file for later use.
     * @param tasks
     */
    public void storeTasks(TaskList tasks) {
        try {
            File file = new File(filePath);
            FileWriter fw = new FileWriter(file);
            String storedTasks = "";
            for (int i = 0; i < tasks.getNumberOfTasks(); i++) {
                if (i < tasks.getNumberOfTasks() - 1) {
                    storedTasks += tasks.convertTaskToMemoryString(i) + "\n";
                } else {
                    storedTasks += tasks.convertTaskToMemoryString(i);
                }
            }
            fw.write(storedTasks);
            fw.close();
        } catch (IOException e) {
            String fileDirectory = filePath.replace(filePath.substring(filePath.lastIndexOf("/")), "");
            File file = new File(fileDirectory);
            if (file.mkdir()) {
                storeTasks(tasks);
            } else {
                System.out.println("OOPS!!! There is an error occurred when trying to store the tasks");
            }
        }
    }
}
