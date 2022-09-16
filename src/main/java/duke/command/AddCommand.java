package duke.command;

import duke.exception.DukeEmptyDescriptionException;
import duke.exception.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.exception.DukeInvalidDateException;
import duke.exception.DukeInvalidTaskException;
import duke.task.Task;
import duke.task.Deadline;
import duke.task.ToDo;
import duke.task.Event;

/**
 * Executes the commands to add tasks to the list.
 *
 * @author Lim Ai Lin
 */
public class AddCommand extends Command {
    private final String[] STR;
    private final Commands COMMAND;
    private enum Commands {
        DEADLINE,
        TODO,
        EVENT
    }

    /**
     * Creates a new AddCommand object.
     *
     * @param str The array String of the task containing the description and date and time if necessary.
     * @param i The integer specifying the type of task.
     */
    public AddCommand(String[] str, int i) {
        this.STR = str;
        Commands[] commands = new Commands[]{Commands.DEADLINE, Commands.TODO, Commands.EVENT};
        this.COMMAND = commands[i];
    }

    /**
     * Executes the add command the user inputs.
     *
     * @param tasks The list in which the task is to be added.
     * @param ui The ui to deal with user interactions.
     * @param storage The storage to be updated with the newly added task.
     * @throws DukeException
     *          Thrown when the task has no description or no date and time is given when necessary.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        Task myTask;
        assert COMMAND == Commands.DEADLINE || COMMAND == Commands.EVENT || COMMAND == Commands.TODO;
        switch(COMMAND) {
        case DEADLINE:
            myTask = executeDl();
            break;
        case TODO:
            myTask = executeTd();
            break;
        case EVENT:
            myTask = executeEvnt();
            break;
        default:
            throw new DukeInvalidTaskException();
        }
        tasks.add(myTask);
        storage.writeFile(tasks);
        return ui.add(tasks, myTask);
    }

    /**
     * Executes the deadline command the user inputs.
     *
     * @throws DukeException
     *          Thrown when the task has no description or no date is given.
     */
    private Deadline executeDl() throws DukeException {
        String[] dl;
        try {
            dl = STR[1].split(" /by ");
        } catch (Exception e) {
            throw new DukeEmptyDescriptionException();
        }

        try {
            return new Deadline(dl[0], dl[1]);
        } catch (Exception e) {
            throw new DukeInvalidDateException();
        }
    }

    /**
     * Executes the to do command the user inputs.
     *
     * @throws DukeException
     *          Thrown when the task has no description.
     */
    private ToDo executeTd() throws DukeException {
        try {
            return new ToDo(STR[1]);
        } catch (Exception e) {
            throw new DukeEmptyDescriptionException();
        }
    }

    /**
     * Executes the event command the user inputs.
     *
     * @throws DukeException
     *          Thrown when the task has no description or no date and time is given.
     */
    private Event executeEvnt() throws DukeException {
        String[] evnt;
        try {
            evnt = STR[1].split(" /at ");
        } catch (Exception e) {
            throw new DukeEmptyDescriptionException();
        }

        try {
            return new Event(evnt[0], evnt[1]);
        } catch (Exception e) {
            throw new DukeEmptyDescriptionException();
        }
    }
}
