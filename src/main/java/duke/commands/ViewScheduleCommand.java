package duke.commands;

import java.time.LocalDateTime;

import duke.utils.Storage;
import duke.utils.TaskList;
/**
 * Represents an executable command.
 *
 * @author sikai00
 */
public class ViewScheduleCommand extends Command {
    /** Command identifier used by Parser **/
    public static final String COMMAND_WORD = "view";
    private final LocalDateTime startDateRange;
    private final LocalDateTime endDateRange;

    /**
     * Initializes a new ViewScheduleCommand instance.
     *
     * @param startDateRange Start of datetime range
     * @param endDateRange End of datetime range (inclusive)
     */
    public ViewScheduleCommand(LocalDateTime startDateRange, LocalDateTime endDateRange) {
        this.startDateRange = startDateRange;
        this.endDateRange = endDateRange;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public CommandResult execute(TaskList taskList, Storage storage) {
        TaskList results = taskList.findDatedTasks(this.startDateRange, this.endDateRange);
        String msg = "Here are the matching tasks in your list:\n";
        if (results.size() > 0) {
            msg += results.toString();
        } else {
            msg = "There are no matching tasks in your list.";
        }
        return new CommandResult(msg);
    }
}
