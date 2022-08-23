package duke.task;

import duke.core.DukeException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.FormatStyle;

/**
 * A class that represents a task in the task list.
 */
public abstract class Task {
    protected String text = "";
    protected LocalDate details;
    protected boolean complete = false;

    private static final String serializerSeparator = "---";

    public enum TaskType { ToDo, Deadline, Event }

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

    public final String serialize() {
        return getTaskType() +
                serializerSeparator +
                complete +
                serializerSeparator +
                text +
                serializerSeparator +
                details;
    }

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
                    task.details = LocalDate.parse(args[3]d);
                } catch (DateTimeParseException e) {
                    return null;
                }
            }
        }

        return task;
    }

    public abstract TaskType getTaskType();

    @Override
    public String toString() {
        return "[" + (complete ? "X" : " ") + "] " + text;
    }

    protected String getFormattedDetails() {
        return details.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL));
    }
}