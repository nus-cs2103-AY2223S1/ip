public abstract class Command {
    public enum Commands {
        mark, unmark, todo, deadline, event, delete, bye, list
    }

    protected String command;

    public Command(String command) {
        this.command = command;
    }

    public abstract void execute();
}
