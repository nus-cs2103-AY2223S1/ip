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
        this.hasEnded = false;
        this.parser = new Parser();
        try {
            this.storage = new Storage(Paths.get(System.getProperty("user.dir"),
                    "data", "duke.txt"));
            this.tasks = this.storage.load();
        } catch (DukeException e) {
            System.out.println(e.getMessage());
            this.tasks = new TaskList();
        }
    }

    public String getResponse(String input) {
        String response;
        try {
            Command command = parser.parse(input, this.tasks);
            response = command.run();
            if (command instanceof EndCommand) {
                this.hasEnded = true;
            }
            this.storage.save(this.tasks);
        } catch (DukeException e) {
            response = e.getMessage();
        }
        return response;
    }

    public boolean getHasEnded() {
        return hasEnded;
    }

}
