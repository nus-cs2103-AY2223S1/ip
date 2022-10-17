package duke.functions.storage;

import duke.tasks.Task;
import duke.tasks.ToDo;

/**
 * LoadDeadline class to initiate the loading of a Deadline task into the TaskList.
 *
 * @author lauralee
 */
public class LoadToDo implements Load {


    private String taskDescription;

    /**
     * Constructor for LoadToDo class.
     *
     * @param taskDescription The string format of the task description as saved in storage.
     */
    public LoadToDo(String taskDescription) {
        this.taskDescription = taskDescription.substring(7);
    }

    @Override
    public Task loadTask() {
        return new ToDo(this.taskDescription);
    }
}
