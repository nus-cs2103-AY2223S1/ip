package command;

public class CommandPair {
    private Command command;
    private String description;

    public CommandPair(Command command) {
        this.command = command;
        this.description = "";
    }

    public CommandPair(Command command, String description) {
        this.command = command;
        this.description = description;
    }

    public Command getCommand() {
        return this.command;
    }

    public String getDescription() {
        return this.description;
    }
}
