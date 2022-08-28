package duke.command;

import duke.*;
import duke.task.Task;

import java.io.IOException;

/**
 * A command that deletes a task from the task list.
 */
public class DeleteCommand extends Command {
    private Task task;
    private int taskIndex;
    private TaskList taskList;

    private DeleteCommand(String command, Task task, int taskIndex, TaskList taskList) {
        super(command);
        this.task = task;
        this.taskIndex = taskIndex;
        this.taskList = taskList;
    }

    /**
     * Factory method taking in input string from user and a TaskList.
     * Throws IllegalArgumentException if the index for the task to be deleted is not given,
     * or if the index is out of bounds of the task list.
     *
     * @param command "delete k", where k is the 1-based index of the task to be deleted in the task list.
     * @param taskList TaskList containing the current tasks.
     * @return DeleteCommand instance that deletes a task from the task list when executed.
     * @throws IllegalArgumentException if input string from user is invalid.
     */
    public static DeleteCommand of(String command, TaskList taskList) throws IllegalArgumentException {
        int taskIndex;
        try {
            taskIndex = Parser.getTaskIndex(command, taskList);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("🙁 OOPS!!! Provide a number to delete a task.\n");
        }

        Task task;
        try {
            task = taskList.get(taskIndex);
        } catch (IndexOutOfBoundsException e) {
            throw new IllegalArgumentException(
                    String.format(
                            "🙁 OOPS!!! Provide a valid number (from 1 to %d) to delete a task.\n", taskList.size())
            );
        }
        return new DeleteCommand(command, task, taskIndex, taskList);
    }

    @Override
    public void execute(TaskList tasklist, Storage storage) throws IOException {
        this.taskList.remove(taskIndex, storage);
    };

    @Override
    public void execute(TaskList tasklist, Storage storage, Ui ui) {
        try {
            this.execute(tasklist, storage);
        } catch (IOException e) {
            ui.println(e.getMessage());
            return;
        }
        ui.printWithDivider(String.format("Noted. I've removed this task:\n  %s", this.task.toString()));
    }
}
