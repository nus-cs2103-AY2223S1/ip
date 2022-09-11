package duke.command;

import java.util.ArrayList;

import duke.util.Ui;

/**
 * Command to handle when user provides empty input.
 */
public class EmptyCommand extends Command {

    /**
     * Returns user input entered is empty message, and the list of available user commands.
     *
     * @return Message {@code String} from command execution.
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
