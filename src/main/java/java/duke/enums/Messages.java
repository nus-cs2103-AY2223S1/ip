package duke.enums;

/**
 * List of common messages to display to the user
 */
public enum Messages {
    LOGO("____        _        \n|  _ \\ _   _| | _____ \n| | | | | | | |/ / _ "
            + "\\\n| |_| | |_| |   <  __/\n|____/ \\__,_|_|\\_\\___|\n"),
    GREET("Hi! I am Duke, what can I do for you?"),
    EXIT("Goodbye! Hope to see you soon!"),
    FOUND("Here are the matching tasks in your list:"),
    LINE_SEPARATION("-*-*-*-*-*-*-*-*-*-*-*"),
    ADD_TASK("Added task: "),
    ADD_TODO("Added todo: "),
    ADD_EVENT("Added event: "),
    ADD_DEADLINE("Added deadline: "),
    MARK_DONE("Nice! I've marked this task as done:"),
    MARK_UNDONE(":( Stop Procrastinating! I've marked it as undone."),
    DELETE("Wow! Good job, I have removed the following task: "),
    ERROR_INVALID_COMMAND("Beep-Boop, I don't understand you :< \n Valid Commands: \n"
                            + "list (show all tasks) \n\n"
                            + "todo task description (add a todo to list)\n\n"
                            + "event task description /at yyyy-mm-dd hh:mm (add an event)\n\n"
                            + "deadline task description /at yyyy-mm-dd hh:mm (add a task)\n\n"
                            + "mark index (mark the task at that index)\n\n"
                            + "delete index (delete task at that index)\n\n"
                            + "find keyword (finds task containing that keyword)\n\n"),
    ERROR_TODO(">:| OOPS!!! The description of a todo cannot be empty."),
    ERROR_INVALID_INDEX(">:| OOPS!!! There is no task with this index."),
    ERROR_MISSING_PARAMETERS(">:| OOPS!!! There are missing parameters."),
    ERROR_INVALID_DATETIME(">:| OOPS!!!! The time format should be yyyy-MM-dd HH:mm");

    private final String message;

    Messages(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return this.message;
    }
}
