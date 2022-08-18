import java.util.Arrays;

public enum Action {
    Todo("todo"),
    Deadline("deadline"),
    Event("event"),
    Mark("mark"),
    Unmark("unmark"),
    Delete("delete");

    public final String label;
    private Action(String label) {
        this.label = label;
    }

    public static Action parseCommand(String command) throws InvalidCommandException {
        for (Action action : values()) {
            if (command.startsWith(action.label)) {
                return action;
            }
        }

        throw new InvalidCommandException(
               "Could not determine the command. Valid commands include: todo, deadline, event, mark, unmark, delete");
    }
}
