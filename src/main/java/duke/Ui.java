package duke;

import java.util.Scanner;

/**
 * A UI which deals with user interactions.
 */
public class Ui {
    private final String GREETING = "Hello";
    private final String BYE = "Goodbye";
    private Parser parser;

    /**
     * Constructor method for a Ui.
     */
    public Ui() {
        this.parser = new Parser();
    }

    /**
     * Prints the greeting.
     */
    public void doGreeting() {
        System.out.println(this.GREETING);
    }

    /**
     * Prints the exit message.
     */
    public void doBye() {
        System.out.println(this.BYE);
    }

    /**
     * Interprets the input of the user.
     *
     * @param tasks list of tasks of the user
     */
    public void giveInput(TaskList tasks) {
        String input;
        Scanner sc = new Scanner(System.in);
        while (!this.parser.isBye()) {
            input = sc.nextLine();
            try {
                this.parser.parse(input, tasks);
            } catch (DukeException e) {
                System.out.println(e);
            }
        }
    }
}
