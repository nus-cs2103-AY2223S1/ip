public class TodoCommand extends TaskCommand {
    public static final String COMMAND_WORD = "todo";

    public TodoCommand(String command) {
        super(new Todo(command));
    }
}
