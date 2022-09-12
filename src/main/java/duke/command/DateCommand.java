package duke.command;

import java.time.LocalDate;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Date command class to find all the tasks on a give date.
 */
public class DateCommand extends Command {
    private LocalDate localDate;

    /**
     * Constructor of date command.
     *
     * @param date The date to find.
     */
    public DateCommand(LocalDate date) {
        this.localDate = date;
    }

    /**
     * Prints all tasks on a given date.
     *
     * @param tasks The tasks to be executed.
     */
    @Override
    public String execute(TaskList tasks) {
        int i = 0;
        String response = "";
        for (Task task : tasks.getTasks()) {
            if (tasks.getTaskType(task).equals("Deadlines")) {
                Deadline d = (Deadline) task;
                if (d.getDate().equals(this.localDate)) {
                    i = i + 1;
                    response = response + i + "." + d + "\n";
                }
            } else if (tasks.getTaskType(task).equals("Events")) {
                Event e = (Event) task;
                if (e.getDate().equals(this.localDate)) {
                    i = i + 1;
                    response = response + i + "." + e + "\n";
                }
            }
        }
        return Ui.showTasksOnDateMessage(this.localDate, response);
    }

    /**
     * @inheritDoc
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
