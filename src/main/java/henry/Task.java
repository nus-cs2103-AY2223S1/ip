package henry;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import command.Commands;
import util.TaskUtils;

/**
 * The base for tasks that can be added to the
 * task list. All task types (Todo, Deadline, Event)
 * are just instances of Task with different types passed
 * into the constructor.
 */
public class Task {

    private final String description;
    private final Commands type;
    private final List<LocalDateTime> tentativeDates;
    private boolean isDone;
    // date is not final as it can be changed with the Tentative command
    private LocalDateTime date;

    public Task(Commands type, String description, LocalDateTime date) {
        this(type, description, date, false, new ArrayList<>());
    }

    /**
     * Constructor for a task. The task will have the specified
     * type, description, date, and status (if used).
     *
     * @param type        the type of task. Can be Todo, Deadline, or Event.
     * @param description the description of the task.
     * @param date        the date that the task must be completed by/is due.
     *                    Only used for Deadline and Event tasks.
     * @param isDone      whether the task is completed.
     */
    public Task(Commands type, String description, LocalDateTime date, boolean isDone,
                List<LocalDateTime> tentativeDates) {
        this.type = type;
        this.description = description;
        this.date = date;
        this.isDone = isDone;
        this.tentativeDates = tentativeDates;
    }

    /**
     * Sets the status of this task as the input boolean.
     *
     * @param status the desired status of the task
     */
    public void setComplete(boolean status) {
        this.isDone = status;
    }

    /**
     * Gets the description of the task.
     *
     * @return a String representing the description of the task
     */
    public String getDescription() {
        return description;
    }

    /**
     * Gets the LocalDateTime object related to the task.
     *
     * @return a LocalDateTime object representing the date of the task
     */
    public LocalDateTime getLocalDateTime() {
        return date;
    }

    /**
     * Gets the type of the task.
     *
     * @return a Commands with the enum value of the task type
     */
    public Commands getType() {
        return type;
    }

    /**
     * Returns the file-formatted version of the task. This is different from
     * the toString() method, in that it is simpler and thus easier to parse.
     *
     * @return a string representing the task as it would be written to the file
     */
    public String toFileEncodedString() {
        return TaskUtils.getFileEncodedTask(type, isDone ? 1 : 0, description, date, tentativeDates);
    }

    /**
     * Adds a tentative date to the Task. Only applicable for Event tasks.
     *
     * @param date the tentative date to be added
     */
    public void addTentativeDate(LocalDateTime date) {
        tentativeDates.add(date);
    }

    /**
     * Confirms the tentative date of the task. Only applicable for Event tasks.
     *
     * @param index the index of the date chosen
     */
    public void confirmDate(int index) {
        date = tentativeDates.get(index);
        tentativeDates.clear();
    }

    /**
     * Custom equals method to check if two tasks are equal.
     * Checks if the type, description, date and completion status are the same.
     *
     * @param o the object to be compared to
     * @return true if the two tasks are equal, false otherwise
     */
    @Override
    public boolean equals(Object o) {
        if (o instanceof Task) {
            Task other = (Task) o;
            return other.type == type
                   && other.description.equals(description)
                   && other.date.equals(date)
                   && other.isDone == isDone;
        }
        return false;
    }

    @Override
    public String toString() {
        return TaskUtils.toStandardString(type, isDone, description, date, tentativeDates);
    }
}
