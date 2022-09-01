package duke.command;

import duke.*;
import duke.task.Task;

import java.io.IOException;

/**
 * A command that unmarks a task in the task list.
 */
public class UpdateUnmarkCommand extends UpdateCommand {
    private UpdateUnmarkCommand(String command, Task task, int taskIndex) {
        super(command, task, taskIndex);
    }

    /**
     * Factory method taking in input string from user and a TaskList.
     * Throws IllegalArgumentException if the index for the task to be unmarked is not given,
     * or if the index is out of bounds of the task list.
     *
     * @param command "unmark k", where k is the 1-based index of the task to be unmarked in the task list.
     * @param taskList TaskList containing the current tasks.
     * @return UpdateUnmarkCommand instance that unmarks a task from the task list when executed.
     * @throws IllegalArgumentException if input string from user is invalid.
     */
    public static UpdateUnmarkCommand of(String command, TaskList taskList) throws IllegalArgumentException {
        int taskIndex;
        try {
            taskIndex = Parser.getTaskIndex(command);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("🙁 OOPS!!! Provide a number to unmark a task.\n");
        }

        Task task;
        try {
            task = taskList.getTask(taskIndex);
        } catch (IndexOutOfBoundsException e) {
            throw new IllegalArgumentException(
                    String.format(
                            "🙁 OOPS!!! Provide a valid number (from 1 to %d) to unmark a task.\n", taskList.size())
            );
        }
        return new UpdateUnmarkCommand(command, task, taskIndex);
    }

    @Override
    public void execute(TaskList taskList, Storage storage) throws IOException {
        taskList.unmark(super.taskIndex, storage);
    }

    @Override
    public void execute(TaskList taskList, Storage storage, Ui ui) {
        try {
            this.execute(taskList, storage);
        } catch (IOException e) {
            ui.println(e.getMessage());
        }
        ui.printWithDivider(String.format("OK, I've marked this task as not done yet:\n  %s", super.task.toString()));
    }
}
