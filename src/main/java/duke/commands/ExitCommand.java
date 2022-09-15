package duke.commands;

public class ExitCommand extends Command {

    public ExitCommand() {
    }

    @Override
    public String execute() {
        System.exit(0);
        return "";
    }

    @Override
    public String undo() {
        return "";
    }

}
