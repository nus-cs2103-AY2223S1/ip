package duke.command;

import duke.util.Ui;

import java.util.ArrayList;

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
     * Prints the list of available user commands and their usage.
     */
    @Override
    public String execute() {
        return Ui.formatMessages(HelpCommand.getHelpGuide());
    }
}
