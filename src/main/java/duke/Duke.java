package duke;

import java.util.Scanner;

import duke.exceptions.DukeException;

/** Represents a Duke Object */
public class Duke {
    private Ui ui;
    private Parser parser;
    private boolean isEnded;

    /**
     * Generates a new Duke object
     */
    public Duke() {
        this.ui = new Ui();
        this.parser = new Parser(ui);
        this.isEnded = false;
    }

    private void run() {
        String command;
        Scanner sc = new Scanner(System.in);

        System.out.println(ui.greet());
        while (!this.isEnded) {
            command = sc.nextLine();
            try {
                String response = parser.handleInput(command);
                if (response.equals("Bye. Hope to see you again soon!\n")) {
                    isEnded = true;
                }
                System.out.println(response);
            } catch (DukeException e) {
                System.out.println(ui.printException(e));
            }
        }
    }

    /**
     * Returns a single Duke output corresponding to input
     * @param input
     * @return String output
     */
    public String getResponse(String input) {
        try {
            return parser.handleInput(input);
        } catch (DukeException e) {
            return ui.printException(e);
        }
    }

    /**
     * Runs GUIless Duke (invoked as main method)
     * @param args
     */
    public static void main(String[] args) {
        Duke instance = new Duke();
        instance.run();
    }
}
