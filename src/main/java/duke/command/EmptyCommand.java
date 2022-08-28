package duke.command;

import duke.util.Ui;

import java.util.ArrayList;

/**
 * Command to handle when user provides empty input.
 */
public class EmptyCommand extends Command {

    /**
     * Prints user input entered is empty message, and the list of available user commands.
     */
    @Override
    public String execute() {
        ArrayList<String> toPrint = new ArrayList<>();
        toPrint.add("No command entered!");
        String[] helpGuide = HelpCommand.getHelpGuide();
        for (String line: helpGuide) {
            toPrint.add(line);
        }
        return Ui.formatMessages(toPrint.toArray(new String[0]));
    }
}
