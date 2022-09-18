package jarvis.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * A class that represents a deadline, have a due date "by"
 */
public class Deadline extends Task {
    private LocalDateTime by;

    /**
     * Construct a deadline from users' input
     * @param input Include both description and specified time
     * @param isDone Whether the deadline is done when constructed
     * @throws DateTimeParseException Possible wrong time format when parsing the time
     */
    public Deadline(String input, boolean isDone) throws DateTimeParseException {
        super(isDone);
        String[] strArr = input.split("/by");
        this.description = strArr[0].trim();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        try {
            this.by = LocalDateTime.parse(strArr[1].trim(), formatter);
        } catch (DateTimeParseException e) {
            System.out.println("Wrong date format");
            throw e;
        }
    }

    /**
     * Construct a deadline from the data, used for loading stored data
     * @param input Description of the deadline
     * @param by Due date of the task, in string form
     * @param isDone Whether the deadline is marked as done in the data
     */
    public Deadline(String input, String by, boolean isDone) {
        super(isDone);
        this.description = input;
        try {
            this.by = LocalDateTime.parse(by);
        } catch (DateTimeParseException e) {
            System.out.println("Wrong date format");
            throw e;
        }
    }

    @Override
    public Task.TaskType getTaskType() {
        return Task.TaskType.Deadline;
    }

    /**
     * For storing deadline to data file
     * @return The string representation of this deadline in data file
     */
    @Override
    public String toDataForm() {
        String done = this.isDone ? "1" : "0";
        return "D|" + done + "|" + this.description + "|" + this.by + "\n";
    }

    @Override
    public String toString() {
        String date = this.by.format(DateTimeFormatter.ofPattern("dd MMM yyyy HH:mm"));
        String head = "[D][" + this.getStatusIcon() + "] ";
        String body = this.description + " (by: " + date + ")";
        return head + body;
    }


    /**
     * Compare the priority of this deadline with other tasks
     * Deadlines have higher priority than events and todos,
     * in this case we return -1
     * Compare among deadlines by their time. Earlier time has higher priority
     *
     * @param task2 the other task to be compared with
     * @return The priority -1, 0 or 1 as specified above
     */
    @Override
    public int compareTo(Task task2) {
        if (task2 instanceof Todo || task2 instanceof Event) {
            return -1;
        }
        Deadline _task2 = (Deadline) task2;
        return this.by.compareTo(_task2.by);
    }
}
