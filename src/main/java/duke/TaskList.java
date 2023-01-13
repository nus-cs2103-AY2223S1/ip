package duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

/**
 * TaskList class containing all the logic for interactions with the list of tasks.
 */
public class TaskList {
    private final ArrayList<Task> tasks;

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
        for (String line : res) {

            char tag = line.charAt(1);
            boolean isDone = (line.charAt(4) == 'X');

            String[] tokens = line.split(" ", 3);

            if (tokens.length < 3) {
                throw(new DukeException("Error parsing!"));
            }

            String[] msg = tokens[2].split(" \\(");
            String name = msg[0];
            LocalDateTime date = null;

            if (msg.length > 1) {
                try {
                    date = LocalDateTime.parse(msg[1].split("\\)")[0],
                            DateTimeFormatter.ofPattern("MMM dd yyyy H:m"));
                } catch (Exception e) {
                    throw (new DukeException("Error parsing!"));
                }
            }

            if (msg.length == 1) {
                assert(date == null);
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
     * @param tag         Character indicating type of task.
     * @param description Title of task.
     * @param time        Time of task in LocalDateTime format.
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

    /**
     * Sorts the Task list according to specified parameters.
     * The list can be either sorted alphabetically, or chronologically.
     * @param sortType Type of sort to use.
     * @param isDescending
     */
    public void sort(String sortType, boolean isDescending) {
        switch (sortType) {
        case "alphabetically":
            Collections.sort(tasks, Comparator.comparing(Task::getName));
            break;
        case "chronologically":
            Collections.sort(tasks, Comparator.comparing(Task::getTime));
            break;
        default:
            break;
        }

        if (isDescending) {
            Collections.reverse(tasks);
        }
    }
}
