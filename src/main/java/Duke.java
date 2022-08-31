import exceptions.DukeException;
import handlers.*;
import models.TaskList;
import services.Parser;
import utils.Commands;

import java.util.Scanner;

import static services.Ui.dukePrint;

public class Duke {
    private Storage storage;
    private final Parser parser;
    private TaskList taskList;

    public Duke() {
        parser = new Parser();
        storage = new Storage();
        taskList = new TaskList();
    }

    public void run() {
        taskList = storage.load();
        Scanner s = new Scanner(System.in);
        dukePrint("Hello! What can I do for you, my highness?");

        while (true) {
            String input = s.nextLine();
            Commands command = parser.parseCommand(input);
            String[] wholeCommand = parser.parseFullCommand(input);

            try {
                switch (command) {
                    case BYE:
                        ByeHandler.handle();
                        break;
                    case TODO:
                        ToDoHandler.handle(taskList, wholeCommand[1]);
                        break;
                    case DEADLINE:
                        DeadlineHandler.handle(taskList, wholeCommand[1]);
                        break;
                    case EVENT:
                        EventHandler.handle(taskList, wholeCommand[1]);
                        break;
                    case DELETE:
                        DeleteHandler.handle(taskList, wholeCommand[1]);
                        break;
                    case LIST:
                        ListHandler.handle(taskList);
                        break;
                    case MARK:
                        MarkHandler.handle(taskList, wholeCommand[1]);
                        break;
                    case UNMARK:
                        UnmarkHandler.handle(taskList, wholeCommand[1]);
                        break;
                    default:
                        dukePrint("Unknown command");
                        break;
                }
                storage.save(taskList);
            } catch (DukeException e) {
                dukePrint(e.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.run();
    }
}
