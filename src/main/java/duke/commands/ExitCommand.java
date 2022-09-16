package duke.commands;

public class ExitCommand extends Command {

    /**
     * Constructs a new command to exit the app.
     */
    public ExitCommand() {
    }

    /**
     * Exits the App.
     * @return
     */
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
