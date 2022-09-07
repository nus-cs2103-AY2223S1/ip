package kirby.commands;

import kirby.Storage;
import kirby.TaskList;
import kirby.ui.Ui;

/**
 * HelpCommand class handles the command to list all the available commands.
 */
public class HelpCommand extends Command {
    private static final String listOfAllCommands =
            new StringBuilder().append("Feeling confused? \n" + "Here's the list of all commands you can use: \n\n")
                    .append("A. Create a task \n")
                    .append("1. todo <description> \n")
                    .append("2. event <description> /at <time> \n")
                    .append("3. deadline <description> /by <time> \n")
                    .append("where <time> can be in the form of a valid date 'yyyy-mm-dd'\n\n")
                    .append("B. Fetch tasks \n")
                    .append("1. list \n")
                    .append("2. get <date> \n\n")
                    .append("C. Mark complete or incomplete \n")
                    .append("1. mark <task number> \n")
                    .append("2. unmark <task number> \n\n")
                    .append("D. Delete task \n")
                    .append("delete <task number> \n\n")
                    .append("E. Search a task \n")
                    .append("find <keyword> \n\n")
                    .append("F. Exit program \n")
                    .append("bye").toString();
    /**
     * {@inheritDoc}
     * Returns the list of all available commands.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return listOfAllCommands;
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
