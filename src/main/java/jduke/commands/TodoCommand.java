package jduke.commands;

public class TodoCommand extends Command {
    public static final String COMMAND_WORD = "todo";
    public static final String FORMAT = "todo <description>";

    private final String params;
    public TodoCommand(String params) {
        this.params = params;
    }

    @Override
    public String execute() {
        return this.tasklist.addTodo(this.params);
    }
}
