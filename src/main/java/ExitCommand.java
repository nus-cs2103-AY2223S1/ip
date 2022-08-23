public class ExitCommand extends Command {

    ExitCommand(String command) {
        super(command);
    }

    private static final String COMMAND_ID = "exit";

    @Override
    public void execute(TaskRecords taskList, BotUI ui) {

    }

    @Override
    public boolean isExit() {
        return true;
    }
}
