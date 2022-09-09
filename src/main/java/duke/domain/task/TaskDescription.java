package duke.domain.task;

public class TaskDescription {
    public static final String MESSAGE_CONSTRAINTS = "Names should only contain alphanumeric characters and spaces, and it should not be blank";

    public static final String VALIDATION_REGEX = "[\\p{Alnum}][\\p{Alnum} ]*";

    public final String taskDescription;

    public TaskDescription(String description) {
        taskDescription = description;
    }

    public static boolean isValid(String test) {
        return test.matches(VALIDATION_REGEX);
    }
}
