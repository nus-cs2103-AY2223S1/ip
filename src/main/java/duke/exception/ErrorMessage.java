package duke.exception;

public enum ErrorMessage {
    INVALID_DATE_FORMAT ("☹ OOPS!!! This is not a proper date format, please refer to command format information."),
    INVALID_DATE ("☹ OOPS!!! This is not a proper date format, year, month or day value is invalid."),
    MISSING_DESCRIPTION ("☹ OOPS!!! The description of a %s task cannot be empty."),
    INVALID_DEADLINE_COMMAND ("☹ OOPS!!! The command not properly formatted.\n"
            + "Please follow the format: Deadline {description} /by {date}."),
    INVALID_EVENT_COMMAND ("☹ OOPS!!! The command not properly formatted.\n"
            + "Please follow the format: Event {description} /at {date} to {date}."),
    INVALID_COMMAND_TYPE ("☹ OOPS!!! I'm sorry, but I don't know what that means :-("),
    INVALID_INDEX ("☹ OOPS!!! The task index exceeds task list size limit."),
    MISSING_INDEX ("☹ OOPS!!! The task index of a %s command cannot be empty."),
    INVALID_DATE_RANGE ("☹ OOPS!!! The start date of a Event task cannot be after end date.");

    private String name;
    ErrorMessage(String name) {
        this.name = name;
    }

    public String getName(String taskType) {
        return String.format(this.name, taskType);
    }
}
