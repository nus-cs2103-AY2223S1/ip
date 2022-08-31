package duke.command;

import duke.storage.Storage;
import duke.task.TasksController;

/**
 * ExitCommand class will execute the exit command
 */
public class ExitCommand extends Command {

    /**
     * An abstract method that every child class needs to implement
     * @param controller Duke task controller
     * @param taskText if it's add task command, then pass the context of the task.
     * @param taskTime if it's add Event or Deadline, then pass the time
     * @param taskIndex if it's mark or unmark command, then pass the task number
     * @param keywords if it's find command, then pass the keywords
     * @param storage Duke IO processor
     */
    public String execute(TasksController controller, String taskText, String taskTime, int taskIndex,
                          Storage storage, String ...keywords) {
        storage.save(controller.getTasks());
        return "Bye. Hope to see you soon!";
    }
}
