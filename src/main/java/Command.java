abstract class Command {
    public static enum Commands {
        bye, list, help, mark, unmark, delete, todo, deadline, event, invalid;
    }
    public abstract void execute() throws DukeException;
    public abstract boolean isExit();

    public static Commands checkEnums(String command) {
        for (Commands e : Commands.values()) {
            if (e.name().equals(command))
                return e;
        }
        return Commands.invalid;
    }
}
