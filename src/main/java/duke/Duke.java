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

    /**
     * Gets the response by Duke from the command.
     *
     * @param input User command.
     * @return Response by Duke from the command.
     */
    public String getResponse(String input) {
        String[] userCommands = parser.parseCommand(input);
        StringBuilder response = new StringBuilder();
        for (String userCommand : userCommands) {
            if (userCommand.equals("")) {
                continue;
            }
            try {
                Command command = parser.parse(userCommand, tasks);
                response.append(command.run() + "\n\n");
                if (command instanceof EndCommand) {
                    hasEnded = true;
                }
                storage.save(tasks);
            } catch (DukeException e) {
                response.append(e.getMessage() + "\n\n");
            }
        }

        return response.toString();
    }

    /**
     * Gets the boolean on whether Duke has ended.
     *
     * @return Boolean on whether Duke has ended.
     */
    public boolean getHasEnded() {
        return hasEnded;
    }

}
