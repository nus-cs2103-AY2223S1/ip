package dukeprogram.command;

import dukeprogram.Duke;

/**
 * MarkTaskCommand marks a task as completed within the task list of this current Duke instance
 */
public class MarkTaskCommand extends AnnotateTaskCommand {

    /**
     * Creates a new MarkTaskCommand
     * @param duke the instance of duke that spawned this command
     */
    public MarkTaskCommand(Duke duke) {
        super(duke, true, "Alright, I've marked this task as done.");
    }
}
