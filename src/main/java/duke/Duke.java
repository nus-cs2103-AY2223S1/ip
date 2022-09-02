package duke;

import duke.command.ByeCommand;
import duke.command.Command;
import duke.command.CommandType;
import duke.task.DeadlineTask;
import duke.task.EventTask;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.TodoTask;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Duke {

    private static final String LOGO = " ____        _\n"
            + "|  _ \\ _   _| | _____\n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";
    private static final String GREETING = "Hello! I'm Duke!\n";

    private TaskList tasks;
    private boolean tasksEnd = false;

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

        while (!tasksEnd) {
            System.out.print(">> ");
            String input = sc.nextLine();
            try {
                Command command = Parser.parseCommand(input);
                command.execute(tasks);
                if (command instanceof ByeCommand) {
                    tasksEnd = true;
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