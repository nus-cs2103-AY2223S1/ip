import exceptions.DukeException;
import data.LocalStorage;
import handlers.*;
import models.*;
import services.Parser;
import utils.Commands;

import java.util.Arrays;
import java.util.Scanner;
import static services.Ui.dukePrint;

public class Duke {

    private final LocalStorage storage;
    private final Parser parser;
    private TaskList list;

    public Duke() {
        parser = new Parser();
        storage = new LocalStorage();
        list = new TaskList();
    }

    public void run() {
        list = storage.load();
        Scanner dukeSc = new Scanner(System.in);

        String greeting = "Hello! Imma Duke!\n What can I do for you?";
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

    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.run();
    }
}
