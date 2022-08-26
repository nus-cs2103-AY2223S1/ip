package duke;

import duke.commands.CommandHandlerFactory;
import duke.commands.CommandHandler;
import duke.data.Storage;
import duke.exceptions.DukeException;
import duke.tasks.TaskList;

import java.io.File;
import java.util.Scanner;

import static duke.utils.DukeUtils.wrapWithLines;

public class Duke {
    private static final String logo = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";

    private void initialise() {
        wrapWithLines("Hello from\n" + Duke.logo);

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

    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.initialise();
    }
}