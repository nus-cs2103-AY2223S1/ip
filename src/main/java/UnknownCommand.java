public class UnknownCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        String tryagain = "I'm sorry, but I do not understand your orders.";
        ui.printMessage(tryagain);
    }
}
