package duke.command;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Represents an Exit Command, which quits Duke's service to user.
 *
 * @author Elgin
 */
public class ExitCommand extends Command {
    /**
     * Executes the Exit command, and stops Duke and user interaction, ending off with a bye.
     *
     * @param tasks All tasks present in Duke.
     * @param ui The UI controller that handles interaction between user and Duke.
     * @param storage Storage that stores all tasks on Disk.
     * @return Duke's message to the user.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        storage.save(tasks);

        // Timeout before exiting program (3 seconds).
        new Thread(() -> {
            try {
                Runnable runnable = () -> System.exit(0);
                Thread.sleep(3000);
                runnable.run();
            } catch (Exception e) {
                System.err.println(e);
            }
        }).start();

        return ui.byeMsg();
    }
}
