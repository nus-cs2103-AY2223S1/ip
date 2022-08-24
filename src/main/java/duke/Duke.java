package duke;
import java.text.ParseException;
import java.util.Scanner;
import java.io.IOException;


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

        while(true) {
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
