package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.FormatStyle;

import duke.core.DukeException;

/**
 * A class that represents a task in the task list.
 */
public abstract class Task {
    private static final String serializerSeparator = "---";
    protected String text = "";
    protected LocalDate details;
    protected boolean complete = false;

    /**
     * Deserializes the string version of a task into a Task object.
     * @param serializedTask String to deserialize.
     * @return Deserialized task.
     */
    public static Task deserialize(String serializedTask) {

        Task task = null;
        String[] args = serializedTask.split(serializerSeparator);

        TaskType type;

        try {
            type = TaskType.valueOf(args[0]);
        } catch (IllegalArgumentException e) {
            return null;
        }

        switch (type) {
        case ToDo:
            task = new ToDo();
            break;
        case Deadline:
            task = new Deadline();
            break;
        case Event:
            task = new Event();
            break;
        default:
            break;
        }

        if (task != null) {
            task.complete = Boolean.parseBoolean(args[1]);
            task.text = args[2];
            if (args.length >= 4) {
                try {
                    task.details = LocalDate.parse(args[3]);
                } catch (DateTimeParseException e) {
                    if (task.getTaskType() == TaskType.ToDo) {
                        return task;
                    }
                    System.out.println("fail 2");
                    return null;
                }
            }
        }

        return task;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setDetails(LocalDate details) {
        this.details = details;
    }

    public void setComplete(boolean complete) {
        this.complete = complete;
    }

    public DukeException invalidParameterError() {
        return new DukeException("Invalid parameters!");
    }

    /**
     * Serializes this task into a string format for saving.
     * @return String format of task.
     */
    public final String serialize() {
        return getTaskType()
                + serializerSeparator
                + complete
                + serializerSeparator
                + text
                + serializerSeparator
                + details;
    }

    public abstract TaskType getTaskType();

    @Override
    public String toString() {
        return "[" + (complete ? "X" : " ") + "] " + text;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null) {
            return false;
        } else if (o instanceof Task) {
            Task other = (Task) o;
            return this.getTaskType() == other.getTaskType()
                    && this.complete == other.complete
                    && this.text.equals(other.text)
                    && ((this.details == null && other.details == null)
                    || this.details.equals(other.details));
        } else {
            return false;
        }
    }

    protected String getFormattedDetails() {
        return details.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL));
    }

    /**
     * Enumeration for type of task.
     */
    public enum TaskType { ToDo, Deadline, Event }
}
