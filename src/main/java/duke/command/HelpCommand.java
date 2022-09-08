package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.UI;

/**
 * Representation of the help command.
 */
public class HelpCommand extends Command {
    /**
     * Checks if AddCommand is an exist command.
     * @return false.
     */
    @Override
    public boolean isExit() {
        return false;
    }

    /**
     * Executes the HelpCommand.
     * @param taskList TaskList object to add to taskList.
     * @param ui UI object to print to user.
     * @param storage Storage object which saves and loads the taskList.
     */
    @Override
    public void execute(TaskList taskList, UI ui, Storage storage) {
        String mess = "You may try\n"
                + "todo <description>\n"
                + "deadline <description> /by <date>\n"
                + "event <description> /at time\n"
                + "list\n"
                + "mark <index>\n"
                + "unmark <index>\n"
                + "find <keyword>\n"
                + "help\n"
                + "bye";
        ui.printWithDivider(mess);
    }

    /**
     * Returns message from the HelpCommand.
     * @param taskList TaskList object to add to taskList.
     * @param ui UI object to print to user.
     * @param storage Storage object which saves and loads the taskList.
     */
    @Override
    public String getResponse(TaskList taskList, UI ui, Storage storage) {
        String responseMessage = "You may try\n"
                + "\ttodo <description>\n"
                + "\tdeadline <description> /by <date>\n"
                + "\tevent <description> /at time\n"
                + "\tlist\n"
                + "\tmark <index>\n"
                + "\tunmark <index>\n"
                + "\tfind <keyword>\n"
                + "\thelp\n"
                + "\tbye";
        return responseMessage;
    }
}
