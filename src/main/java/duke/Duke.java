package duke;

import duke.modules.Todos;

import java.util.ArrayList;
import java.util.List;

import static duke.Ui.printLines;
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
        printLines(say("Hello, Duke here! What can I do for you?"));

        // initialize plugins
        List<String> messages = new ArrayList<>();

        Todos todos = new Todos();
        messages.addAll(todos.init());

        printLines(messages);

        boolean fExit = false;
        while (!fExit) {
            String line = readLine();
            ExecuteResult result = Parser.execute(line, todos);
            printLines(result.getReply());
            fExit = result.shouldExitAfter();
        }
    }
}
