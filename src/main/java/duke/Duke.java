package duke;

import javafx.application.Application;

/**
 * Main object to be created to initialise the chatbot program.
 * 
 * @author Siau Wee
 */
public class Duke {

    /**
     * Main driver method of the program. To be called at the start
     * of the life cycle of the program.
     */
    public static void main(String[] args) {
        Application.launch(Ui.class, args);
    }
}
