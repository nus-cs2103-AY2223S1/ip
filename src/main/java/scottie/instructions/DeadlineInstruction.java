package scottie.instructions;

import java.time.format.DateTimeParseException;
import java.time.temporal.TemporalAccessor;
import java.util.Map;

import scottie.common.DateTimeUtil;
import scottie.tasks.Deadline;
import scottie.tasks.TaskList;
import scottie.ui.Ui;

/**
 * Encapsulates the deadline instruction which is used to
 * add a deadline to the task list.
 */
class DeadlineInstruction extends Instruction {
    private static final String MISSING_DESCRIPTION_MESSAGE = "Um... I'll need a description for the deadline, buddy.";
    private static final String MISSING_DATE_MESSAGE = "Um... I'll need a date for the deadline, buddy.";
    private static final String INVALID_DATE_MESSAGE = "Um... I've never seen a date formatted like that before...";
    private static final String DEADLINE_ADDED_MESSAGE = "Ok got it, I've added this deadline:";

    /**
     * Constructs a DeadlineInstruction with the given arguments.
     *
     * @param mainArgument The main argument provided to this instruction.
     * @param flagArgumentsMap The map between flags and values provided to
     *                         this instruction.
     */
    DeadlineInstruction(String mainArgument, Map<String, String> flagArgumentsMap) {
        super(mainArgument, flagArgumentsMap);
    }

    /**
     * Adds a new Deadline to the given TaskList.
     * The main argument of this instruction is used as the Deadline's
     * description and the "by" flag argument is used as the end date time.
     *
     * @param taskList The TaskList to add the Deadline to.
     * @param ui The Ui used to display messages to the user.
     */
    @Override
    public void execute(TaskList taskList, Ui ui) {
        if (!this.hasMainArgument()) {
            ui.showError(MISSING_DESCRIPTION_MESSAGE);
            return;
        }
        String endDateTimeString = this.getFlagArgument("by");
        if (endDateTimeString == null) {
            ui.showError(MISSING_DATE_MESSAGE);
            return;
        }

        TemporalAccessor endDateTime;
        try {
            endDateTime = DateTimeUtil.parseCompactDateTime(endDateTimeString);
        } catch (DateTimeParseException e) {
            ui.showError(INVALID_DATE_MESSAGE);
            return;
        }

        Deadline deadline = new Deadline(this.getMainArgument(), endDateTime);
        taskList.addTask(deadline);
        ui.showMessages(DEADLINE_ADDED_MESSAGE, deadline.toString());
    }
}
