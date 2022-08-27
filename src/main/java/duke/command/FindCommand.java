package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

public class FindCommand extends Command {
    private String keyword;

    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        String result = tasks.find(keyword);
        if (result.isEmpty()) {
            ui.printString("Sorry, none of your tasks match the search temrs.");
            return;
        }
        ui.printString("Here are the matching tasks in your list:\n" + result);
    }
}
