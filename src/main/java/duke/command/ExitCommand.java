package duke.command;

public class ExitCommand extends Command {
    private static final String GOODBYE = "Bye. Hope to see you again soon!" + "\n";

    @Override
    public String action() {
        return GOODBYE;
    }
}
