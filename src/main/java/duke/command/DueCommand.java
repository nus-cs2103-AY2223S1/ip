package duke.command;

import java.time.LocalDate;
import java.util.ArrayList;

import duke.FileStorage;
import duke.Ui;
import duke.task.Task;
import duke.task.TaskList;

/**
 * Command used to search tasks due/occurring at a particular date.
 */
public class DueCommand extends Command {
    public static final String COMMAND_WORD = "due";
    private LocalDate date;

    public DueCommand(LocalDate date) {
        this.date = date;
    }

    /**
     * Finds the due tasks from the taskList
     * and returns the corresponding message to the GUI.
     *
     * @param list The taskList of Duke.
     * @param storage The fileStorage of Duke.
     * @param ui The Ui of Duke.
     * @return The string meant for the GUI.
     */
    @Override
    public String execute(TaskList list, FileStorage storage, Ui ui) {
        ArrayList<Task> dueTasks = list.getDueTasks(date);
        return ui.getDueTasksMessage(dueTasks);
    }
}
