package pluto.command;

import pluto.PlutoException;
import pluto.Storage;
import pluto.TaskList;
import pluto.Ui;

import java.io.IOException;

public class FindCommand extends Command {

    private String keyword;

    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) {
        TaskList t = tasks.filter(keyword);
        if (t.nTasks() == 0) {
            ui.print("\tNo tasks found.");
        } else {
            ui.print("\tHere are the matching tasks in your list:");
            ui.print(t.toString());
        }

    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof FindCommand) {
            FindCommand other = (FindCommand) o;
            return this.keyword.equals(other.keyword);
        }
        return false;
    }

}
