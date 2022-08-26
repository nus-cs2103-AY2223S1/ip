package duke.command;

import duke.util.Storage;
import duke.util.TaskList;
import duke.util.Ui;

public class ExitCommand extends Command {

    private static final String EXIT_OUTPUT_STRING = "Bye! See you next time!";

    public ExitCommand() {
        super(CommandType.EXIT);
    }

    @Override
    public void executeConcretely(Ui ui, TaskList taskList, Storage storage) {
        ui.printOutput(EXIT_OUTPUT_STRING);
    }
}
