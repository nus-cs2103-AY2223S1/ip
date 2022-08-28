import java.util.StringTokenizer;

public class QueryInterpreter {

    public QueryInterpreter() {
    }

    public String getType(String input) throws AmandaException {
        if (input.equals("")) {
            throw new AmandaException("\nDon't call me up and say nothing!\n");
        }
        StringTokenizer tokens = new StringTokenizer(input, " ");
        String type = tokens.nextToken();
        if (type.equals("todo") | type.equals("deadline") | type.equals("event")) {
            return "task";
        } else if (type.equals("list") | type.equals("mark") | type.equals("unmark") | type.equals("delete")) {
            return type;
        } else {
            throw new AmandaException("\nI have no idea what you just said.\n");
        }
    }

    public String getIndex(String input) {
        StringTokenizer tokens = new StringTokenizer(input, " ");
        tokens.nextToken();
        return tokens.nextToken();
    }
}
