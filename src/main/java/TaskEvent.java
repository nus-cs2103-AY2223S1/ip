import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class TaskEvent extends Task{

    private LocalDate startTime;
    private LocalDate endTime;

    public TaskEvent(String name, boolean done, String startTime, String endTime) throws TaskNoNameException {
        super(name, done);
        this.startTime = LocalDate.parse(startTime);
        this.endTime = LocalDate.parse(endTime);
    }

    public static String makeEvent(Scanner s, List<Task> taskList) {

        System.out.print("What is the name of your event: ");
        String name = s.nextLine();

        System.out.print("What is the start time of your event: ");
        String start = s.nextLine();

        System.out.print("What is the end time of your event: ");
        String end = s.nextLine();

        try {
            Task task = new TaskEvent(name, false, start, end);
            taskList.add(task);
            return "I created a Event " + task + " for you. You have " + taskList.size() + " task(s) now.";
        } catch (TaskNoNameException e) {
            return e.getMessage();
        }

    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from " +
                DukeDateTimeFormatter.formatDisplay(this.startTime) + " to " +
                DukeDateTimeFormatter.formatDisplay(this.endTime) + ")";
    }

    @Override
    public String toStorageString() {
        return super.toStorageString() + "|||" + "event" + "|||" +
                DukeDateTimeFormatter.formatStorage(this.startTime) + "|||" +
                DukeDateTimeFormatter.formatStorage(this.endTime);
    }

}