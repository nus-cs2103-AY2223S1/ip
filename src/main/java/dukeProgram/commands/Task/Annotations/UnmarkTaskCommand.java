package dukeprogram.commands.task.annotations;

/**
 * MarkTaskCommand marks a task as incomplete
 */
public class UnmarkTaskCommand extends AnnotateTaskCommand {

    public UnmarkTaskCommand() {
        super(false);
    }
}
