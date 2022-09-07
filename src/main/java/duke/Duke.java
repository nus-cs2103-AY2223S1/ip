package duke;

import java.nio.file.Paths;

import duke.command.Command;
import duke.command.EndCommand;
import duke.component.Parser;
import duke.component.Storage;
import duke.component.TaskList;
import duke.exception.DukeException;


/**
 * Represents a personal task manager named Duke.
 */
public class Duke {

    public static final String MSG_START = "Hey there! I'm Duke.\nWhat do you want to do today?";

    private boolean hasEnded;
    private TaskList tasks;
    private Storage storage;
    private Parser parser;


    /**
     * Constructs a new Duke.
     */
    public Duke() {
        hasEnded = false;
        parser = new Parser();
        try {
            storage = new Storage(Paths.get(System.getProperty("user.dir"),
                    "data", "duke.txt"));
            tasks = storage.load();
        } catch (DukeException e) {
            System.out.println(e.getMessage());
            tasks = new TaskList();
        }
    }

    public String getResponse(String input) {
        String response;
        try {
            Command command = parser.parse(input, tasks);
            response = command.run();
            if (command instanceof EndCommand) {
                hasEnded = true;
            }
            storage.save(tasks);
        } catch (DukeException e) {
            response = e.getMessage();
        }
        return response;
    }

    public boolean getHasEnded() {
        return hasEnded;
    }

}
