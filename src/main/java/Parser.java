import java.util.ArrayList;
import java.util.List;

public class Parser {

    private static final ArrayList<String> ADD_COMMANDS = new ArrayList<>(List.of("todo", "deadline", "event"));
    private static final ArrayList<String> DELETE_COMMANDS = new ArrayList<>(List.of("delete", "remove"));
    private static final ArrayList<String> EDIT_COMMANDS = new ArrayList<>(List.of("mark", "unmark"));
    private static final ArrayList<String> VIEW_COMMANDS = new ArrayList<>(List.of("list"));
    private static final ArrayList<String> EXIT_COMMANDS = new ArrayList<>(List.of("bye"));

    public Parser() {
        //
    }

    public static Command parse(String strToParse) throws DukeException {
        String[] splitTask = strToParse.split(" ", 2);
        String keyword = splitTask[0];
        if (!ADD_COMMANDS.contains(keyword) && !EDIT_COMMANDS.contains(keyword) && !VIEW_COMMANDS.contains(keyword)
                && !EXIT_COMMANDS.contains(keyword) && !DELETE_COMMANDS.contains(keyword)) {
            throw new DukeException("oops, I am unable to understand your command :(");
        } else {
            if (EXIT_COMMANDS.contains(keyword)) {
                return new ExitCommand();
            } else if (VIEW_COMMANDS.contains(keyword)) {
                return new ViewCommand(keyword);
            } else {
                try {
                    String input = splitTask[1];
                    if (ADD_COMMANDS.contains(keyword)) {
                        return new AddCommand(keyword, input);
                    } else if (EDIT_COMMANDS.contains(keyword)) {
                        return new EditCommand(keyword, input);
                    } else if (DELETE_COMMANDS.contains(keyword)) {
                        return new DeleteCommand(input);
                    }
                } catch (ArrayIndexOutOfBoundsException e) {
                    throw new DukeException("oops, your command seems to be incomplete :(");
                }
            }
        }
        return null;
    }
}
