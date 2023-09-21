package anthea.task;

import java.util.Optional;

import anthea.Anthea;

/**
 * Constructs tasks from strings
 */
public class TaskFactory {
    private static boolean isTrue(String str) {
        return str.equals("true");
    }

    /**
     * Constructs the task.
     *
     * @param taskData Data for the task.
     * @return Task according to taskData.
     * @throws IllegalArgumentException If taskData does not conform to the format.
     */
    public static Task constructTask(String[] taskData) throws IllegalArgumentException {
        if (taskData == null || taskData.length < 1) {
            throw new IllegalArgumentException("taskData cannot be null or of length 1");
        }
        boolean isTask = taskData[0].equals("Task") && taskData.length >= 3;
        if (isTask) {
            return new Task(taskData[1], isTrue(taskData[2]));
        }
        boolean isToDo = taskData[0].equals("ToDo") && taskData.length >= 3;
        if (isToDo) {
            return new ToDo(taskData[1], isTrue(taskData[2]));
        }
        boolean isDeadline = taskData[0].equals("Deadline") && taskData.length >= 4;
        if (isDeadline) {
            return new Deadline(taskData[1], taskData[3], isTrue(taskData[2]));
        }
        boolean isEvent = taskData[0].equals("Event") && taskData.length >= 4;
        if (isEvent) {
            return new Event(taskData[1], taskData[3], isTrue(taskData[2]));
        }
        throw new IllegalArgumentException("Unsupported task type or incorrect task data length");
    }

    /**
     * Constructs the task.
     *
     * @param taskData Data for the task.
     * @return Optional of Task according to taskData, Optional.empty() if cannot construct.
     */
    public static Optional<Task> constructOptionalTask(String[] taskData) {
        assert taskData != null;
        try {
            return Optional.of(constructTask(taskData));
        } catch (IllegalArgumentException ex) {
            Anthea.getUi().printStyledMessage(
                    "(>.<') did not understand this task - dropping it",
                    String.join(", ", taskData));
            return Optional.empty();
        }
    }
}
