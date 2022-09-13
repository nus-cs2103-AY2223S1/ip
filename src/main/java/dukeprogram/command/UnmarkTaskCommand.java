package dukeprogram.command;

import dukeprogram.Duke;

/**
 * UnmarkTaskCommand marks a task as completed within the task list of this current Duke instance
 */
public class UnmarkTaskCommand extends AnnotateTaskCommand {

    /**
     * Creates a new UnmarkTaskCommand
     * @param duke the instance of duke that spawned this command
     */
    public UnmarkTaskCommand(Duke duke) {
        super(duke, false, "Okay, I've unmarked this task.");
    }
}
