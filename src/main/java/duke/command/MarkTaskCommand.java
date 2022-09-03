package duke.command;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import duke.storage.Storage;
import duke.task.TaskList;

/**
 * Command to mark a task as done.
 */
public class MarkTaskCommand extends Command {
    public static final String COMMAND_WORD = "mark";

    private List<Integer> taskIndices;

    public MarkTaskCommand(List<Integer> taskIndices) {
        this.taskIndices = taskIndices;
    }

    @Override
    public String execute(TaskList tasks, Storage storage) {
        taskIndices = new ArrayList<>(new HashSet<>(taskIndices)); // Remove duplicates
        StringBuilder ret = new StringBuilder("Nice! I've marked these tasks as done:");
        for (int taskIndex : taskIndices) {
            tasks.markTaskAsDone(taskIndex);
            ret.append("\n\t").append(tasks.getTask(taskIndex));
        }
        storage.write(tasks);
        return ret.toString();
    }
}
