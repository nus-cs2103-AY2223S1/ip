import commands.Command;
import commands.CommandHandlerFactory;
import commands.CommandParser;
import commands.CommandHandler;
import exceptions.DukeException;
import tasks.TaskList;

import java.util.Scanner;

import static utils.DukeUtils.wrapWithLines;

public class Duke {
    private static final String logo = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";

    private final TaskList taskList = new TaskList();

    private void handle(String input) throws DukeException {
        Command command;

        try {
            CommandParser cp = new CommandParser();
            command = cp.getCommand(input);
        } catch (IllegalStateException e) {
            throw new DukeException("This command does not exist!");
        }
        String message = "";
        wrapWithLines(message);
    }

    private void initialise() {
        wrapWithLines("Hello from\n" + Duke.logo);

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