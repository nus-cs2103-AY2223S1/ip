package duke.command;

import duke.constant.PriorityLevel;
import duke.exception.DukeException;
import duke.exception.InvalidIndexException;
import duke.exception.InvalidPriorityException;
import duke.task.TaskList;

/**
 * a calls to represent the priority command.
 */
public class PriorityCommand extends Command {
    public static final String COMMAND = "priority";
    private int targetIndex;
    private String priorityLevel;

    /**
     * Constructs a new PriorityCommand instance.
     *
     * @param description the command description.
     * @throws DukeException If Index or priority is not valid.
     */
    public PriorityCommand(String description) throws DukeException {
        String[] splitted = description.split(" ", 2);
        String indexString = splitted[0].trim();
        try {
            targetIndex = Integer.parseInt(indexString);
        } catch (Exception e) {
            throw new InvalidIndexException();
        }

        if (splitted.length == 1) {
            throw new InvalidPriorityException();
        }

        String priority = splitted[1].trim();
        switch (priority) {
        case PriorityLevel.LOW: case PriorityLevel.MEDIUM:
        case PriorityLevel.HIGH: case PriorityLevel.NONE:
            priorityLevel = priority;
            break;

        default:
            throw new InvalidPriorityException();
        }
    }

    @Override
    public String executeAndGetResponse(TaskList taskList) throws DukeException {
        return taskList.setPriority(targetIndex, priorityLevel);
    }
}
