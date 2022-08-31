package duke;

import java.util.ArrayList;
import java.util.Scanner;

import duke.command.Command;
import duke.exception.DukeException;
import duke.task.TaskList;

/**
 * The Duke class that instantiates instances of duke.
 *
 * Duke is a ChatBot that performs different actions
 * based on commands provided by user.
 *
 * CS2103T IP
 * AY22/23 Semester 1
 * @author Tan Jia Rong
 */
public class Duke {
    // Location of save file
    private static final String SAVE_LOCATION = "./data/data.txt";

    // Initialise variables
    private Scanner sc = new Scanner(System.in);
    private TaskList tasks;
    private Storage storage;
    private Ui ui;

    /**
     * Constructor for ChatBot, Duke.
     *
     * @param filePath Location of save file.
     */
    public Duke(String filePath) {
        try {
            this.ui = new Ui();
            this.storage = new Storage(filePath);
            this.tasks = new TaskList(this.storage.load());
        } catch (DukeException e) {
            ui.printErr(e.getMessage());
            this.tasks = new TaskList(new ArrayList<>());
        }
    }

    /**
     * Executes the ChatBot Program.
     */
    public void run() {
        // Greets User
        System.out.println(ui.greetings());
        boolean isExit = false;

        String command = this.sc.next();
        String description = this.sc.nextLine();

        while (!isExit) {
            try {
                Command c = Parser.parse(command, description, tasks);
                System.out.println(c.execute(this.tasks, this.ui, this.storage));
                isExit = c.isExit();
            } catch (DukeException e) {
                System.out.println(ui.printErr(e.getMessage()));
            } finally {
                command = sc.next();
                description = sc.nextLine();
            }
        }
    }

    /**
     * Returns the Duke ChatBot.
     *
     * @param args arguments (if any).
     */
    public static void main(String[] args) {
        // Initialise variables
        Duke duke = new Duke(SAVE_LOCATION);
        duke.run();
    }
}
