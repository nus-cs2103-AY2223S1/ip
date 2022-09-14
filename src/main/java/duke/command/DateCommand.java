package duke.command;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.function.Predicate;

import duke.main.DukeException;
import duke.main.Storage;
import duke.main.TaskList;
import duke.main.Ui;
import duke.task.DatedTask;
import duke.task.Task;

/**
 * A class for commands with date.
 */
public class DateCommand extends Command {
    private LocalDate date;

    /**
     * Constructs the Date Command.
     *
     * @param date the date of the task.
     * @throws DateTimeException throws exception when date is not in the right format.
     * @throws DukeException if command cannot be executed.
     */
    public DateCommand(String date) throws DateTimeException {
        this.date = LocalDate.parse(date);
    }

    /**
     * Returns array of tasks that meets the criteria set by predicate p.
     *
     * @param arr an array of tasks.
     * @param p a condition for the tasks in the array to meet.
     * @return a list of tasks.
     */
    private ArrayList<Task> filter(ArrayList<Task> arr, Predicate<? super Task> p) {
        ArrayList<Task> newArr = new ArrayList<>();
        for (Task t: arr) {
            if (p.test(t)) {
                newArr.add(t);
            }
        }
        return newArr;
    }

    /**
     * Prints a list of tasks happening on a particular date.
     *
     * @param tasks the list of tasks
     * @param ui the user interface
     * @param storage the storage
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        ui.sayList(filter(tasks.getArr(), x -> x instanceof DatedTask
                && ((DatedTask) x).getDate().equals(this.date)));
    }


    /**
     * Makes sure program continues and not exit.
     * @return boolean indicating not exit.
     */
    public boolean isExit() {
        return false;
    }
}
