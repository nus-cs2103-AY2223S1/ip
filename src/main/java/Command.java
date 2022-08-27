public abstract class Command {
    private String command;
    private String inputString;
    public Command(String inputString) {
        this.inputString = inputString;
        this.command = inputString.split(" ")[0];
    }

    public Command() {
    }

    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws KirbyMissingArgumentException;

public abstract boolean isExit();
}
