package duke;

import command.Command;
import exception.DukeException;
import exception.IncorrectInputException;
import exception.IncorrectInputFormatException;

import java.io.IOException;
import java.text.ParseException;
import java.time.format.DateTimeParseException;


/**
 * A class that encapsulates the Duke object.
 *
 * @author Wee Xin Yang, Markus
 * @version 0.1
 * @since 2022-8-24
 */
public class Duke {

    private final SavedTaskHandler storage;
    private final Ui ui;
    private final Parser parser;

    /**
     * Constructor for Duke Object
     */
    public Duke() throws IOException, ParseException {
        ui = new Ui();
        storage = new SavedTaskHandler();
        parser = new Parser(storage);
    }


    public String getResponse(String input) throws ParseException {
        try {
            Command c = parser.parse(input);
            return c.execute();
        } catch (DukeException e) {
            return "exception bro";
        } catch (IncorrectInputException e) {
            return e.toString();
        } catch (IncorrectInputFormatException e) {
            return e.toString();
        } catch (DateTimeParseException e) {
            return "Ensure your date is in the format YYYY-MM-DD (2019-12-25)";
        }
    }
}
