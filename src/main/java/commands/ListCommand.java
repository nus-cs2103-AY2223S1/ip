package commands;

import duke.Parser;
import duke.Ui;

public class ListCommand implements Command {

    /**
     * Lists out all the items in the TaskList.
     *
     * @param parser
     */
    @Override
    public void execute(Parser parser) {
        Ui.showLine();
        System.out.println("\tHere are the tasks in your list:");
        parser.getTaskList().showList();
        Ui.showLine();
    }
}
