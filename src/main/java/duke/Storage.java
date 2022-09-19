package duke;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import duke.exception.DukeException;
import duke.task.Deadline;
import duke.task.DoAfter;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

/**
 * Stores data.
 */
public class Storage {

    private String filePath;
    private ArrayList<String> storage;

    /**
     * Creates an instance of Storage to store data.
     * @param filePath path of file
     */
    public Storage(String filePath) {
        this.filePath = filePath;
        storage = new ArrayList<>();
    }

    /**
     * Loads saved data from the initialised filePath.
     */
    public ArrayList<Task> load() {
        ArrayList<Task> taskList = new ArrayList<>();

        try {
            BufferedReader reader = new BufferedReader(new FileReader(filePath));
            String line;

            while ((line = reader.readLine()) != null) {
                if (line.contains("todo")) {
                    storage.add(line);
                    String task = line.replace("todo", "");
                    if (task.contains(" | X")) {
                        task = task.replace(" | X", "");
                        Todo todo = new Todo(task);
                        todo.markAsDone();
                        taskList.add(todo);
                    } else {
                        Todo todo = new Todo(task);
                        taskList.add(todo);
                    }
                } else if (line.contains("deadline")) {
                    storage.add(line);
                    String task = line.replace("deadline", "");
                    if (task.contains(" | X")) {
                        task = task.replace(" | X", "");
                        Deadline deadline = new Deadline(task);
                        deadline.markAsDone();
                        taskList.add(deadline);
                    } else {
                        Deadline deadline = new Deadline(task);
                        taskList.add(deadline);
                    }
                } else if (line.contains("event")) {
                    storage.add(line);
                    String task = line.replace("event", "");
                    if (task.contains(" | X")) {
                        task = task.replace(" | X", "");
                        Event event = new Event(task);
                        event.markAsDone();
                        taskList.add(event);
                    } else {
                        Event event = new Event(task);
                        taskList.add(event);
                    }
                } else if (line.contains("doafter")) {
                    storage.add(line);
                    String task = line.replace("doafter", "");
                    if (task.contains(" | X")) {
                        task = task.replace(" | X", "");
                        DoAfter doAfter = new DoAfter(task);
                        doAfter.markAsDone();
                        taskList.add(doAfter);
                    } else {
                        DoAfter doAfter = new DoAfter(task);
                        taskList.add(doAfter);
                    }
                }
            }
            reader.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return taskList;
    }

    /**
     * Marks a task as done.
     * @param index the task number on the storage list
     */
    public void mark(int index) {
        if (!storage.get(index - 1).contains(" | X")) {
            String temp = storage.get(index - 1) + " | X";
            storage.remove(index - 1);
            storage.add(index - 1, temp);
        }
    }

    /**
     * Unmarks an undone task.
     * @param index the task number on the storage list
     */
    public void unmark(int index) {
        if (storage.get(index - 1).contains(" | X")) {
            String temp = storage.get(index - 1).replace(" | X", "");
            storage.remove(index - 1);
            storage.add(index - 1, temp);
        }
    }

    /**
     * Deletes a task from the storage list.
     * @param index the task number on the storage list
     */
    public void delete(int index) {
        storage.remove(index - 1);
    }

    public void delete(String taskType, String description) {
        storage.remove(taskType + description);
    }

    /**
     * Adds a task to be recorded and saved in storage.
     * @param taskType the string representing the type of task
     * @param description the string describing the task
     */
    public void add(String taskType, String description) {
        storage.add(taskType + description);
    }

    /**
     * Stores and saves data from storage list into the initialised filePath.
     */
    public void store() {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(filePath));
            for (int i = 0; i < storage.size(); i++) {
                writer.write(storage.get(i) + "\n");
            }
            writer.close();
        } catch (IOException e) {
            throw new DukeException("Save file could not be created.");
        }
    }
}
