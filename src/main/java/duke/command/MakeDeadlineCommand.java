package duke.command;

import java.time.LocalDate;

import duke.main.Storage;
import duke.main.TaskList;
import duke.main.Ui;
import duke.task.Deadline;

/**
 * A Command which makes a Deadline object and adds it to a TaskList object when executed.
 */
public class MakeDeadlineCommand extends Command {
    private String description;
    private LocalDate time;

    /**
     * Constructs a MakeDeadlineCommand object which will make a
     * Deadline object with description and time and add to a TaskList object when executed.
     * @param description Description for the Deadline object to be created.
     * @param time Time for the Deadline object to be created.
     */
    public MakeDeadlineCommand(String description, LocalDate time) {
        this.description = description;
        this.time = time;
    }

    /**
     * Makes a new Deadline object with description and time from the constructor and adds it to taskList.
     * @param taskList TaskList object of the Duke object for the Command object to use.
     * @param ui Ui object of the Duke object for the Command object to use.
     * @param storage Storage object of the Duke object for the Command object to use.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        Deadline newTask = new Deadline(this.description, this.time);
        taskList.addTask(newTask);
        ui.showTaskAdded(newTask, taskList.getTaskListLength());
    }

    /**
     * Whether the MakeDeadlineCommand should end the Duke object.
     * It should not.
     * @return False.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
