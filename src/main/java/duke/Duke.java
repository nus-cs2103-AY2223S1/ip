import java.io.IOException;
import java.text.ParseException;
import java.util.*;

public class Duke {
    private static final List<Task> ListofMessages  = new ArrayList<>();

    public static void main(String[] args) throws DukeException, IOException, ParseException {
        Parser.Parser(ListofMessages);

    }
}
