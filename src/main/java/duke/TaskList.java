package duke;

import duke.task.Task;
import duke.task.Todo;
import duke.task.Event;
import duke.task.Deadline;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * TaskList class containing all the logic for interactions with the list of tasks.
 */
public class TaskList {
    private ArrayList<Task> tasks;

    /**
     * Creates empty TaskList.
     */
    public TaskList() {
        tasks = new ArrayList<>();
    }

    /**
     * Creates TaskList and populates the list with text from a list of strings.
     *
     * @param res List of strings to be populated into list.
     * @throws DukeException if data cannot be parsed properly.
     */
    public TaskList(List<String> res) throws DukeException {
        tasks = new ArrayList<>();
        for (String line: res) {

            char tag = line.charAt(1);
            boolean isDone = (line.charAt(4) == 'X' ? true : false);
            String msg[] = line.split(" ", 3)[2].split(" \\(");
            String name = msg[0];
            LocalDateTime date = null;

            if (msg.length > 1) {
                try {
                    date = LocalDateTime.parse(msg[1].split("\\)")[0],
                            DateTimeFormatter.ofPattern("MMM dd yyyy H:m"));
                } catch (Exception e) {
                    throw(new DukeException("Error parsing!"));
                }
            }

            Task task = createTask(tag, name, date);

            if (isDone) {
                task.mark();
            }

            tasks.add(task);
        }
    }

    /**
     * Creates new Task object given a list of descriptors.
     *
     * @param tag Character indicating type of task.
     * @param description Title of task.
     * @param time Time of task in LocalDateTime format.
     * @return New Task containing above details.
     */
    public Task createTask(char tag, String description, LocalDateTime time) {
        switch (tag) {
        case 'T':
            return new Todo(description);
        case 'E':
            return new Event(description, time);
        case 'D':
            return new Deadline(description, time);
        default:
            return null;
        }
    }

    public void remove(int index) {
        tasks.remove(index);
    }

    public Task get(int index) {
        return tasks.get(index);
    }

    public void add(Task task) {
        tasks.add(task);
    }

    public int size() {
        return tasks.size();
    }

    public ArrayList<Task> tasks() {
        return tasks;
    }

}
