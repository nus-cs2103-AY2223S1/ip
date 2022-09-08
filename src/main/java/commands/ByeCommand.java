package commands;

import duke.Parser;
import duke.Storage;
import duke.Ui;

public class ByeCommand implements Command {

    /**
     * Closes the parser's scanner, saves the current TaskList
     * @param parser
     */
    @Override
    public void execute(Parser parser) {
        parser.closeScanner();
        Storage.save(parser.getTaskList());
        Ui.showGoodbye();
    }
}
