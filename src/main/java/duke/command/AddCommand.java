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
     *          Thrown when the task has no name or no date and time is given when necessary.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        Task myTask = null;
        switch(COMMAND) {
        case DEADLINE:
            String[] dl = new String[2];
            try {
                dl = STR[1].split(" /by ");
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
                myTask = new ToDo(STR[1]);
            } catch (Exception e) {
                ui.emptyDescription();
            }
            break;

        case EVENT:
            String[] evnt = new String[0];
            try {
                evnt = STR[1].split(" /at ");
            } catch (Exception e) {
                ui.emptyDescription();
            }

            try {
                myTask = new Event(evnt[0], evnt[1]);
            } catch (Exception e) {
                ui.emptyDescription();
            }
            break;
        default:
            Ui.invalidTask();
            break;
        }
        tasks.add(myTask);
        storage.writeFile(tasks);
        return ui.add(tasks, myTask);
    }
}
