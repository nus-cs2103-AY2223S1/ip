package duke.command;

import java.util.ArrayList;

import duke.DukeException;


public class HelpCommand extends Command {
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

    @Override
    public void execute() throws DukeException {
        Command.ui.printMessages(HelpCommand.getHelpGuide());
    }
}
