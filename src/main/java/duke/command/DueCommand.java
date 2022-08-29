package duke.command;

import java.time.LocalDate;
import java.util.ArrayList;

import duke.FileStorage;
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
     */
    @Override
    public String execute(TaskList list, FileStorage storage) {
        ArrayList<Task> dueTasks = list.getDueTasks(date);
        StringBuilder strBuilder = new StringBuilder("Here are the tasks due at this date:");
        for (int i = 0; i < dueTasks.size(); i++) {
            strBuilder.append("\n").append(i + 1).append(".").append(dueTasks.get(i));
        }
        return strBuilder.toString();
    }
}
