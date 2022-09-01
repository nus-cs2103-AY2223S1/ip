package duke;

import java.io.IOException;
import java.util.ArrayList;

import duke.command.Command;
import duke.util.Parser;
import duke.util.Storage;
import duke.util.TaskList;
import duke.util.Ui;

/**
 * Class containing initialisation of Duke chatbot.
 */
public class Duke {
    private static Storage storage = new Storage("./././././data/duke.txt");
    private TaskList tasks;
    private static Response response = new Response(new StringBuilder());

    public Duke() {
        try {
            storage.storageRead(response);
            tasks = storage.getTaskList();
        } catch (IOException e) {
            tasks = new TaskList(new ArrayList<>());
        }
    }

    public String dukeExecute(String input) throws DukeException {
        while (true) {
            try {
                response.reset();
                Command command = Parser.parseCommand(input);
                command.run(tasks, response);
                String message = Ui.formatMessage(response.displayMessage());
                response.reset();
                return message;
            } catch (DukeException e) {
                return Ui.formatMessage(e.toString());
            }
        }
    }

    public Response getResponse() {
        return this.response;
    }
}

