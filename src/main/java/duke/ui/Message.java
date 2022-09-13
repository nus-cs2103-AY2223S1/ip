package duke.ui;

public enum Message {
    MARK("Very well, this task is done:"),
    UNMARK("Very well, this task is now undone:"),
    DELETE("Very well, task deleted:"),
    ADDED("Very well, added another task:"),
    SNOOZED("Very well, snoozed this task:"),
    INVALID("Your command is difficult to interpret."),
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
    NO_RESULTS_FOUND("No matches for your request."),
    DESCRIPTION_EMPTY("You will need to describe your task."),
    INVALID_SNOOZE_TYPE("You have not entered a valid snooze type.");

    private String value;

    Message(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }
}
