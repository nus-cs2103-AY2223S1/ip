package duke.command;

public class Command {
    private DukeCommandType commandType;
    private String args;

    public Command(DukeCommandType commandType, String args) {
        this.commandType = commandType;
        this.args = args;
    }

    public DukeCommandType getCommandType() {
        return this.commandType;
    }

    public String getArgs() {
        return this.args;
    }
}

