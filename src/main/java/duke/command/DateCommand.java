package duke.command;

import duke.main.DukeException;
import duke.main.Storage;
import duke.main.TaskList;
import duke.main.Ui;
import duke.task.DatedTask;
import duke.task.Task;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.function.Predicate;

public class DateCommand extends Command {
    LocalDate date;

    public DateCommand(String date) throws DateTimeException {
        this.date = LocalDate.parse(date);
    }

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

    public boolean isExit() {
        return false;
    }
}
