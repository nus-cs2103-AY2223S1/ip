package duke;

import duke.commands.Command;
import duke.parser.DukeParser;

public class Duke {
    final Storage storage = new Storage();
    final TaskList taskList;
    final DukeParser parser = new DukeParser();

    Duke() {
        taskList = new TaskList(storage);
    }

    /**
     * Process user {@code input} and returns Duke's response.
     *
     * @param input user command
     * @return Duke's response
     */
    public String getResponse(String input) {
        try {
            Command command = parser.parseCommand(input);
            String response = command.execute(taskList);
            storage.syncTasksToFile(taskList);
            return response;
        } catch (DukeException e) {
            return (e.getMessage());
        }
    }

    public static void main(String[] args) {
        Launcher.main(args);
    }
}
