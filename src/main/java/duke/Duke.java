package duke;

import duke.command.ByeCommand;
import duke.command.Command;
import duke.task.TaskList;
import java.util.Scanner;

public class Duke {

    private static final String LOGO = " ____        _\n"
            + "|  _ \\ _   _| | _____\n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";
    private static final String GREETING = "Hello! I'm Duke!\n";

    private TaskList tasks;
    private boolean hasTasksEnd = false;

    /**
     * Initialises Duke class with empty {@code TaskList}.
     */
    public Duke() {
        tasks = new TaskList();
    }

    /**
     * Runs the program.
     */
    public void run() {

        tasks = Storage.load();
        Parser.printMsg(String.format("%s%sYou have %s. What can I do for you?", LOGO, GREETING, tasks.lengthString()));

        Scanner sc = new Scanner(System.in);

        while (!hasTasksEnd) {
            System.out.print(">> ");
            String input = sc.nextLine();
            try {
                Command command = Parser.parseCommand(input);
                command.execute(tasks);
                if (command instanceof ByeCommand) {
                    hasTasksEnd = true;
                }
                Storage.write(tasks);
            } catch (DukeException e) {
                Parser.printMsg(e.getMessage());
            }
        }
    }
    /**
     * Starts the program.
     */
    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.run();
    }
}