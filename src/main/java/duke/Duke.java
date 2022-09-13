package duke;

import duke.exceptions.DukeException;
import duke.handlers.*;
import duke.models.TaskList;
import duke.services.Parser;
import duke.storage.Storage;
import duke.utils.Commands;

public class Duke {
    private Storage storage;
    private final Parser parser;
    private TaskList taskList;

    public Duke() {
        parser = new Parser();
        storage = new Storage();
        taskList = new TaskList();
    }


    /**
     * Runs method to execute the Duke instance.
     */
    public String generateResponse(String input) {
        taskList = storage.load();

        String response;
        Commands command = parser.parseCommand(input);
        String[] wholeCommand = parser.parseFullCommand(input);

        try {
            switch (command) {
            case BYE:
                response = ByeHandler.getResponse();
                break;
            case TODO:
                response = ToDoHandler.getResponse(taskList, wholeCommand[1]);
                break;
            case DEADLINE:
                response = DeadlineHandler.getResponse(taskList, wholeCommand[1]);
                break;
            case EVENT:
                response = EventHandler.getResponse(taskList, wholeCommand[1]);
                break;
            case DELETE:
                response = DeleteHandler.getResponse(taskList, wholeCommand[1]);
                break;
            case LIST:
                response = ListHandler.getResponse(taskList);
                break;
            case MARK:
                response = MarkHandler.getResponse(taskList, wholeCommand[1]);
                break;
            case UNMARK:
                response = UnmarkHandler.getResponse(taskList, wholeCommand[1]);
                break;
            case FIND:
                response = FindHandler.getResponse(taskList, wholeCommand[1]);
                break;
            default:
                response = "Unknown command";
                break;
            }
                storage.save(taskList);
            } catch (DukeException e) {
                response = e.getMessage();
            }
            return response;
        }

    public String getResponse(String text) {
        return generateResponse(text);
    }
}
