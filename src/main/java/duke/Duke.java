package duke;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import duke.command.Command;
import duke.command.UnknownCommand;
import duke.util.Parser;
import duke.util.Storage;
import duke.util.TaskList;
import duke.util.Ui;

/**
 * Class containing initialisation of Duke chatbot.
 */
public class Duke {
    private static Storage storage = new Storage("./././././data/duke.txt");
    private TaskList taskList;
    //private Scanner sc = new Scanner(System.in);
    private static Response response = new Response(new StringBuilder());

    public Duke() {
        Ui.greet();
        try {
            storage.storageRead();
            taskList = storage.getTaskList();
        } catch (IOException e) {
            taskList = new TaskList(new ArrayList<>());
        }
    }

    public String dukeExecute(String input) throws DukeException {
        while (true) {
            try {
                response.reset();
                Command command = Parser.parseCommand(input);
                command.run(taskList, response);
                String message = Ui.formatMessage(response.displayMessage());
                response.reset();
                return message;
            } catch (DukeException e) {
                return Ui.formatMessage(e.toString());
            }
        }
    }
}

