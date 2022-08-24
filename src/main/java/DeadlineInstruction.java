import java.time.temporal.TemporalAccessor;
import java.util.Map;

public class DeadlineInstruction extends Instruction {
    public DeadlineInstruction(String mainArgument, Map<String, String> flagArgumentsMap) {
        super(mainArgument, flagArgumentsMap);
    }

    @Override
    public void execute(TaskList taskList, Ui ui) {
        if (!this.hasMainArgument()) {
            ui.showMessages("Sorry, I will need a description for the deadline.");
            return;
        }
        String endDateTimeString = this.getFlagArgument("by");
        if (endDateTimeString == null) {
            ui.showMessages("Sorry, I will need a date for the deadline.");
            return;
        }

        TemporalAccessor endDateTime = DateTimeUtil.parseCompactDateTime(endDateTimeString);
        Deadline deadline = new Deadline(this.getMainArgument(), endDateTime);
        taskList.addTask(deadline);
        ui.showMessages("Got it, I've added this deadline:", deadline.toString());
    }
}
