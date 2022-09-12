package alpha.command;

import alpha.FileOperations;
import alpha.TaskList;
import alpha.Ui;

/**
 * Displays all the commands and their required format.
 */
public class Help extends Command {
    /**
     * {@inheritDoc}
     *
     * Returns all the recognised input commands and their expected format.
     * @return String containing list of commands and their format.
     */
    @Override
    public String execute(TaskList tasks, Ui uI, FileOperations fileOperations) {
        return uI.returnText(
                "COMMAND\t" + "FORMAT\n"
                + "1. todo    \t\t" + "todo description\n"
                + "2. event   \t\t" + "event description /on yyyy-mm-dd\n"
                + "3. deadline\t" + "deadline description /by yyyy-mm-dd\n"
                + "4. mark    \t\t" + "mark task number\n"
                + "5. unmark  \t" + "unmark task number\n"
                + "6. delete  \t\t" + "delete task number\n"
                + "7. list    \t\t" + "list\n");
    }
}
