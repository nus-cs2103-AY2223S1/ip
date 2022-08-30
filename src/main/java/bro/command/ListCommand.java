package bro.command;

import bro.Storage;
import bro.TaskList;
import bro.Ui;

public class ListCommand extends Command {

    public ListCommand(){}

    @Override
    public void execute(TaskList tasklist, Ui ui, Storage storage) {
        tasklist.listAll();
    }
}
