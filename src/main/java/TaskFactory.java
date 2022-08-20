import java.util.Optional;

/**
 * Constructs tasks from strings
 */
public class TaskFactory {
    /**
     * Constructs the task.
     * @param taskData Data for the task.
     * @return Task according to taskData.
     * @throws IllegalArgumentException If taskData does not conform to the format.
     */
    public static Task constructTask(String[] taskData) throws IllegalArgumentException {
        if (taskData == null || taskData.length < 1) {
            throw new IllegalArgumentException("taskData cannot be null or of length 1");
        }
        if (taskData[0].equals("Task") && taskData.length >= 3) {
            return new Task(taskData[1], taskData[2].equals("true"));
        }
        if (taskData[0].equals("ToDo") && taskData.length >= 3) {
            return new ToDo(taskData[1], taskData[2].equals("true"));
        }
        if (taskData[0].equals("Deadline") && taskData.length >= 4) {
            return new Deadline(taskData[1], taskData[3], taskData[2].equals("true"));
        }
        if (taskData[0].equals("Event") && taskData.length >= 4) {
            return new Event(taskData[1], taskData[3], taskData[2].equals("true"));
        }
        throw new IllegalArgumentException("Unsupported task type or incorrect task data length");
    }

    /**
     * Constructs the task.
     * @param taskData Data for the task.
     * @return Optional of Task according to taskData, Optional.empty() if cannot construct.
     */
    public static Optional<Task> constructOptionalTask(String[] taskData) {
        try {
            return Optional.of(constructTask(taskData));
        } catch (IllegalArgumentException ex) {
            Duke.messagePrint("(>.<') did not understand this task - dropping it",
                    String.join(", ", taskData));
            return Optional.empty();
        }
    }
}
