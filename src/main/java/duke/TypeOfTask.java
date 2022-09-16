package duke;

import duke.exceptions.InvalidTaskException;

/**
 * Represents type of task (event, deadline, todo).
 */
public enum TypeOfTask {
    event,
    deadline,
    todo;

    /**
     * Validate if the target string is a valid Task type
     * @param str
     * @return TypeOfTask
     * @throws InvalidTaskException where str does not follow specified task.
     */
    public static TypeOfTask validate(String str) throws InvalidTaskException {
        try {
            return TypeOfTask.valueOf(str);
        } catch (IllegalArgumentException e) {
            throw new InvalidTaskException(str);
        }
    }
}
