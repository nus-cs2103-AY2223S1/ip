public enum Command {

    LIST("list"),
    TODO("todo"),
    EVENT("event"),
    DEADLINE("deadline"),
    MARK("mark"),
    UNMARK("unmark"),
    DELETE("delete"),
    BYE("bye"),
    INVALID("");


    private final String command;
    Command(String command) {
        this.command = command;
    }

    public static Command find(String command) {
        for (Command request: Command.values()) {
            if(command.equals(request.command)) {
                return request;
            }
        }
        return INVALID;
    }
}
