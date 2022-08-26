package duke.logic;

import java.util.Scanner;

/**
 * Ui is the user interface allowing communication between the user and Duke.
 *
 * @author totsukatomofumi
 */
public class Ui {
    /** Scanner object to parse System.in. */
    private Scanner scanner;

    /** Logo of Duke. */
    private static String logo = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";

    /** Prompt. */
    private static String prompt = "> ";

    /**
     * Constructs a Ui.
     */
    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Starts the user interface up.
     *
     * @param parser the parser to parse user input.
     */
    public void start(Parser parser) {
        System.out.println(Ui.logo);
        System.out.println("Hello! I'm Duke.\nWhat can I do for you?");
        System.out.print(prompt);   //prompt

        while (scanner.hasNextLine()) {
            parser.parse(scanner.nextLine()).run();
            System.out.print(prompt);
        }
    }
}
