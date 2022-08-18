package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.Ui;
import duke.task.TaskList;

import java.io.IOException;

public class Command {
    public void execute(TaskList taskList, Ui ui, Storage storage) throws IOException, DukeException {
        System.out.println("Parent class Command executed");
    }

    public static boolean isExit(Command command) {
        return command instanceof ExitCommand;
    }
}
