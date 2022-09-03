package duke.ui;

public enum Message {
    MARK("Nice! I've marked this task as done:"),
    UNMARK("OK, I've marked this task as not done yet:"),
    DELETE("OK, I've deleted this task:"),
    ADDED("Got it! I've added this task:"),
    INVALID("I don't know what you mean."),
    GREETING("Hi, I'm Lurch.\nYou rang?"),
    EMPTY("You have no tasks in the list right now"),
    BYE("Have a lovely day with dark and cloudy skies."),
    ERROR("Oh bother!"),
    OUT_OF_BOUNDS_ERROR("The entered task index is out of bounds"),
    TASK_INDEX_ERROR("You entered an invalid task index"),
    NO_AT_ERROR("You need to use \"/at\" to specify when the event is"),
    NO_BY_ERROR("You need to use \"/by\" to specify when the event is"),
    NO_TIME_ERROR("You didn't specify the time."),
    DATE_FORMAT_ERROR("Invalid date/time format");

    private String value;

    Message(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }
}
