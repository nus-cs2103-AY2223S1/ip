public enum Messages {
    LOGO("____        _        \n|  _ \\ _   _| | _____ \n| | | | | | | |/ / _ \\\n| |_| | |_| |   <  __/\n|____/ \\__,_|_|\\_\\___|\n"),
    GREET("Hi! I am Duke, what can I do for you?"),
    EXIT("Goodbye! Hope to see you soon!"),
    LINE_SEPARATION("-*-*-*-*-*-*-*-*-*-*-*"),
    ADD_TASK("Added task: ");

    public String message;

    Messages(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return this.message;
    }
}
