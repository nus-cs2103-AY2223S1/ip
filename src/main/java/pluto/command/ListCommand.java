package pluto.command;

import pluto.Storage;
import pluto.TaskList;
import pluto.Ui;

public class ListCommand extends Command {

    public void execute(TaskList tasks, Ui ui, Storage storage) {
        if (tasks.nTasks() == 0) {
            ui.print("\tNo tasks added yet.");
        } else {
            ui.print("\tHere are the tasks in your list:");
            ui.print(tasks.toString());
        }
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof ListCommand) {
            return true;
        }
        return false;
    }

}
