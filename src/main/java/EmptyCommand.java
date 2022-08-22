import java.util.ArrayList;

public class EmptyCommand extends Command {
    @Override
    public void execute() {
        ArrayList<String> toPrint = new ArrayList<>();
        toPrint.add("No command entered!");
        String[] helpGuide = HelpCommand.getHelpGuide();
        for (String line: helpGuide) {
            toPrint.add(line);
        }
        Command.ui.printMessages(toPrint.toArray(new String[0]));
    }
}
