package duke;

import java.util.Scanner;

/**
 * The Duke program.
 */
public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Constructs a Duke object
     *
     * @param filePath to a txt file that stores existing tasks
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = storage.readFromFile();
        } catch (DukeException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    /**
     * Runs the duke program and parses user's input.
     */
    public void run() {
        Scanner scn = new Scanner(System.in);
        int numTasks = this.tasks.getLength();

        //Can change to greet
        ui.say("Hello! I'm Pawl\nWhat can I do for you?");

        String input = scn.nextLine();
        //Potential exception: No input
        while (!Parser.checkExit(input)) {
            Parser.parse(input, tasks, storage, ui);

            input = scn.nextLine();
        }

        ui.say("Bye. Hope to see you again soon!");
    }

    public static void main(String[] args) {
        new Duke("data.txt").run();
    }
}
