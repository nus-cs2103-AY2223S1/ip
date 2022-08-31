package scottie.instructions;

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
            ui.showMessages("Sorry, I will need a description for the event.");
            return;
        }
        String endDateTimeString = this.getFlagArgument("at");
        if (endDateTimeString == null) {
            ui.showMessages("Sorry, I will need a date for the event.");
            return;
        }

        TemporalAccessor endDateTime = DateTimeUtil.parseCompactDateTime(endDateTimeString);
        Event event = new Event(this.getMainArgument(), endDateTime);
        taskList.addTask(event);
        ui.showMessages("Got it, I've added this event:", event.toString());
    }
}
