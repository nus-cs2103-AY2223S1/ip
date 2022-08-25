package duke;

import duke.models.Task;

import java.util.ArrayList;
import java.util.List;


/**
 * Contains the task list and has operations to add/delete tasks in the list
 */
public class TaskList {
    private List<Task> tasks;

    /**
     * Initializes an empty duke.TaskList
     */
    public TaskList() {
        tasks = new ArrayList<>();
    }

    /**
     * Initializes a taskList that contains existing tasks that have been saved
     * @param taskList List of existing tasks to be loaded
     */
    public TaskList(List<Task> taskList) {
        tasks = taskList;
    }

    public void addTodo(Task t) {
        tasks.add(t);
    }

}
