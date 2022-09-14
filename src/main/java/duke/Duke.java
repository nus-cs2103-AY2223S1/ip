package duke;
import java.io.IOException;
import java.text.ParseException;


/**
 * A class that encapsulates the Duke object.
 *
 * @author  Wee Xin Yang, Markus
 * @version 0.1
 * @since   2022-8-24
 */
public class Duke {

    private SavedTaskHandler storage;
    private Ui ui;
    private Parser parser;

    public Duke() throws IOException, ParseException {
        ui = new Ui();
        storage = new SavedTaskHandler();
        parser = new Parser(storage);
    }


    public String getResponse(String input) throws ParseException {
        return parser.parse(input);
    }
}
