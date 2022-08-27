package duke;

public class Parser {
    public enum CommandType {
        BYE,
        MARK,
        UNMARK,
        DELETE,
        LIST,
        TODO,
        DEADLINE,
        EVENT
    }

    public static Command parse(String fullCommand) throws DukeException {
        String[] split = fullCommand.split(" ", 2);
        CommandType command;
        try {
            command = CommandType.valueOf(split[0].toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
        String description = split.length == 2 ? split[1] : "";
        switch (command) {
        case BYE:
            return new ExitCommand();
        case LIST:
            return new ListCommand();
        case MARK:
            return new DoneCommand(Integer.parseInt(description));
        case UNMARK:
            return new UndoneCommand(Integer.parseInt(description));
        case DELETE:
            return new DeleteCommand(Integer.parseInt(description));
        case TODO:
            return new AddCommand(new Todo(description));
        case DEADLINE:
            String[] Deadline = description.split(" /by ", 2);
            return new AddCommand(new Deadline(Deadline[0], Deadline[1]));
        case EVENT:
            String[] Event = description.split(" /at ", 2);
            return new AddCommand(new Event(Event[0], Event[1]));
        /* case FIND:
            return new FindCommand(description);

         */
        default:
            throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }
}
