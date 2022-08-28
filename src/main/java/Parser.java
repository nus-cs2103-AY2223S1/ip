import java.util.Locale;

public class Parser {
    private enum Commands {
        BYE,
        LIST,
        MARK,
        UNMARK,
        DEADLINE,
        TODO,
        EVENT,
        DELETE
    }

    public Command parse(String command) throws DukeException {
        String[] str = command.split("\\s", 2);

        Commands myTask;

        try {
            myTask = Commands.valueOf(str[0].toUpperCase(Locale.ROOT));
        } catch (Exception e) {
            throw new DukeException("Oops! I don't know what that means.");
        }

        switch (myTask) {
            case BYE:
                return new ExitCommand();
            case LIST:
                return new ListCommand();
            case MARK:
                return new MarkCommand(str, true);
            case UNMARK:
                return new MarkCommand(str, false);
            case DEADLINE:
                return new AddCommand(str, 0);
            case TODO:
                return new AddCommand(str, 1);
            case EVENT:
                return new AddCommand(str, 2);
            case DELETE:
                return new DeleteCommand(str);
        }
        return new ExitCommand();
    }
}
