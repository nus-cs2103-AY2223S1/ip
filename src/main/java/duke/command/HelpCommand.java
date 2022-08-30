package duke.command;

import java.util.ArrayList;

import duke.util.Ui;

/**
 * Command to print all available user commands and their usage.
 */
public class HelpCommand extends Command {

    /**
     * Returns a String array of user commands and their usage.
     *
     * @return String array of user commands and their usage.
     */
    public static String[] getHelpGuide() {
        ArrayList<String> helpGuide = new ArrayList<>();
        helpGuide.add("Here are the list of commands:");
        int i = 1;
        for (CommandUsage commandUsage : CommandUsage.values()) {
            helpGuide.add(String.format("%d. %s", i, commandUsage.toString()));
            i++;
        }
        return helpGuide.toArray(new String[0]);
    }

    /**
     * Returns a {@code String} containing the list of available user commands and their usage.
     *
     * @return Message {@code String} from command execution.
     */
    @Override
    public String execute() {
        return Ui.formatMessages(HelpCommand.getHelpGuide());
    }
}
