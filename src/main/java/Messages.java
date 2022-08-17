public enum Messages {
    LOGO("____        _        \n|  _ \\ _   _| | _____ \n| | | | | | | |/ / _ \\\n| |_| | |_| |   <  __/\n|____/ \\__,_|_|\\_\\___|\n"),
    GREET("Hi! I am Duke, what can I do for you?"),
    EXIT("Goodbye! Hope to see you soon!"),
    LINE_SEPARATION("-*-*-*-*-*-*-*-*-*-*-*"),
    ADD_TASK("Added task: "),
    ADD_TODO("Added todo: "),
    ADD_EVENT("Added event: "),
    ADD_DEADLINE("Added deadline: "),
    MARK_DONE("Nice! I've marked this task as done:"),
    MARK_UNDONE(":( Stop Procrastinating! I've marked it as undone."),
    ERROR("Beep-Boop, I don't understand you :<");

    public String message;

    Messages(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return this.message;
    }
}
