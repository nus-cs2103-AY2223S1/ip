import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class TaskDeadline extends Task{

    private LocalDate deadline;

    public TaskDeadline(String name, boolean done, String deadline) throws TaskNoNameException {
        super(name, done);
        this.deadline = LocalDate.parse(deadline);
    }

    public static String makeDeadline(Scanner s, List<Task> taskList) {

        System.out.print("What is the name of your deadline task: ");
        String name = s.nextLine();

        System.out.print("When is the deadline of your task, type in YYYY-MM-DD: ");
        String deadline = s.nextLine();

        try {
            Task task = new TaskDeadline(name, false, deadline);
            taskList.add(task);
            return "I created a Deadline " + task + " for you. You have " + taskList.size() + " task(s) now.";
        } catch (TaskNoNameException e) {
            return e.getMessage();
        }

    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + DukeDateTimeFormatter.formatDisplay(this.deadline) + ")";
    }

    @Override
    public String toStorageString() {
        return super.toStorageString() + "|||" + "deadline" + "|||" +
                DukeDateTimeFormatter.formatStorage(this.deadline);
    }

}
