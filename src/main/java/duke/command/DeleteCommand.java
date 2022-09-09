package duke.command;

import java.io.IOException;

import duke.internal.MessageBuilder;
import duke.internal.Parser;
import duke.internal.Storage;
import duke.task.Task;
import duke.task.TaskList;

/**
 * A command to delete a task from the task list.
 * Usage: delete [0]
 * [0]: index of the task to delete
 * Note that the index starts at 1.
 */
public class DeleteCommand extends Command {
    private final int index;

    public DeleteCommand(int index) {
        this.index = index;
    }

    @Override
    public boolean isTerminal() {
        return false;
    }

    @Override
    public void execute(TaskList tasks, Storage storage, MessageBuilder messageBuilder,
                        Parser parser) throws IOException {
        Task task = tasks.getTask(index);
        tasks.deleteTask(index);
        messageBuilder.addLine("I've deleted this task.")
                .addLine(task.toString())
                .addTaskListSize(tasks);
        storage.saveTasks(tasks);
    }
}
