import java.util.StringTokenizer;

public class QueryInterpreter {

    public QueryInterpreter() {
    }

    public String getType(String input) {
        StringTokenizer tokens = new StringTokenizer(input, " ");
        String type = tokens.nextToken();
        if (type.equals("todo") | type.equals("deadline") | type.equals("event")) {
            return "task";
        }
        return type;
    }

    public String getIndex(String input) {
        StringTokenizer tokens = new StringTokenizer(input, " ");
        tokens.nextToken();
        return tokens.nextToken();
    }
}
