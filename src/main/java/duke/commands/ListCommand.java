package duke.commands;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import duke.data.TaskList;
import duke.storage.Storage;
import duke.tasks.Task;
import duke.ui.Ui;

/**
 * This class represents a command to list out the tasks in the task list
 * If a date is provided, tasks that fall on that date will be listed
 */
public class ListCommand extends Command {

    private final String date;

    /**
     * Constructs a new list command
     * @param info Essential information for the output list
     */
    public ListCommand(String... info) {
        super();
        if (info.length == 0) {
            date = null;
        } else {
            date = info[0];
        }
    }

    /**
     * Executes the Command
     * @param ui Prints the messages based on the type of Command
     * @param storage Saves the modified list of tasks
     * @param taskList List of tasks
     */
    @Override
    public String execute(Ui ui, Storage storage, TaskList taskList) {
        if (date == null) {
            ArrayList<Task> list = taskList.list();
            return ui.printList(list);
        } else {
            List<Task> list = taskList.list(date);
            LocalDate d = LocalDate.parse(date, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
            return ui.printList(list, d);
        }
    }
}
