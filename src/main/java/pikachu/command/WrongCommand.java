package pikachu.command;

import pikachu.PikachuEmotion;
import pikachu.Storage;
import pikachu.TaskList;
import pikachu.Ui;

/**
 * Represents command that is invalid. A <code>WrongCommand</code> object corresponds to
 * an instruction that is invalid.
 */
public class WrongCommand extends Command {
    /**
     * Deals with unidentified messages for the task manager Pikachu.
     *
     * @param tasks Task List of all tasks currently.
     * @param ui Ui for user to see.
     * @param storage Storage in charge of the current tasks.
     * @return Pikachu's reply.
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return "Pi?";
    }

    /**
     * Returns PikaChu's emotion after receiving the task.
     * @return CONFUSED.
     */
    @Override
    public PikachuEmotion getEmotion() {
        return PikachuEmotion.CONFUSED;
    }

    /**
     * Returns whether this function performs an exit action on the task manager.
     * @return true, exit.
     */
    public boolean isExit() {
        return false;
    }
}
