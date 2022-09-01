package duke.command;

import duke.storage.Storage;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.TaskList;
import duke.task.ToDo;
import duke.ui.Ui;

/**
 * Represents an Add Command, to add a task to tasks.
 *
 * @author Elgin
 */
public class AddCommand extends Command {
    /** The name of the command (e.g. todo, deadline, event). */
    private final String command;

    /** The arguments of the command (e.g. 'sleep /at 2020-12-12'). */
    private final String arguments;

    /**
     * Constructor of AddCommand.
     *
     * @param command The name of the command.
     * @param arguments The arguments of the command (e.g. 'sleep /at 2020-12-12').
     */
    public AddCommand(String command, String arguments) {
        this.command = command;
        this.arguments = arguments;
    }

    /**
     * Executes AddCommand and adds a task to tasks.
     *
     * @param tasks All tasks present in Duke.
     * @param ui The UI controller that handles interaction between user and Duke.
     * @param storage Storage that stores all tasks on Disk.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        if (this.command.equals("todo")) {
            ToDo newToDo = tasks.addToDo(this.arguments);

            return ui.taskAddedMsg(tasks.getTaskLen(), newToDo);
        } else if (this.command.equals("deadline")) {
            Deadline newDeadline = tasks.addDeadline(this.arguments);

            return ui.taskAddedMsg(tasks.getTaskLen(), newDeadline);
        } else {
            Event newEvent = tasks.addEvent(this.arguments);

            return ui.taskAddedMsg(tasks.getTaskLen(), newEvent);
        }
    }
}
