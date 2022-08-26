package duke;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Represents the Duke chatbot.
 *
 */
public class Duke {


    protected static ArrayList<Task> arrayList = null;

    private Ui ui;
    private Parser parser;

    private Duke() {
        this.ui = new Ui();
        this.parser = new Parser();
    }

    /**
     * Runs the scanner and Duke chatbot.
     *
     */
    private void run() throws DukeException, IOException {

        this.ui.displayLogo();
        Scanner sc = new Scanner(System.in);

        String[] strArray = sc.nextLine().split(" ");

        while (!strArray[0].equals("bye")) {
            this.parser.parseCommand(strArray);

            strArray = sc.nextLine().split(" ");
        }

        System.out.println("Bye. Hope to see you again soon!");

    }

    /**
     * Runs the Duke chatbot in the main method
     *
     */
    public static void main(String[] args) throws DukeException, IOException {
        Duke duke = new Duke();
        duke.run();
    }
}
