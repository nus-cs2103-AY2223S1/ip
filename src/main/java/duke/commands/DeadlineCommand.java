package duke.commands;

import java.time.LocalDateTime;

import duke.gui.Ui;
import duke.storage.Storage;
import duke.task.Deadline;
import duke.task.TaskList;


/**
 * Represents a deadline command.
 */
public class DeadlineCommand extends Command {
    public static final String COMMAND_WORD = "deadline";
    public static final String SHORTER_COMMAND_WORD = "d";

    private Deadline deadlineTask;

    /**
     * Creates a new instance of deadline command and deadline task.
     *
     * @param description The description of the deadline task.
     * @param deadlineTiming The datetime of when the deadline is due.
     */
    public DeadlineCommand(String description, LocalDateTime deadlineTiming) {
        this.deadlineTask = new Deadline(description, deadlineTiming);
    }

    /**
     * Adds the deadline task to the task list and save it to the file.
     *
     * @param taskList The list of tasks in Duke.
     * @param ui The ui class to get the command response.
     * @param storage The storage used to save the tasks in the local file.
     * @return The success message after adding a deadline task.
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        taskList.addTask(this.deadlineTask);
        storage.appendTaskToFile(this.deadlineTask);
        return ui.getAddTaskCommandMessage(this.deadlineTask, taskList);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
