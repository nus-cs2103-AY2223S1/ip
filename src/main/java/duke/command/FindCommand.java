package duke.command;

import java.util.ArrayList;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.exception.DukeException;
import duke.task.Task;

public class FindCommand extends Command {

    private String query;

    public FindCommand(String query) {
        this.query = query;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        ArrayList<? super Task> result = tasks.find(query);
        if (result.size() == 0) {
            ui.printMessage("No results match your search :(");
        } else {
            ui.printMessage("Here are the matching tasks in your list:");
        }
        for (int i = 0; i < result.size(); i++) {
            ui.printMessage(result.get(i).toString());
        }
    }

    @Override
    public String getCommand() {
        return "find";
    }

    @Override
    public String toString() {
        return "find " + this.query;
    }
}
