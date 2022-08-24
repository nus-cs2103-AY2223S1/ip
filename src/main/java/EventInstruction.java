import java.time.temporal.TemporalAccessor;
import java.util.Map;

public class EventInstruction extends Instruction {
    public EventInstruction(String mainArgument, Map<String, String> flagArgumentsMap) {
        super(mainArgument, flagArgumentsMap);
    }

    @Override
    public void execute(TaskList taskList) {
        if (!this.hasMainArgument()) {
            System.out.println("Sorry, I will need a description for the event.");
            return;
        }
        String endDateTimeString = this.getFlagArgument("at");
        if (endDateTimeString == null) {
            System.out.println("Sorry, I will need a date for the event.");
            return;
        }

        TemporalAccessor endDateTime = DateTimeUtil.parseCompactDateTime(endDateTimeString);
        Event event = new Event(this.getMainArgument(), endDateTime);
        taskList.addTask(event);
        System.out.println("Got it, I've added this event:");
        System.out.println(event);
    }
}
