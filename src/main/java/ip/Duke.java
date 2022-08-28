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
 */
public class Duke {
    /** Interacts with the user, take input and give output */
    private static final Ui ui = new Ui();
    /** Extract commands given to the ui */
    private static final Parser parser = new Parser();
    /** Encapsulation of tasks */
    private static TaskList taskList = new TaskList();
    /** File that task data is read from and written to */
    private static final Storage storage = new Storage("src/main/java/ip/taskData.txt");
    /** Flag to determine if the task data file should be wiped after program end */
    private static boolean toWipe = false;

    public static void main(String[] args) {
        if (System.getProperty("user.dir").endsWith("text-ui-test")) {
            toWipe = true;
        }
        try {
            // Try loading tasklist from default storage location.
            taskList = storage.load();
        } catch (IOException e) {
            ui.say("Error in loading task data file. Searching for file in default location.");
            try {
                taskList = storage.load("taskList.txt");
                ui.say("Task data file located in " + storage.getPath());
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