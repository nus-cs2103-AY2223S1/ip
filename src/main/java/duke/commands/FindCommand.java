package duke.commands;

import duke.exception.DukeException;
import duke.main.Storage;
import duke.main.Ui;
import duke.tasks.Task;
import duke.tasks.TaskList;

import java.util.ArrayList;

public class FindCommand extends Command{
    private String keyword;

    public FindCommand(String description) throws DukeException {
        String[] addlst = description.split(" ", 2);
        if (addlst.length < 2) {
            throw new DukeException("Keyword missing!");
        }
        this.keyword = addlst[1];
    }
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        ArrayList<Task> filtered = tasks.find(this.keyword);

        System.out.println("Here are the matching tasks in your list:");
        for (int i = 0; i < filtered.size(); i++) {
            System.out.println((i+1) + "." + filtered.get(i).toString());
        }
    };
}
