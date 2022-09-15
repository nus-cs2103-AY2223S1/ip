package duke.command;

import duke.DukeException;
import duke.Parser;
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
        this.task = task;
    }

    @Override
    public void execute(TaskList tasks) {
        tasks.add(task);
        try {
            Storage.write(tasks);
        } catch (DukeException e) {
            Parser.printMsg(e.getMessage());
        }
        Parser.printMsg(String.format("Got it. I've added this task:\n %s\nNow you have %s in the list.",
                tasks.get(tasks.size() - 1),
                tasks.lengthString()));
    }
}
