import exceptions.HazellException;
import exceptions.UnknownCommand;

import java.io.IOException;
import java.util.Scanner;

public class Hazell {
    private static final String logo = "  _    _               _ _ \n"
            + " | |  | |             | | |\n"
            + " | |__| | __ _ _______| | |\n"
            + " |  __  |/ _` |_  / _ \\ | |\n"
            + " | |  | | (_| |/ /  __/ | |\n"
            + " |_|  |_|\\__,_/___\\___|_|_|\n";

    public static void reply(String msg) {
        String DIVIDER = "\t____________________________________________________________";
        System.out.println(DIVIDER);
        for (String line : msg.split("\n")) {
            System.out.println("\t" + line);
        }
        System.out.println(DIVIDER);
    }

    public static void main(String[] args) {
        System.out.println(logo);

        TaskList taskList;
        try {
            taskList = TaskList.createFromFile();
        } catch (IOException e) {
            taskList = new TaskList();
            reply("Looks like this is the first time you started me up. "
                    + "I'll be saving your tasks to data/hazell.txt!");
        }

        reply("Hello, I am Hazell!\nWhat can I do for you?");
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()) {
            String userinput = scanner.nextLine().strip();
            Command command = Command.parse(userinput);
            try {
                if (command.startsWith("bye")) {
                    reply("Bye. Hope to see you again soon!");
                    System.exit(0);
                } else if (command.startsWith("list")) {
                    reply(String.format("Here are the tasks in your list:\n%s", taskList));
                } else if (command.startsWith("mark")) {
                    int index = Integer.parseInt(command.getTrailingArgs().get(0)) - 1;
                    String response = taskList.markTaskAsDone(index);
                    reply(response);
                } else if (command.startsWith("unmark")) {
                    int index = Integer.parseInt(command.getTrailingArgs().get(0)) - 1;
                    String response = taskList.markTaskAsUndone(index);
                    reply(response);
                } else if (command.startsWith("todo")) {
                    String description = String.join(" ", command.getTrailingArgs());
                    String response = taskList.addTask(ToDo.create(description));
                    reply(response);
                } else if (command.startsWith("deadline")) {
                    String description = String.join(" ", command.getTrailingArgs());
                    String time = command.getKwarg("by");
                    String response = taskList.addTask(Deadline.create(description, time));
                    reply(response);
                } else if (command.startsWith("event")) {
                    String description = String.join(" ", command.getTrailingArgs());
                    String time = command.getKwarg("at");
                    String response = taskList.addTask(Event.create(description, time));
                    reply(response);
                } else if (command.startsWith("delete")) {
                    int index = Integer.parseInt(command.getTrailingArgs().get(0)) - 1;
                    String response = taskList.deleteTask(index);
                    reply(response);
                } else {
                    throw new UnknownCommand();
                }
            } catch (HazellException ex) {
                reply(ex.toString());
            }
        }
    }
}
