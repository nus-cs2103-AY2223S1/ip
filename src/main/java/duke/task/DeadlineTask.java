package duke.task;

import java.time.LocalDate;

/**
 * Represents a deadline task.
 */
public class DeadlineTask extends Task {
    protected LocalDate by;

    public DeadlineTask(String description, LocalDate by) {
        super(description);
        this.by = by;
    }

    @Override
    public String toString() {
        int day = by.getDayOfMonth();
        String dayOfMonth = day < 10 ? "0" + day : "" + day;
        String month = by.getMonth().toString().substring(0, 3);
        String year = by.getYear() + "";
        String formattedDate = month + " " + dayOfMonth + " " + year;
        return "[D]" + super.toString() + " (by: " + formattedDate + ")";
    }
    
    public int compareTo(Task task) {
        if (!(task instanceof DeadlineTask)) {
            return -1;
        }
        DeadlineTask dTask = (DeadlineTask) task;
        return this.by.isBefore(dTask.by)
                ? -1
                : this.by.isAfter(dTask.by)
                ? 1
                : 0;
    }
}
