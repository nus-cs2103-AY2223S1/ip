package duke;

import java.util.ArrayList;
import static java.util.stream.Collectors.joining;

/**
 * Represents a list of tasks.
 */
public class TaskList extends ArrayList<Task> {
    public TaskList(ArrayList<Task> tasks) {
        super(tasks);
    }

    /**
     * Converts TaskList into save data string to be stored in a file.
     * @return the save data string
     */
    public String toSaveData() {
        return this.stream().map(Task::toSaveData).collect(joining("\n"));
    }

    /**
     * Finds all tasks in the list that contain the given string.
     * @param toFind the string to be searched within task descriptions
     * @return list of tasks that contain the given string
     */
    public ArrayList<Task> find(String toFind) {
        return this.stream().filter(task -> task.descriptionContains(toFind)).collect(ArrayList::new, ArrayList::add, ArrayList::addAll);
    }
}
