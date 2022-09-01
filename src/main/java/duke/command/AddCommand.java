package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

/**
 * Executes the commands to add tasks to the list.
 * @author Lim Ai Lin
 */
public class AddCommand extends Command {
    String[] str;
    Commands command;
    private enum Commands {
        DEADLINE,
        TODO,
        EVENT
    }

    /**
     * Creates a new AddCommand object.
     * @param str The array String of the task containing the description and date and time if necessary.
     * @param i The integer specifying the type of task.
     */
    public AddCommand(String[] str, int i) {
        this.str = str;
        Commands[] commands = new Commands[]{Commands.DEADLINE, Commands.TODO, Commands.EVENT};
        this.command = commands[i];
    }

    /**
     * Executes the add command the user inputs.
     * @param tasks The list in which the task is to be added.
     * @param ui The ui to deal with user interactions.
     * @param storage The storage to be updated with the newly added task.
     * @throws DukeException
     *          Thrown when the task has no name or no date and time is given when necessary.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        Task myTask = null;
        switch(command) {
        case DEADLINE:
            String[] dl = new String[2];
            try {
                dl = str[1].split(" /by ");
            } catch (Exception e) {
                ui.emptyDescription();
            }

            try {
                myTask = new Deadline(dl[0], dl[1]);
            } catch (Exception e) {
                ui.missingDate();
            }
            break;

        case TODO:
            try {
                myTask = new ToDo(str[1]);
            } catch (Exception e) {
                throw new DukeException("Oops! The description of a todo cannot be empty!");
            }
            break;

        case EVENT:
            String[] evnt;
            try {
                evnt = str[1].split(" /at ");
            } catch (Exception e) {
                throw new DukeException("Oops! The description of an event cannot be empty!");
            }

            try {
                myTask = new Event(evnt[0], evnt[1]);
            } catch (Exception e) {
                throw new DukeException("Oops! When is the event?");
            }
            break;
        }
        tasks.add(myTask);
        storage.writeFile(tasks);
        ui.add(tasks, myTask);
    }
}
