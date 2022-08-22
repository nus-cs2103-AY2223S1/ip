import java.util.List;

public class ByeCommand extends Command {
    public ByeCommand(String command) {
        super(command);
    }

    @Override
    public String execute(TaskList tasks, Storage storage) {
        return "Bye! Hope to see you again soon!";
    }
}
