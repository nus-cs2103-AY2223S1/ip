package duke;

import duke.exceptions.DukeException;
import duke.data.LocalStorage;
import duke.handlers.*;
import duke.models.*;
import duke.services.Parser;
import duke.utils.Commands;

import java.util.Scanner;
import static duke.services.Ui.dukePrint;

public class Duke {

    private final LocalStorage storage;
    private final Parser parser;
    private TaskList list;

    /**
     * Constructor for Duke Instance.
     */
    public Duke() {
        parser = new Parser();
        storage = new LocalStorage();
        list = new TaskList();
    }

    /**
     * Runs the Duke Instance.
     */
    public void run() {
        list = storage.load();
        Scanner dukeSc = new Scanner(System.in);

        String greeting = "Hello! Imma duke.Duke!\n What can I do for you?";
        dukePrint(greeting);

        while (true) {
            String input = dukeSc.nextLine();
            Commands command = parser.parseCommand(input);
            String[] fullCommand = parser.parseFullCommand(input);

            try {
                switch (command) {
                case BYE:
                    ByeHandler.handle();
                    break;
                case LIST:
                    ListHandler.handle(list);
                    break;
                case TODO:
                    TodoHandler.handle(list, fullCommand[1]);
                    break;
                case DEADLINE:
                    DeadlineHandler.handle(list, fullCommand[1]);
                    break;
                case EVENT:
                    EventHandler.handle(list, fullCommand[1]);
                    break;
                case MARK:
                    MarkHandler.handle(list, fullCommand[1]);
                    break;
                case UNMARK:
                    UnmarkHandler.handle(list, fullCommand[1]);
                    break;
                case DELETE:
                    DeleteHandler.handle(list, fullCommand[1]);
                    break;
                default:
                    dukePrint("Unknown Command!");
                    break;
                }
                storage.write(list);
            } catch (DukeException dukeException) {
                dukePrint(dukeException.getMessage());
            }
        }
    }

    /**
     * Our main method. Starts a Duke instance and runs the program.
     * @param args The command line arguments.
     **/
    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.run();
    }
}
