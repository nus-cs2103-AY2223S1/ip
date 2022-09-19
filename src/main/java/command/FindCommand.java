package command;

import storage.Storage;

import tasklist.TaskList;

import ui.Ui;

public class FindCommand extends Command {
    private String query;

    public FindCommand(String query) {
        this.query = query;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        String results = tasks.find(query);
        ui.showFound(results);
    }
}
