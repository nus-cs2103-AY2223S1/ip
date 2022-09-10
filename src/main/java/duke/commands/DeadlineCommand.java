package duke.commands;

import java.time.LocalDateTime;

import duke.storage.Storage;
import duke.task.Deadline;
import duke.task.TaskList;
import duke.ui.TextUi;


/**
 * Represents a deadline command.
 */
public class DeadlineCommand extends Command {
    public static final String COMMAND_WORD = "deadline";

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
     * @param ui The TextUi class used to print message in Duke.
     * @param storage The storage used to save the tasks in the local file.
     */
    @Override
    public void execute(TaskList taskList, TextUi ui, Storage storage) {
        taskList.addTask(this.deadlineTask);
        storage.appendTaskToFile(this.deadlineTask);
        ui.showAddTaskMessage(this.deadlineTask, taskList);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
