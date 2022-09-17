enum CommandType {
    LOAD("load"),
    LIST("list"),
    MARK("mark"),
    UNMARK("mark"),
    TODO("todo"),
    DEADLINE("deadline"),
    EVENT("event"),
    BYE("bye");

    private String command;

    CommandType(String command) {
        this.command = command;
    }

    public String getCommand() {
        return this.command;
    }

}
