package hazell;

import hazell.exceptions.HazellException;
import hazell.exceptions.UnknownCommand;

import java.io.IOException;
import java.util.Scanner;

/**
 * Main class of the chatbot.
 */
public class Hazell {
    private Storage storage;
    private TaskList taskList;
    private Ui ui;
    private static final String logo = "  _    _               _ _ \n"
            + " | |  | |             | | |\n"
            + " | |__| | __ _ _______| | |\n"
            + " |  __  |/ _` |_  / _ \\ | |\n"
            + " | |  | | (_| |/ /  __/ | |\n"
            + " |_|  |_|\\__,_/___\\___|_|_|\n";

    /**
     * Create a new instance of the chatbot.
     * @param filePath Path to store chatbot data for persistence
     */
    public Hazell(String filePath) {
        ui = new Ui();
        System.out.println(logo);
        try {
            storage = new Storage(filePath);
            taskList = new TaskList(storage.load());
        } catch (IOException e) {
            taskList = new TaskList();
            ui.reply("Looks like this is the first time you started me up. "
                    + "I'll be saving your tasks to data/hazell.txt!");
        }
        taskList.setStorage(storage);
    }

    /**
     * Start the chatbot.
     */
    public void run() {
        ui.reply("Hello, I am Hazell!\nWhat can I do for you?");
        while (ui.hasNextCommand()) {
            Command command = ui.getNextCommand();
            try {
                if (command.startsWith("bye")) {
                    ui.reply("Bye. Hope to see you again soon!");
                    System.exit(0);
                } else if (command.startsWith("list")) {
                    ui.reply(String.format("Here are the tasks in your list:\n%s", taskList));
                } else if (command.startsWith("mark")) {
                    int index = Integer.parseInt(command.getTrailingArgs().get(0)) - 1;
                    String response = taskList.markTaskAsDone(index);
                    ui.reply(response);
                } else if (command.startsWith("unmark")) {
                    int index = Integer.parseInt(command.getTrailingArgs().get(0)) - 1;
                    String response = taskList.markTaskAsUndone(index);
                    ui.reply(response);
                } else if (command.startsWith("todo")) {
                    String description = String.join(" ", command.getTrailingArgs());
                    String response = taskList.addTask(ToDo.create(description));
                    ui.reply(response);
                } else if (command.startsWith("deadline")) {
                    String description = String.join(" ", command.getTrailingArgs());
                    String time = command.getKwarg("by");
                    String response = taskList.addTask(Deadline.create(description, time));
                    ui.reply(response);
                } else if (command.startsWith("event")) {
                    String description = String.join(" ", command.getTrailingArgs());
                    String time = command.getKwarg("at");
                    String response = taskList.addTask(Event.create(description, time));
                    ui.reply(response);
                } else if (command.startsWith("delete")) {
                    int index = Integer.parseInt(command.getTrailingArgs().get(0)) - 1;
                    String response = taskList.deleteTask(index);
                    ui.reply(response);
                } else {
                    throw new UnknownCommand();
                }
            } catch (HazellException ex) {
                ui.reply(ex.toString());
            }
        }
    }

    public static void main(String[] args) {
        new Hazell("data/hazell.txt").run();
    }
}
