import duke.MessagefulException;
import duke.modules.Todos;
import duke.modules.todos.Deadline;
import duke.modules.todos.Event;
import duke.modules.todos.Todo;

import java.util.Scanner;

import static duke.Ui.readLine;
import static duke.Ui.sayAsError;
import static duke.Ui.sayError;
import static duke.Ui.say;

/**
 * Entrypoint for the Duke chatbot.
 */
public class Duke {
    /**
     * Entrypoint.
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
            Scanner scanner = new Scanner(line);
            String command = scanner.hasNext() ? scanner.next() : "";

            try {
                switch (command) {
                case "":
                    sayAsError("Sorry, I didn't catch that?");
                    break;
                case "bye":
                    say("OK. See you next time! *boings away*");
                    fExit = true;
                    break;
                case "todo":
                    todos.cmdAdd(scanner, Todo::fromChat);
                    break;
                case "deadline":
                    todos.cmdAdd(scanner, Deadline::fromChat);
                    break;
                case "event":
                    todos.cmdAdd(scanner, Event::fromChat);
                    break;
                case "list":
                    todos.cmdList();
                    break;
                case "mark":
                    todos.cmdMark(scanner);
                    break;
                case "unmark":
                    todos.cmdUnmark(scanner);
                    break;
                case "delete":
                    todos.cmdDelete(scanner);
                    break;
                default:
                    sayAsError("Sorry, I didn't understand what you said :(");
                    break;
                }
            } catch (MessagefulException e){
                sayError(e);
            }
        }
    }
}
