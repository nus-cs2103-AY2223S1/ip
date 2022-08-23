package duke.command;

import java.util.ArrayList;

public class UnknownCommand extends Command {
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
