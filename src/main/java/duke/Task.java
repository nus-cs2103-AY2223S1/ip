package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * The Task class represents a task that the user wishes to record.
 */
class Task {
    private Boolean isDone;
    private final String description;

    /**
     * Constructs an instance of Task.
     *
     * @param description description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.markAsNotDone();
    }

    /**
     * Marks the associated Task as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Marks the associated Task as not done.
     */
    public void markAsNotDone() {
        this.isDone = false;
    }

    /**
     * Retrieves the description of the Task.
     *
     * @return description of the Task.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Retrieves the completion status of the Task.
     *
     * @return whether the Task is done or not done.
     */
    public Boolean getStatus() {
        return this.isDone;
    }

    /**
     * Returns the record of the Task's description and completion status.
     *
     * @return the record of the Task.
     */
    public String printTask() {
        return String.format("[%s] %s",
                this.getStatus() ? "X" : " ",
                this.getDescription());
    }

    /**
     * Retrieves a character representing the type of Task from a "record".
     *
     * @param entry the entry entered by the user to record a Task.
     * @return the character representing the type of Task.
     */
    public static char getTaskTypeChar(String entry) {
        return entry.charAt(1);
    }

    /**
     * Retrieves whether the "record" of a Task is marked or not.
     *
     * @param entry the entry entered by the user to record a Task.
     * @return true if the Task is recorded as done, false otherwise.
     */
    public static boolean isMarked(String entry) {
        return entry.charAt(4) == 'X';
    }

    /**
     * Obtain the appropriate Task instance from a user entry.
     *
     * @param <T> Task and its children.
     * @param entry the entry entered by the user to record a Task.
     * @param type the character representing the type of Task.
     * @return an appropriate instance of Task.
     */
    public static <T extends Task> Task parseEntry(String entry, char type) {
        T task = null;
        String dataStr = entry.substring(7);

        switch (type) {
        case 'T':
            task = (T) new Todo(String.format("%s%s", Parser.TASK_KEYWORD_TODO, dataStr));
            break;
        case 'D':
            String deadlineStr = dataStr.substring(0, dataStr.length() - 1);
            deadlineStr = deadlineStr.replace("(by:", "/by");
            String frontStr = deadlineStr.split(Deadline.DELIMITER)[0];
            String dateStr = deadlineStr.split(Deadline.DELIMITER)[1];
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy");
            LocalDate date = LocalDate.parse(dateStr, formatter);
            dateStr = date.toString();
            String fullStr = frontStr + Deadline.DELIMITER + dateStr;
            task = (T) new Deadline(String.format("%s%s", Parser.TASK_KEYWORD_DEADLINE, fullStr));
            break;
        case 'E':
            String eventStr = dataStr.substring(0, dataStr.length() - 1);
            eventStr = eventStr.replace("(at:", "/at");
            String frontStrE = eventStr.split(Event.DELIMITER)[0];
            String dateStrE = eventStr.split(Event.DELIMITER)[1];
            DateTimeFormatter formatterE = DateTimeFormatter.ofPattern("MMM dd yyyy");
            LocalDate dateE = LocalDate.parse(dateStrE, formatterE);
            dateStrE = dateE.toString();
            String fullStrE = frontStrE + Event.DELIMITER + dateStrE;
            task = (T) new Event(String.format("%s%s", Parser.TASK_KEYWORD_EVENT, fullStrE));
            break;
        default:
        }

        if (Task.isMarked(entry)) {
            task.markAsDone();
        }

        return task;
    }
}
