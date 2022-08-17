import java.util.List;

public class ByeCommand extends Command {
    private static final String EXIT_MESSAGE = "Bye! Hope to see you again soon!";

    public ByeCommand(String command) {
        super(command);
    }

    @Override
    public String execute(List<Task> tasks) {
        return EXIT_MESSAGE;
    }
}
