package jduke.commands;

public class ListCommand extends Command {
    public static final String COMMAND_WORD = "list";
    public static final String FORMAT = "list <dd/mm/yyyy | optional>";

    private final String listParams;

    public ListCommand(String listParams) {
        this.listParams = listParams;
    }

    @Override
    public String execute() {
        return tasklist.listTasks(listParams);
    }
}
