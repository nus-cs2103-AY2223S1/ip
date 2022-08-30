package duke.commands;

import duke.exception.DukeException;
import duke.main.Storage;
import duke.main.Ui;
import duke.tasks.Task;
import duke.tasks.TaskList;

/**
 * TaskCommand class is the parent class for TodoTask, DeadlineTask and EventTask
 */
public class TaskCommand extends Command {

    protected String description;

    /**
     * Constructor for Task Command
     *
     * @param description Description of task details
     * @throws DukeException If user formats wrongly
     */
    public TaskCommand(String description) throws DukeException {
        String[] addlst = description.split(" ", 2);
        if (addlst.length < 2) {
            throw new DukeException("Task description missing!");
        }
        this.description = addlst[1];
    }

    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        return null;
    }

    /**
     * Prints the correct message depending on the tasks type
     *
     * @param tasklist Array of tasks
     * @param task     The task that has been added
     */
    public String getMessage(TaskList tasklist, Task task) {
        String str = "Got it. I've added this task: \n";
        str += task.toString();
        str += "Now you have " + tasklist.size() + " task(s) in the list";
        return str;
    }
}
