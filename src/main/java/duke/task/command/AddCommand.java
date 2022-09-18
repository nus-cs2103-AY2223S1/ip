package duke.task.command;

import duke.exception.DukeException;
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
    private static String command;

    /** The arguments of the command (e.g. 'sleep /at 2020-12-12'). */
    private static String arguments;

    /**
     * Constructor of AddCommand.
     *
     * @param command The name of the command.
     * @param arguments The arguments of the command (e.g. 'sleep /at 2020-12-12').
     */
    public AddCommand(String command, String arguments) {
        AddCommand.command = command;
        AddCommand.arguments = arguments;
    }

    /**
     * Executes AddCommand and adds a task to tasks.
     *
     * @param tasks All tasks present in Duke.
     * @param ui The UI controller that handles interaction between user and Duke.
     * @param storage Storage that stores all tasks on Disk.
     * @return Duke's message to the user.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        switch (AddCommand.command) {
        case "todo":
            ToDo newToDo = tasks.addToDo(AddCommand.arguments);

            assert newToDo != null : "Should not reach this line if ToDo task failed to create";

            return ui.getTaskAddedMsg(tasks.getTaskLen(), newToDo);
        case "deadline":
            Deadline newDeadline = tasks.addDeadline(AddCommand.arguments);

            assert newDeadline != null : "Should not reach this line if Deadline task failed to create";

            return ui.getTaskAddedMsg(tasks.getTaskLen(), newDeadline);
        case "event":
            Event newEvent = tasks.addEvent(AddCommand.arguments);

            assert newEvent != null : "Should not reach this line if Event task failed to create";

            return ui.getTaskAddedMsg(tasks.getTaskLen(), newEvent);
        default:
            throw new DukeException("Invalid command to create a new task");
        }
    }
}
