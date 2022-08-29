import java.util.Optional;

public enum Commands {
    bye("bye"),
    list("list"),
    delete("delete"),
    todo("todo"),
    deadline("deadline"),
    event("event"),
    mark("mark"),
    unmark("unmark");

    private String text;

    Commands(String command) {
        this.text = command;
    }

    public static Optional<Commands> parseCommand(String command) {
        for (Commands c : Commands.values()) {
            if (c.text.equals(command)) {
                return Optional.of(c);
            }
        }
        return Optional.empty();
    }
}
