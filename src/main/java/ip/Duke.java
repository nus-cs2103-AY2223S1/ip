package ip;

import ip.command.ByeCommand;
import ip.command.Command;
import ip.exception.DukeException;
import ip.exception.InvalidCommand;

import java.io.IOException;

/**
 * <h1>Task management program</h1>
 * Record to-do's, deadlines, and events.
 * Mark them as done or not.
 * Delete them when no longer needed!
 * 
 * @author Jonathan Lam
 */
public class Duke {
    private static Ui ui = new Ui();
    private static Parser parser = new Parser();
    private static TaskList taskList = new TaskList();
    private static Storage storage = new Storage("src/main/java/ip/taskData.txt");
    private static boolean toWipe = false;

    public static void main(String[] args) {
        // Set Duke to wipe stored task data on termination.
        if (System.getProperty("user.dir").endsWith("text-ui-test")) {
            toWipe = true;
        }
        // Load task list from default storage location.
        try {
            taskList = storage.load();
        // Try to find task list from fallback location.
        } catch (IOException e) {
            ui.say("Error in loading task data file. Searching for file in fallback location.");
            try {
                taskList = storage.load("taskList.txt");
            } catch (IOException ee) {
                ui.say(ee.toString());
            }
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