import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.function.Predicate;

public class DateCommand extends Command {
    LocalDate date;

    public DateCommand(String date) throws DateTimeException {
        this.date = LocalDate.parse(date);
    }

    public ArrayList<Task> filter(ArrayList<Task> arr, Predicate<? super Task> p) {
        ArrayList<Task> newArr = new ArrayList<>();
        for (Task t: arr) {
            if (p.test(t)) {
                newArr.add(t);
            }
        }
        return newArr;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        ui.sayList(filter(tasks.arr, x -> x instanceof DatedTask
                && ((DatedTask) x).date.equals(this.date)));
    }
    public boolean isExit() {
        return false;
    }
}
