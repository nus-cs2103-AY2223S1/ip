package ip;

import ip.command.ByeCommand;
import ip.command.Command;
import ip.exception.DukeException;
import ip.exception.InvalidCommand;

import java.io.IOException;

/**
 * <h1>Task management program<h2>
 * Record to-do's, deadlines, and events.
 * Mark them as done or not.
 * Delete them when no longer needed!
 * 
 * @author Jonathan Lam
 * @since 2022-08-25
 */
public class Duke {
    private static Ui ui = new Ui();
    private static Parser parser = new Parser();
    private static TaskList taskList = new TaskList();
    private static Storage storage = new Storage("src/main/java/ip/taskData.txt");
    private static boolean toWipe = false;

    public static void main(String[] args) {
        if (System.getProperty("user.dir").endsWith("text-ui-test")) {
            toWipe = true;
        }
        try {
            taskList = storage.load();
        } catch (IOException e) {
            ui.say("Error in loading task data file. Using default location.");
            ui.say("WARNING: Task data will be deleted after this run.");
            storage = new Storage("taskList.txt");
            toWipe = true;
        }
        ui.divider();
        while (true) {
            try {
                parser.load(ui.getNextLine());
                Command command = parser.getCommand();
                if (command instanceof ByeCommand) {
                    ui.sayBye();
                    break;
                } else {
                    ui.divider();
                    try {
                        command.execute(taskList);
                        storage.write(taskList);
                    } catch (DukeException e) {
                        System.out.println(e);
                    }
                    parser.clear();
                    ui.divider();
                }
            } catch (InvalidCommand e) {
                System.out.println("You have entered an invalid command.");
                ui.say(e.toString());
            }
        }
        if (toWipe) {
            storage.wipe();
            ui.say("Storage wiped.");
        }
    }

}