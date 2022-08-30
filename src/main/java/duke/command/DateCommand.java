package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Task;

import java.time.LocalDate;
import java.util.ArrayList;

/**
 * DateCommand is the command for when the user wants to find tasks corresponding to a specific date.
 */
public class DateCommand extends Command {

    private LocalDate date;
    private ArrayList<Task> matchingTasks;

    /**
     * Constructor for DateCommand.
     *
     * @param date Date to filter the tasks by.
     */
    public DateCommand(LocalDate date) {
        super();
        this.date = date;
        this.matchingTasks = new ArrayList<>();
    }

    /**
     * Executes the specific command corresponding to the type of input the user gives.
     *
     * @param list List of tasks.
     * @param ui Ui to print messages.
     * @param storage To save the list after making changes.
     */
    @Override
    public void execCommand(TaskList list, Ui ui, Storage storage) {
        for (int i = 0; i < list.getSize(); i++) {
            Task task = list.getTask(i);
            if (task.getTime().equals(date)) {
                matchingTasks.add(task);
            }
        }
        ui.showDate(matchingTasks);
    }
}
