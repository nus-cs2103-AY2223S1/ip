package duke.taskmanager.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import duke.chatbot.commands.exceptions.EmptyTaskException;
import duke.chatbot.commands.exceptions.InvalidArgumentsException;
import duke.chatbot.commands.exceptions.InvalidDeadlineException;

/**
 * Deadline Task is a Task with the additional deadline information.
 */
public class DeadlineTask extends Task {
    public static final String TASK_DELIMITER = "/by ";
    private static final String TASK_TYPE = "D";
    private LocalDateTime deadline;

    /**
     * Creates a new deadline task with information indicating the name of the task and
     * the deadline of the task provided in the form of a date specified by the dateFormat.
     * Default completion status of the task is false.
     *
     * @param taskName string of the name of the task
     * @param deadline string of the deadline of the task in the format of dateFormat
     */
    public DeadlineTask(String taskName, LocalDateTime deadline) {
        super(TASK_TYPE, taskName);
        this.deadline = deadline;
        assert !(super.getTaskName().equals("")) : "Task should not be empty";
    }

    /**
     * Creates a new deadline task with information indicating the name of the task and
     * the deadline of the task provided.
     *
     * @param taskName string of the name of the task
     * @param deadline localDateTime of the deadline of the task
     * @param isCompleted boolean of the completion status of the task.
     */
    public DeadlineTask(String taskName, LocalDateTime deadline, boolean isCompleted) {
        super(TASK_TYPE, taskName, isCompleted);
        this.deadline = deadline;
        assert !(super.getTaskName().equals("")) : "Task should not be empty";
    }

    /**
     * Returns the deadline of the task.
     *
     * @return localDateTime of the deadline of the task
     */
    private LocalDateTime getDeadline() {
        return this.deadline;
    }

    /**
     * Formats the details of the task into a format that can be read and written by
     * the task manager.
     *
     * @return details of the task in a string format readable and writable by taskManager
     */
    @Override
    public String getFormattedString(String attributeSeparator) {
        return super.getFormattedString(attributeSeparator) + attributeSeparator + this.deadline;
    }

    /**
     * Updates the deadline task with the new task with updated task name and deadline.
     *
     * @param newTask the new task to update the current deadline task with
     */
    public void update(DeadlineTask newTask) {
        super.update(newTask);
        this.deadline = newTask.getDeadline();
    }

    /**
     * Returns the details of the task to be displayed by the chatbot.
     *
     * @return details of the task
     */
    @Override
    public String toString() {
        return super.toString() + " (by: "
                + this.deadline.getDayOfMonth() + " "
                + this.deadline.getMonth() + " "
                + this.deadline.getYear() + " | "
                + this.deadline.getHour() + ":" + String.format("%02d", this.deadline.getMinute()) + ")";
    }
}
