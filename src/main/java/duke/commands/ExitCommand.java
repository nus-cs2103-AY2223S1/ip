package duke.commands;

import duke.task.TaskList;
import duke.task.TaskStorage;
import duke.util.Ui;
import javafx.application.Platform;

public class ExitCommand extends Command {
    public ExitCommand(TaskStorage storage, TaskList taskList, Ui ui) {
        super(storage, taskList, ui);
    }

    public String sayGoodbye() {
        String result;
        result =  ui.sayGoodbye();
        Platform.exit();
        return result;
    }
}
