package duke.enums;

import duke.exceptions.InvalidCommandException;

/**
 * A helper enumeration that specifies various keywords and their corresponding actions that are
 * available to the user.
 */
public enum Action {
    Todo("todo"),
    Deadline("deadline"),
    Event("event"),
    Mark("mark"),
    Unmark("unmark"),
    Delete("delete"),
    Find("find"),
    Tag("tag");

    public final String label;

    Action(String label) {
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
