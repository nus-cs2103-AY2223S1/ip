package duke.commands;

import java.io.IOException;
import duke.TaskList;
import duke.DukeException;
import duke.Ui;
import duke.Storage;
import duke.tasks.*;

public class DeleteCommand extends Command{
    private String input;

    public DeleteCommand(String input) {
        this.input = input;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException, IOException {
        if (this.input.matches("\\d+")) {
            int deleteIndex = Integer.parseInt(this.input) - 1;
            if (deleteIndex < 0 || deleteIndex >= taskList.length()) {
                throw new DukeException(String.format("There is no task with index %d", deleteIndex + 1));
            } else {
                Task deletedTask = taskList.index(deleteIndex);
                taskList.remove(deleteIndex);
                String deleteMessage = "Noted. I've removed this task:\n" + deletedTask.toString() +
                        "\nNow you have %d task(s) in the list";
                ui.print(String.format(deleteMessage, taskList.length()));
            }
        } else {
            throw new DukeException(this.input + " is not an integer.");
        }
        storage.saveTasks(taskList);
    }
}
