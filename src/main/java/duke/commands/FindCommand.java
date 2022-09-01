package duke.commands;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Event;

public class FindCommand extends Command {
    private String input;

    public FindCommand(String input) {
        this.input = input;
    }

    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        if (input.isBlank()) {
            throw new DukeException("Wait a minute :/ "
                    + "what are you finding??");
        }
        TaskList tasksWithWord = taskList.getTasksWithWord(input);
        ui.showFindings(tasksWithWord, input);
    }

    public boolean isExit() {
        return false;
    }
}
