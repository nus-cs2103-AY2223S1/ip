public enum CommandWord {

    BYE("bye"),
    LIST("list"),
    MARK("mark"),
    UNMARK("unmark"),
    TODO("todo"),
    DEADLINE("deadline"),
    EVENT("event"),
    DELETE("delete");

    private final String command;

    CommandWord(String command) {
        this.command = command;
    }

    public static CommandWord get(String command) throws DukeException {
        for (CommandWord commandWord : CommandWord.values()) {
            if (commandWord.command.equals(command)) {
                return commandWord;
            }
        }
        throw new DukeException("Sorry, there is no such command!");
    }

}
