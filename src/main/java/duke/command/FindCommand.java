package duke.command;

import duke.util.CliUi;
import duke.util.Storage;
import duke.util.TaskList;

/**
 * A command class that displays the search results from the list for a keyword.
 */
public class FindCommand extends Command {

    private static final String OUTPUT_MESSAGE = "Here are what I found: ";

    private final String keyword;

    /**
     * The standard constructor.
     */
    public FindCommand(String keyword) {
        super(CommandType.FIND);
        this.keyword = keyword;
    }

    /**
     * Executes the command concretely.
     * Displays the search results from the list for a keyword.
     *
     * @param cliUi An object that facilitates output that might be required by the command.
     * @param taskList An object that facilitates basic insert, edit, search, and delete operations
     *                 that this command might need.
     * @param storage An object that facilitates file IO and the save operation that command might need.
     */
    @Override
    protected void executeConcretely(CliUi cliUi, TaskList taskList, Storage storage) {
        String output = OUTPUT_MESSAGE + taskList.find(keyword);
        cliUi.printOutput(output);
    }
}
