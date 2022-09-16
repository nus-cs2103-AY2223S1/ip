package duke;

import duke.ui.Ui;

/**
 * Represents Duke - an interactive virtual assistant to organize tasks.
 */
public class Duke {

    private final Ui ui;


    /**
     * Constructs a Duke object and creates a new Ui object for user interaction
     */
    public Duke() {
        this.ui = new Ui(System.in, System.out);
    }

    /**
     * Introduces Duke and initiates interactive conversation with user
     */
    public String interact(String input) {
        return this.ui.readAndRespond(input);
    }


}