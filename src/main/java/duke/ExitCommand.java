package duke;

/**
 * Command that tells the App the terminate
 */
public class ExitCommand extends Command {

    @Override
    void execute(TaskList tasks, Ui ui, Storage storage) {

    }

    @Override
    boolean isExit() {
        return true;
    }
}
