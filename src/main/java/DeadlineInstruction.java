import java.time.temporal.TemporalAccessor;
import java.util.Map;

public class DeadlineInstruction extends Instruction {
    public DeadlineInstruction(String mainArgument, Map<String, String> flagArgumentsMap) {
        super(mainArgument, flagArgumentsMap);
    }

    @Override
    public void execute(TaskList taskList) {
        if (!this.hasMainArgument()) {
            System.out.println("Sorry, I will need a description for the deadline.");
            return;
        }
        String endDateTimeString = this.getFlagArgument("by");
        if (endDateTimeString == null) {
            System.out.println("Sorry, I will need a date for the deadline.");
            return;
        }

        TemporalAccessor endDateTime = DateTimeUtil.parseCompactDateTime(endDateTimeString);
        Deadline deadline = new Deadline(this.getMainArgument(), endDateTime);
        taskList.addTask(deadline);
        System.out.println("Got it, I've added this deadline:");
        System.out.println(deadline);
    }
}
