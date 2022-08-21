package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

import java.util.Arrays;

public class AddDeadLineCommand extends Command {
    private int limit;
    private String taskDescription;
    private String by;

    public AddDeadLineCommand(String[] inputs) {
        super(CommandType.DEADLINE);
        limit = findElem(inputs, "/by");
        taskDescription = String.join(" ", Arrays.copyOfRange(inputs, 1, limit));
        by = String.join(" ", Arrays.copyOfRange(inputs, limit + 1, inputs.length));
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        if (limit == -1) {
            ui.printDeadLineErrorMessage();
            return;
        }
        tasks.addDeadLine(taskDescription, by);
        ui.printTaskAddedMessage(tasks.getLatestTask(), tasks.getTotalTasks());
    }
}
