package commands;

import duke.Storage;
import duke.Ui;
import tasks.TaskList;

public class HelpCommand extends Command {
    /**
     * Constructor for HelpCommand
     */
    public HelpCommand() {
        super();
    }

    @Override
    public String execute(TaskList taskList, Ui ui, Storage s) {
        return ui.printHelp();
    }
}
