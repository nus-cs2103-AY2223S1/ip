package duke;

import duke.modules.Todos;

import static duke.Ui.readLine;
import static duke.Ui.say;

/**
 * Entrypoint for the Duke chatbot.
 */
public class Duke {
    /**
     * Entrypoint.
     *
     * @param args Command-line arguments.
     */
    public static void main(String[] args) {
        // intro string
        say("Hello, Duke here! What can I do for you?");

        // initialize plugins
        Todos todos = new Todos();

        boolean fExit = false;
        while (!fExit) {
            String line = readLine();
            fExit = Parser.execute(line, todos).shouldExitAfter();
        }
    }
}
