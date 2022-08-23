abstract class Command {

    private final String command;

    Command(String command) {
        this.command = command;
    }

    String getCommand() {
        return this.command;
    }

    abstract void execute(TaskRecords taskList, BotUI ui) throws DukeException;

    abstract boolean isExit();
}
