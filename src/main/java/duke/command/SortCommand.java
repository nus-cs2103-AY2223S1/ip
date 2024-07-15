package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * SortCommand is the Command used to specify the order of the tasks to be listed.
 */
public class SortCommand extends Command {
    private static final String SORT_ERROR = "OOPS!!! This sort command is invalid. (sort [chrono / rchrono])";
    private static final String SORT_DONE_MSG = "Tasks sorted ";
    private static final String CHRONO = "chronologically.";
    private static final String REVERSE_CHRONO = "reverse chronologically.";
    private String order;

    /**
     * Initializes a SortCommand object.
     *
     * @param order The order in which the list should be ordered.
     */
    public SortCommand(String order) {
        this.order = order;
    }

    /**
     * Sorts the tasks based on its date in chronological or reverse chronological order
     *
     * @param tasks The list of tasks.
     * @param ui The class that deals with interactions with the user.
     * @param storage The class that deals with loading and storing tasks.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        if (!order.equalsIgnoreCase("Chrono")
                && !order.equalsIgnoreCase("rChrono")) {
            throw new DukeException(SORT_ERROR);
        }

        boolean isChrono = order.equalsIgnoreCase("Chrono");

        if (isChrono) {
            tasks.sortChrono();
        } else {
            tasks.sortReverseChrono();
        }

        String finalMsg = SORT_DONE_MSG
                + (order.equalsIgnoreCase("Chrono")
                    ? CHRONO
                    : REVERSE_CHRONO);
        return finalMsg;
    }
}
