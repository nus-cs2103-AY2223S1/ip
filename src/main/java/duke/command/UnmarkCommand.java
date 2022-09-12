package duke.command;

import java.io.IOException;

import duke.internal.MessageBuilder;
import duke.internal.Parser;
import duke.internal.Storage;
import duke.task.Task;
import duke.task.TaskList;

/**
 * A command to unmark a task in the task list as done.
 * Usage: unmark [0]
 * [0]: index of the task to unmark as done
 * Note that the index starts at 1.
 */
public class UnmarkCommand extends Command {
    private final int index;

    public UnmarkCommand(int index) {
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
        task.markAsUndone();
        messageBuilder.addLine("I've unmarked your task as done.").addLine(task.toString());
        storage.saveTasks(tasks);
    }
}
