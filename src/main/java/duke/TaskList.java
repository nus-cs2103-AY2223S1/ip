package duke;

import duke.task.Task;

import java.util.ArrayList;

/**
 * Represents a list of tasks in the duke.Duke application.
 */
public class TaskList extends ArrayList<Task> {

    /**
     * Constructor for duke.TaskList which initialises an ArrayList of tasks.
     */
    public TaskList() {
        super();
    }

    public String find(String keyword) {
        String message = "Here are the matching tasks in your list!\n%s";

        ArrayList<String> foundTasks = new ArrayList<>();

        for (int i = 0; i < this.size(); i++) {
            Task task = this.get(i);
            if (task.toString().contains(keyword)) {
                String taskString = String.format("%d: %s", i + 1, task);
                foundTasks.add(taskString);
            }
        }
        String tasksString = String.join("\n", foundTasks);
        return String.format(message, tasksString);
    }
}
