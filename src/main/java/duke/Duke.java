package duke;

import static duke.utils.Ui.logo;
import static duke.utils.Ui.wrapWithLines;

import java.io.File;
import java.util.Scanner;

import duke.commands.CommandHandler;
import duke.commands.CommandHandlerFactory;
import duke.data.Storage;
import duke.exceptions.DukeException;
import duke.tasks.TaskList;


/**
 * Duke is a program that helps its user to store tasks.
 */
public class Duke {

    private void initialise() {
        wrapWithLines("Hello from\n" + logo);

        File storageDirectory = new File("./data");
        if (!storageDirectory.exists()) {
            if (!storageDirectory.mkdir()) {
                wrapWithLines("Could not create /duke.data directory");
            }
        }

        Storage db = new Storage("./data/duke.txt");
        TaskList taskList = db.load();

        Scanner sc = new Scanner(System.in);
        CommandHandlerFactory commandHandlerFactory = new CommandHandlerFactory();
        String command;
        while (!(command = sc.nextLine()).equals("bye")) {
            try {
                CommandHandler commandHandler = commandHandlerFactory.getHandler(command);
                String message = commandHandler.handle(taskList);
                wrapWithLines(message);
            } catch (DukeException e) {
                wrapWithLines("☹ OOPS!!! " + e.getMessage());
            }
        }
        wrapWithLines("Bye. Hope to see you again soon!");
    }

    /**
     * Calls the initializer of Duke.
     * @param args any command line arguments given.
     */
    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.initialise();
    }
}
