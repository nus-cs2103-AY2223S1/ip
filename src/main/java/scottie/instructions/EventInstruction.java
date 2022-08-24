package scottie.instructions;

import scottie.common.DateTimeUtil;
import scottie.tasks.Event;
import scottie.tasks.TaskList;
import scottie.ui.Ui;

import java.time.temporal.TemporalAccessor;
import java.util.Map;

class EventInstruction extends Instruction {
    EventInstruction(String mainArgument, Map<String, String> flagArgumentsMap) {
        super(mainArgument, flagArgumentsMap);
    }

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
