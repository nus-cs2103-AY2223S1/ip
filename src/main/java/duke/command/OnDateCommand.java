package duke.command;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import duke.Ui;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;

/**
 * Represents a command to list out all <code>Task</code> in the
 * list of tasks given a date.
 *
 * @author Derrick Khoo
 */
public class OnDateCommand extends Command {
    private LocalDate localDate;

    /**
     * Constructs a command to list out all <code>Task</code> in the
     * list of tasks given a date.
     *
     * @param localDate the date entered by the user, parsed into a <code>LocalDate</code>
     */
    public OnDateCommand(LocalDate localDate) {
        this.localDate = localDate;
    }

    /**
     * Handles the command to list out all <code>Task</code> in the list of tasks given a date.
     *
     * @param storage  the <code>Storage</code> dealing with loading and saving tasks in a file
     * @param ui       the user interfaces that deals with user inputs
     * @param taskList the list of tasks
     */
    public void handle(Storage storage, Ui ui, TaskList taskList) {
        ArrayList<Task> list = taskList.getTaskList();
        List<Task> filteredList = list.stream().filter(task -> task.isHappeningOnDate(localDate))
                .collect(Collectors.toList());
        int i = 0;
        ui.line();
        System.out.println("Hey, these are what you need to do on this date: "
                + localDate.format(DateTimeFormatter.ofPattern("MMMM d yyyy")));
        for (Task t : filteredList) {
            System.out.println(i + 1 + "." + t);
            i++;
        }
        ui.line();
    }
}
