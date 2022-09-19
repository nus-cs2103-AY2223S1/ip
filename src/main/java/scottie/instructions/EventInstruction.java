package scottie.instructions;

import java.time.format.DateTimeParseException;
import java.time.temporal.TemporalAccessor;
import java.util.Map;

import scottie.common.DateTimeUtil;
import scottie.tasks.Event;
import scottie.tasks.TaskList;
import scottie.ui.Ui;

/**
 * Encapsulates the event instruction which is used to
 * add an event to the task list.
 */
class EventInstruction extends Instruction {
    private static final String MISSING_DESCRIPTION_MESSAGE = "Um... I'll need a description for the event, buddy.";
    private static final String MISSING_DATE_MESSAGE = "Um... I'll need a date for the event, buddy.";
    private static final String INVALID_DATE_MESSAGE = "Um... I've never seen a date formatted like that before...";
    private static final String EVENT_ADDED_MESSAGE = "Ok got it, I've added this event:";

    /**
     * Constructs an EventInstruction with the given arguments.
     *
     * @param mainArgument The main argument provided to this instruction.
     * @param flagArgumentsMap The map between flags and values provided to
     *                         this instruction.
     */
    EventInstruction(String mainArgument, Map<String, String> flagArgumentsMap) {
        super(mainArgument, flagArgumentsMap);
    }

    /**
     * Adds a new Event to the given TaskList.
     * The main argument of this instruction is used as the Event's
     * description and the "at" flag argument is used as the date time.
     *
     * @param taskList The TaskList to add the Event to.
     * @param ui The Ui used to display messages to the user.
     */
    @Override
    public void execute(TaskList taskList, Ui ui) {
        if (!this.hasMainArgument()) {
            ui.showFormattedError(MISSING_DESCRIPTION_MESSAGE);
            return;
        }
        String endDateTimeString = this.getFlagArgument("at");
        if (endDateTimeString == null) {
            ui.showError(MISSING_DATE_MESSAGE);
            return;
        }

        TemporalAccessor dateTime;
        try {
            dateTime = DateTimeUtil.parseCompactDateTime(endDateTimeString);
        } catch (DateTimeParseException e) {
            ui.showError(INVALID_DATE_MESSAGE);
            return;
        }

        Event event = new Event(this.getMainArgument(), dateTime);
        taskList.addTask(event);
        ui.showMessages(EVENT_ADDED_MESSAGE, event.toString());
    }
}
