package duke.ui;

public enum Message {
    MARK("Very well, this task is done:"),
    UNMARK("Very well, this task is now undone:"),
    DELETE("Very well, task deleted:"),
    ADDED("Very well, you have added another task:"),
    SNOOZED("Very well, you have snoozed this task:"),
    INVALID("I struggle to interpret your command."),
    GREETING("Greetings, I'm Lurch.\nYou rang?"),
    EMPTY("There is nothing in your agenda."),
    BYE("Have a lovely day with dark and cloudy skies."),
    ERROR("Oh bother!"),
    OUT_OF_BOUNDS_ERROR("The number you have given me is too high or too low"),
    TASK_INDEX_ERROR("That is not the correct way to write a number"),
    NO_AT_ERROR("Use \"/at\" to tell me when the event is, please"),
    NO_BY_ERROR("Use \"/by\" to tell me when the event is, please"),
    NO_FOR_ERROR("Use \"/for\" to tell me how long to snooze, please"),
    DATE_FORMAT_ERROR("That is not how you write a date"),
    NO_RESULTS_FOUND("I found nothing matching your request."),
    DESCRIPTION_EMPTY("You will need to describe your task."),
    INVALID_SNOOZE_TYPE("You have not entered a valid snooze type."),
    EMPTY_COMMAND_ERROR("You have not entered anything. Very funny.");

    private String value;

    Message(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }
}
