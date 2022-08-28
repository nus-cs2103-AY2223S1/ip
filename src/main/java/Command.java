public abstract class Command {
    private String input;

    public Command(String input) {
        this.input = input;
    }

    public String getInput() {
        return input;
    }

    public abstract void execute(TaskList tasks, Ui ui, Storage storage);

    public boolean isExit() {
        return false;
    }
}
