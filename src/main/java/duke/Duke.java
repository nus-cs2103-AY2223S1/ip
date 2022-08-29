package duke;

import java.io.IOException;
import java.util.Scanner;

import duke.command.Command;
import duke.util.Parser;
import duke.util.Storage;
import duke.util.TaskList;
import duke.util.Ui;

/**
 * Class containing initialisation of Duke chatbot.
 */
public class Duke {

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     * @throws DukeException the duke exception
     */
    public static void main(String[] args) throws DukeException {
        Ui.greet();
        Scanner sc = new Scanner(System.in);
        Storage storage = new Storage();
        try {
            storage.storageRead();
        } catch (IOException e) {
            e.printStackTrace();
        }
        TaskList taskList = storage.getTaskList();
        while (true) {
            try {
                String input = sc.nextLine();
                Command command = Parser.parseCommand(input);
                command.run(taskList);
            } catch (DukeException e) {
                Ui.formatMessage(e.toString());
            }
        }
    }
}
