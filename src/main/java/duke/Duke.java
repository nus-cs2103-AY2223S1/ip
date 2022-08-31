package duke;

import java.util.Scanner;

import duke.parser.CommandParser;
import duke.storage.Storage;
import duke.tasks.TaskList;

public class Duke {

    private static final Storage STORAGE = new Storage();
    private static final TaskList TASK_LIST = STORAGE.load();
    private static final CommandParser COMMAND_PARSER = new CommandParser(TASK_LIST);

    public static void main(String[] args) {

        System.out.println("Hello! I'm Duke\nWhat can I do for you?");
        Scanner sc = new Scanner(System.in);
        String command = sc.nextLine();

        while (!command.equalsIgnoreCase("bye")) {
            COMMAND_PARSER.handle(command);
            STORAGE.save();
            command = sc.nextLine();
        }

        System.out.println("Bye. Hope to see you again soon!");
    }
}
