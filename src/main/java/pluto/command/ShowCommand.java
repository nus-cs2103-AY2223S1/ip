package pluto.command;

import java.time.LocalDate;

import pluto.PlutoException;
import pluto.Storage;
import pluto.TaskList;
import pluto.Ui;
import pluto.task.Task;

/**
 * Command to show all tasks of a particular date.
 */
public class ShowCommand extends Command {
    /** Date whose tasks need to be retrieved */
    private LocalDate date;

    /**
     * Initializes global variables.
     * @param date Date to find tasks.
     */
    public ShowCommand(LocalDate date) {
        this.date = date;
    }

    /**
     * {@inheritDoc}
     *
     * Displays all tasks of a date.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws PlutoException {
        TaskList filteredTasks = new TaskList();
        for (int i = 0; i < tasks.nTasks(); i++) {
            Task t = tasks.getTask(i);
            LocalDate date = ((Task) t).getDateMaybe();
            if (this.date.equals(date)) {
                filteredTasks.addTask(t);
            }
        }
        if (filteredTasks.nTasks() == 0) {
            return ui.print("No tasks found on this date.");
        } else {
            return ui.print("Here are the tasks on this date:\n" + ui.print(filteredTasks.toString()));
        }
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof ShowCommand) {
            ShowCommand other = (ShowCommand) o;
            return this.date.equals(other.date);
        }
        return false;
    }

}
