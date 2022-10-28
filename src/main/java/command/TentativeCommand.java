package command;

import java.time.LocalDateTime;

import exceptions.HenryException;
import henry.Task;

/**
 * Adds a tentative date for an {@link EventCommand}.
 */
public class TentativeCommand extends Command {
    public static final String COMMAND_WORD = "tentative";
    private static final String MESSAGE_ADD_DATE_SUCCESS = "OK, I've added a tentative date for this event:\n %1$s";
    private static final String MESSAGE_CONFIRM_DATE_SUCCESS = "Date confirmed! This event has been modified:\n %1$s";
    private final int index;
    private final int dateToChooseIndex;
    private final LocalDateTime dateTime;

    /**
     * Creates a TentativeCommand with the given index and dateTime.
     *
     * @param givenIndex    the index of the Event to be modified
     * @param givenDateTime the tentative date/time to be added to the Event
     */
    public TentativeCommand(int givenIndex, LocalDateTime givenDateTime) {
        this.index = givenIndex;
        this.dateToChooseIndex = -1;
        this.dateTime = givenDateTime;
    }

    /**
     * Creates a TentativeCommand with the given index and dateIndex.
     *
     * @param givenIndex     the index of the Event to be modified
     * @param givenDateIndex the index of the chosen tentative date/time to confirm
     */
    public TentativeCommand(int givenIndex, int givenDateIndex) {
        this.index = givenIndex;
        this.dateToChooseIndex = givenDateIndex;
        this.dateTime = LocalDateTime.MAX;
    }

    @Override
    public CommandResult execute() {
        Task task = taskList.get(index);
        if (task.getType() != Commands.EVENT) {
            throw new HenryException("TASK IS NOT AN EVENT!");
        }
        if (dateToChooseIndex == -1) {
            task.addTentativeDate(dateTime);
            taskList.set(index, task);
            return new CommandResult(String.format(MESSAGE_ADD_DATE_SUCCESS, task), taskList);
        } else {
            task.confirmDate(dateToChooseIndex);
            taskList.set(index, task);
            return new CommandResult(String.format(MESSAGE_CONFIRM_DATE_SUCCESS, task), taskList);
        }
    }
}
