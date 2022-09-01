package duke;
import duke.exception.DukeException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

import java.io.*;
import java.util.ArrayList;

public class Storage {

    private String filePath;
    private ArrayList<String> storage;

    public Storage(String filePath) {
        this.filePath = filePath;
        storage = new ArrayList<>();
    }

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
                }
            }
            reader.close();
        } catch (IOException e) {}

        return taskList;
    }

    public void mark(int index) {
        if (!storage.get(index - 1).contains(" | X")) {
                String temp = storage.get(index - 1) + " | X";
                storage.remove(index - 1);
                storage.add(index - 1, temp);
        }
    }

    public void unmark(int index) {
        if (storage.get(index - 1).contains(" | X")) {
            String temp = storage.get(index - 1).replace(" | X", "");
            storage.remove(index - 1);
            storage.add(index - 1, temp);
        }
    }

    public void delete(int index) {
        storage.remove(index - 1);
    }

    public void add(String taskType, String description) {
        storage.add(taskType + description);
    }

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
