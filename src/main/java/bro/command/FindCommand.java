package bro.command;

import bro.BroException;
import bro.Storage;
import bro.TaskList;
import bro.Ui;

public class FindCommand extends Command {
    private String keyword;

    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public void execute(TaskList tasklist, Ui ui, Storage storage) throws BroException {
        tasklist.findTask(keyword);
    }
}
