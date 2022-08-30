package duke.command;

import duke.task.TasksController;
import duke.Ui;
import duke.Storage;

/**
 * Abstract class Command. All commands will inherit from this class.
 */
public abstract class Command {

    /**
     * An abstract method that every child class needs to implement
     * @param controller Duke task controller
     * @param taskText if it's add task command, then pass the context of the task.
     * @param taskTime if it's add Event or Deadline, then pass the time
     * @param taskIndex if it's mark or unmark command, then pass the task number
     * @param keyword if it's find command, then pass the keyword
     * @param storage Duke IO processor
     */
    public abstract String execute(TasksController controller, String taskText, String taskTime, int taskIndex,
                                 String keyword, Storage storage);
}
