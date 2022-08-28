package duke.command;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import duke.Storage;
import duke.Task;
import duke.TaskList;
import duke.Ui;

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
     * @param tasks List of tasks.
     * @param ui Ui interface for input and output.
     * @param storage Storage for Duke's file operations.
     * @return Duke's response
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        String formattedDate = date.format(DateTimeFormatter.ofPattern("E, d MMM yyyy"));
        ArrayList<Task> filteredTasks = tasks.getTasksOn(date);
        ArrayList<String> responseLines = new ArrayList<>();
        responseLines.add(String.format("Here are the tasks on %s:", formattedDate));
        for (int i = 0; i < filteredTasks.size(); i++) {
            Task task = filteredTasks.get(i);
            responseLines.add(i + 1 + ". " + task);
        }
        return String.join("\n", responseLines);
    }
}
