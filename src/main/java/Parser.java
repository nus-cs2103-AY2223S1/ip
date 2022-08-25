import static java.lang.System.exit;

/**
 * Interprets user inputted String as Commands.
 *
 * @author WR3nd3
 */
public class Parser {

    public enum CommandList {
        BYE, LIST, MARK, UNMARK, TODO, EVENT, DEADLINE, DELETE
    }

    public static Command parse(String input) {
        String[] inputArray = input.split(" ", 2);
        String cmd = inputArray[0];
        String content = null;
        int position;

        if (inputArray.length == 2) {
            content = inputArray[1];
        }

        try {
            Parser.CommandList c = Parser.CommandList.valueOf(cmd.toUpperCase());
            switch (c) {
            case TODO:
                if (content == null) {
                    throw new DukeException("todo: " + content);
                }
                return new AddCommand("T", content);
            case EVENT:
                if (content == null || content.split(" /at ").length != 2) {
                    throw new DukeException("Event: " + content);
                }
                String[] inputE = content.split(" /at ");
                return new AddCommand("E", inputE[0], inputE[1]);
            case DEADLINE:
                if (content == null || content.split(" /by ").length != 2) {
                    throw new DukeException("DL: " + content);
                }
                String[] inputD = content.split(" /by ");
                return new AddCommand("D", inputD[0], inputD[1]);
            case MARK:
                if (content == null) {
                    throw new DukeException("mark: " + content);
                }
                position = Integer.parseInt(content);
                return new MarkCommand(position);
            case UNMARK:
                if (content == null) {
                    throw new DukeException("unmark: " + content);
                }
                position = Integer.parseInt(content);
                return new UnmarkCommand(position);
            case DELETE:
                if (content == null) {
                    throw new DukeException("delete: " + content);
                }
                position = Integer.parseInt(content);
                return new DeleteCommand(position);
            case LIST:
                return new ListCommand();
            case BYE:
                return new ExitCommand();
            default:
                throw new DukeException("defaulted " + c);
            }
        } catch (IllegalArgumentException e) {
            throw new DukeException(cmd.toUpperCase());
        }
    }
}
