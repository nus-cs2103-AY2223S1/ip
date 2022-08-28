package duke.command;

import duke.*;
import duke.task.Task;

import java.io.IOException;

/**
 * A command that marks a task in the task list.
 */
public class UpdateMarkCommand extends UpdateCommand {
    private UpdateMarkCommand(String command, Task task, int taskIndex) {
        super(command, task, taskIndex);
    }

    /**
     * Factory method taking in input string from user and a TaskList.
     * Throws IllegalArgumentException if the index for the task to be marked is not given,
     * or if the index is out of bounds of the task list.
     *
     * @param command "mark k", where k is the 1-based index of the task to be marked in the task list.
     * @param taskList TaskList containing the current tasks.
     * @return UpdateMarkCommand instance that marks a task from the task list when executed.
     * @throws IllegalArgumentException if input string from user is invalid.
     */
    public static UpdateMarkCommand of(String command, TaskList taskList) throws IllegalArgumentException {
        int taskIndex;
        try {
            taskIndex = Parser.getTaskIndex(command, taskList);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("🙁 OOPS!!! Provide a number to mark a task.\n");
        }

        Task task;
        try {
            task = taskList.get(taskIndex);
        } catch (IndexOutOfBoundsException e) {
            throw new IllegalArgumentException(
                    String.format(
                            "🙁 OOPS!!! Provide a valid number (from 1 to %d) to mark a task.\n", taskList.size())
            );
        }
        return new UpdateMarkCommand(command, task, taskIndex);
    }

    @Override
    public void execute(TaskList taskList, Storage storage) throws IOException {
        taskList.mark(super.taskIndex, storage);
    }

    @Override
    public void execute(TaskList taskList, Storage storage, Ui ui) {
        try {
            this.execute(taskList, storage);
        } catch (IOException e) {
            ui.println(e.getMessage());
        }
        ui.printWithDivider(String.format("Nice! I've marked this task as done:\n  %s", super.task.toString()));
    }
}
