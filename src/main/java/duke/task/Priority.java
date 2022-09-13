package duke.task;

/**
 * Represents the priority of the task.
 */
public enum Priority {
    HIGH, MEDIUM, LOW;

    /**
     * Checks whether the user has inputted a priority in his command.
     *
     * @param str The inputted string.
     * @return The boolean value of whether the input matches any of the priority strings.
     */
    public static boolean checkPriority(String str) {
        switch (str) {
        case "high":
        case "medium":
        case "low":
            return true;
        default:
            return false;
        }
    }
}
