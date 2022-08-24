public class ExitCommand extends Command {
    private static final String GOOD_BYE_MSG = "Bye. Hope to see you again soon!";

    @Override
    public String execute() {
        return ExitCommand.GOOD_BYE_MSG;
    }
}
