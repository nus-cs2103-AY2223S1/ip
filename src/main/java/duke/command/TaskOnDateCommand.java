package duke.command;

import duke.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

/**
 * Represents command to filter tasks for a specified date.
 */
public class TaskOnDateCommand extends Command {
    private LocalDate date;

    /**
     * Create TaskOnDateCommand.
     * @param date Specified date to filter for.
     */
    public TaskOnDateCommand(LocalDate date) {
        this.date = date;
    }

    /**
     * Filters for tasks on a specified date and prints it to the user.
     * @param taskList List of tasks.
     * @param ui Ui interface for input and output.
     * @param storage Storage for Duke's file operations.
     * @throws DukeException
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        String formattedDate = date.format(DateTimeFormatter.ofPattern("E, d MMM yyyy"));
        ArrayList<Task> filteredTaskList = taskList.getTasksOn(date);
        ui.printWithIndent(String.format("Here are the tasks on %s:", formattedDate));
        for (int i = 0; i < filteredTaskList.size(); i++) {
            Task task =  filteredTaskList.get(i);
            ui.printWithIndent(i + 1 + ". " + task);
        }
    }
}
