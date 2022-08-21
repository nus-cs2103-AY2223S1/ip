public class WelcomeCommand extends Command {
    public WelcomeCommand() {
        super(CommandType.WELCOME);
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.printWelcomeMessage();
    }
}

