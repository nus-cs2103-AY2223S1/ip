package duke.command;

import java.util.ArrayList;

/**
 * Command to handle when user provides an input that is not understood.
 */
public class UnknownCommand extends Command {

    /**
     * Prints unknown command provided message, and the list of available user commands.
     */
    @Override
    public void execute() {
        ArrayList<String> toPrint = new ArrayList<>();
        toPrint.add("Unknown command provided!");
        String[] helpGuide = HelpCommand.getHelpGuide();
        for (String line: helpGuide) {
            toPrint.add(line);
        }
        Command.ui.printMessages(toPrint.toArray(new String[0]));
    }
}
