package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.UI;
import duke.components.Emoji;

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
        String responseMessage = Emoji.CHERRY_BLOSSOM + " You may try\n"
                + " " + Emoji.SNOW_FLAKE + " todo <task>\t\t"
                + " " + Emoji.SNOW_FLAKE + " event <task> /at <time>\n"
                + " " + Emoji.SNOW_FLAKE + " deadline <task> /by <date>\n"
                + " " + Emoji.SNOW_FLAKE + " mark/unmark <index>\t"
                + " " + Emoji.SNOW_FLAKE + " find <keyword>\n"
                + " " + Emoji.SNOW_FLAKE + " help\t\t"
                + " " + Emoji.SNOW_FLAKE + " list\t\t"
                + " " + Emoji.SNOW_FLAKE + " bye";
        return responseMessage;
    }
}
