package duke;

import java.io.IOException;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

/**
 * The Ui class that interacts with the user.
 */
public class Ui {
    private static Scanner echo = new Scanner(System.in);
    private Parser parser;

    /**
     * Constructs the Ui class.
     *
     * @param parser The parser that interprets the user input.
     */
    public Ui(Parser parser) {
        this.parser = parser;
    }

    /**
     * Runs the program after receiving the user input.
     */
    public String run() {
        String response;
        while (!this.parser.isItDone()) {
            try {
                response = echo.nextLine();
                this.parser.reply(response);
            } catch (DukeException e) {
                System.out.println("-----------------------------------------------");
                System.out.println(e);
                System.out.println("-----------------------------------------------");
            } catch (IOException e) {
                throw new RuntimeException(e);
            } catch (DateTimeParseException e) {
                System.out.println("Oh no! Deadline Date and Time is specified wrongly, " + e);
            }
        }
        assert false : "This should not be returned";
        return "ui.run() should not return anything.";
    }
}
