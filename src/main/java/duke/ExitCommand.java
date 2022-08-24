package duke;

/**
 * Command that tells the App the terminate
 */
public class ExitCommand extends Command {

    @Override
    void execute(TaskList tasks, Ui ui, Storage storage) {

    }

    /**
     * @return 
     */
    @Override
    boolean isExit() {
        return true;
    }
}
