package amanda.manager;
import java.util.StringTokenizer;
import amanda.exception.*;
import amanda.command.*;

public class QueryInterpreter {

    public QueryInterpreter() {

    }
    public static Command interpret(String input) throws AmandaException {
        String type = null;
        TaskMaker generator = new TaskMaker();
        try {
            type = getType(input);
        } catch (AmandaException e) {
            e.printStackTrace();
        }
        if (type.equals("task")) {
            return new AddCommand(generator.make(input));
        } else if (type.equals("list")) {
            return new ListCommand();
        } else if (type.equals("mark")) {
            return new MarkCommand(Integer.parseInt(getIndex(input)));
        } else if (type.equals("unmark")) {
            return new UnmarkCommand(Integer.parseInt(getIndex(input)));
        } else if (type.equals("delete")) {
            return new DeleteCommand(Integer.parseInt(getIndex(input)));
        } else {
            return new ExitCommand();
        }
    }

    public static String getType(String input) throws AmandaException {
        if (input.equals("")) {
            throw new AmandaException("\nDon't call me up and say nothing!\n");
        }
        StringTokenizer tokens = new StringTokenizer(input, " ");
        String type = tokens.nextToken();
        if (type.equals("todo") | type.equals("deadline") | type.equals("event")) {
            return "task";
        } else if (type.equals("list") | type.equals("mark") | type.equals("unmark") | type.equals("delete") | type.equals("bye")) {
            return type;
        } else {
            throw new AmandaException("\nI have no idea what you just said.\n");
        }
    }

    public static String getIndex(String input) {
        StringTokenizer tokens = new StringTokenizer(input, " ");
        tokens.nextToken();
        return tokens.nextToken();
    }
}
