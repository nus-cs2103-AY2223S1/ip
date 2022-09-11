package duke;

/**
 * Encapsulate a command that allows user to exit the program,
 * which is-a Command.
 */
public class ExitCommand extends Command {

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        this.setIsExit(true);
        return ui.showBye();
    }
}
