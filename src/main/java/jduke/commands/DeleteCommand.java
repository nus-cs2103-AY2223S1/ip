package jduke.commands;

public class DeleteCommand extends Command {
    public static final String COMMAND_WORD = "delete";
    public static final String FORMAT = "delete <integer>";

    private final String deleteParams;

    public DeleteCommand(String deleteParams) {
        this.deleteParams = deleteParams;
    }
    @Override
    public String execute() {
        return tasklist.deleteTask(this.deleteParams);
    }
}
