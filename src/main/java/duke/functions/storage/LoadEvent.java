package duke.functions.storage;

import duke.tasks.Event;
import duke.tasks.Task;

/**
 * LoadEvent class to initiate the loading of an Event task into the TaskList.
 *
 * @author lauralee
 */
public class LoadEvent implements Load {

    private String taskDescription;
    private String time;

    /**
     * Constructor for LoadEvent class.
     *
     * @param taskDescription The string format of the task description as saved in storage.
     */
    public LoadEvent(String taskDescription) {
        int index = taskDescription.indexOf("(at: ");
        int endIndex = taskDescription.lastIndexOf(")");
        this.taskDescription = taskDescription.substring(7, index);
        this.time = taskDescription.substring(index + 5, endIndex);
    }
    @Override
    public Task loadTask() {
        return new Event(this.taskDescription, this.time);
    }
}
