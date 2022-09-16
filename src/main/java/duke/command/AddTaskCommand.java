package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.task.Task;
import duke.task.TaskList;

public class AddTaskCommand extends Command {
    private final Task task;

    /**
     * Initialises a {@code AddTaskCommand} with a {@code Task}.
     *
     * @param task The {@code task} to be added.
     */
    public AddTaskCommand(Task task) {
        assert task != null;
        this.task = task;
    }

    @Override
    public String execute(TaskList tasks) {
        tasks.add(task);
        try {
            Storage.write(tasks);
        } catch (DukeException e) {
            e.printStackTrace();
        }
        return String.format("Got it. I've added this task:\n %s\nNow you have %s in the list.",
                tasks.get(tasks.size() - 1),
                tasks.lengthString());
    }
}
