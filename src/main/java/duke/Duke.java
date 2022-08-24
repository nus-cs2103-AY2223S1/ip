package duke;
import java.io.IOException;
import java.text.ParseException;
import java.util.Scanner;

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

    public Duke() throws IOException, ParseException {
        ui = new Ui();
        storage = new SavedTaskHandler();
    }



    public void run() throws DukeException, IOException, ParseException {
        ui.greetingMessage();
        Scanner sc = new Scanner(System.in);

        while (true) {
            String input = sc.nextLine();
            Parser parser = new Parser(storage);
            parser.parse(input);
            if (input.equalsIgnoreCase("bye")) {
                break;
            }
        }
    }



    public static void main(String[] args) throws IOException, ParseException, DukeException {
        new Duke().run();
    }
}
