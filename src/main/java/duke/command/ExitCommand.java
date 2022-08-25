package duke.command;
import duke.task.TaskList;
import duke.util.Storage;
import duke.util.Ui;

/**
 * Insert Javadocs
 */
public class ExitCommand extends Command {
    public ExitCommand() {
    }

    /**
     * Insert Javadocs
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        System.out.println(this);
    }

    /**
     * Insert Javadocs
     */
    public boolean isExit() {
        return true;
    }

    @Override
    public String toString() {
        return "__________________________________________________\n"
                + "Adios Amigo! See you soon!";
    }
}
