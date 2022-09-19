package pikachu.command;

import pikachu.PikachuEmotion;
import pikachu.Storage;
import pikachu.TaskList;
import pikachu.Ui;

/**
 * Represents command that exits the task manager Pikachu. A <code>ExitCommand</code> object corresponds to
 * an instruction to exit task manager Pikachu e.g., <code>bye</code>.
 */
public class ExitCommand extends Command {

    /**
     * Quits the task manager Pikachu.
     *
     * @param tasks Task List of all tasks currently.
     * @param ui Ui for user to see.
     * @param storage Storage in charge of the current tasks.
     * @return Pikachu's reply.
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return ui.sayBye();
    }

    /**
     * Returns PikaChu's emotion after receiving the task.
     * @return CRY.
     */
    @Override
    public PikachuEmotion getEmotion() {
        return PikachuEmotion.CRY;
    }

    /**
     * Returns whether this function performs an exit action on the task manager.
     * @return true, exit.
     */
    public boolean isExit() {
        return true;
    }
}
