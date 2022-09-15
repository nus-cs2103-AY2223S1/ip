package duke.exception;

/**
 * Error message that informs user invalid/ not properly formatted commands.
 * It is passed to DukeException as Exception message.
 */
public enum ErrorMessage {
    INVALID_DATE_FORMAT ("OOPS!!! This is not a proper date format, please refer to command format information."),
    INVALID_DATE ("OOPS!!! This is not a proper date format, year, month or day value is invalid."),
    MISSING_DATE ("OOPS!!! The date information of a %s task cannot be empty."),
    MISSING_DESCRIPTION ("OOPS!!! The description of a %s task cannot be empty."),
    INVALID_DEADLINE_COMMAND ("OOPS!!! The command is not properly formatted."
            + " Please follow the format: deadline {description} /by {date}."),
    INVALID_EVENT_COMMAND ("OOPS!!! The command is not properly formatted."
            + " Please follow the format: event {description} /at {date} to {date}."),
    INVALID_TAG_COMMAND ("OOPS!!! The command is not properly formatted."
            + " Please follow the format: tag {task index} /with {tag}."),
    INVALID_COMMAND_TYPE ("OOPS!!! I'm sorry, but I don't know what that means :-("),
    INVALID_INDEX ("OOPS!!! The task index exceeds task list size limit."),
    MISSING_INDEX ("OOPS!!! The task index of a %s command can only be a positive integer and it cannot be empty."),
    INVALID_DATE_RANGE ("OOPS!!! The start date of a Event task cannot be after end date."),
    INVALID_TAG ("OOPS!!! The tag can only contain alphabets and it cannot be empty."),
    EXCEEDING_TAG_NUMBER ("OOPS!!! The tag cannot be added because a task cannot have more than 3 tags."),
    TAG_ALREADY_IN_TASK ("OOPS!!! The tag %s is already in the task tag list, same tag cannot be added again."),
    INVALID_KEYWORD ("OOPS!!! The keyword in list command can only contain alphabets."),
    TAG_NOT_IN_TASK("OOPS!!! The tag %s is not in the task tag list, so it cannot be deleted.");

    private String name;
    ErrorMessage(String name) {
        this.name = name;
    }

    /**
     * Gets name String of ErrorMessage.
     */
    public String getName(String taskType) {
        return String.format(this.name, taskType);
    }
}
