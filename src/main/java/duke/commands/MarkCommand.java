package duke.commands;

import duke.task.TaskList;
import duke.task.TaskStorage;
import duke.util.Ui;

import java.util.ArrayList;

public class MarkCommand extends Command {

    public MarkCommand(TaskStorage storage, TaskList taskList, Ui ui) {
        super(storage, taskList, ui);
    }

    public String mark(ArrayList<String> parsedInput) {
    }
}
