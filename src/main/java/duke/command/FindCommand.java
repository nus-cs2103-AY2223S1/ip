package duke.command;

import duke.util.Storage;
import duke.util.TaskList;
import duke.util.Ui;

public class FindCommand extends Command {

    private static final String OUTPUT_MESSAGE = "Here are what I found: ";

    private String keyword;

    FindCommand(String keyword) {
        super(CommandType.FIND);
        this.keyword = keyword;
    }

    @Override
    protected void executeConcretely(Ui ui, TaskList taskList, Storage storage) {
        String output = OUTPUT_MESSAGE + taskList.find(keyword);
        ui.printOutput(output);
    }
}
