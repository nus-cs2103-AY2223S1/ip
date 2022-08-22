import duke.exceptions.DukeException;

public class ExitCommand extends Command {

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.byeMessage();
        ui.printDivider();
    }

    @Override
    public String getCommand() {
        return "exit";
    }
}
