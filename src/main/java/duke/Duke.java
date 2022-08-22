package duke;

import java.util.Scanner;

public class Duke {
    /** The storage component of duke.Duke. */
    private Storage storage;
    /** The tasks component of duke.Duke. */
    private TaskList tasks;
    /** The parse component of duke.Duke. */
    private Parser parser;
    /** The ui component of duke.Duke. */
    private Ui ui;

    /**
     * The class constructor for duke.Duke. Initializes all necessary
     * objects for usage. Reads and loads previous tasks if available.
     * @param filePath
     */
    public Duke(String filePath) {
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        this.tasks = storage.loadFile(new TaskList());
        this.parser = new Parser(tasks);
    }

    /**
     * The core functionality of duke.Duke is started using this command. One-half
     * of the decision making tree of duke.Duke. Ends the process when encounters
     * "bye", and handles all exceptions.
     */
    public void run() {
        this.ui.start();
        Scanner myObj = new Scanner(System.in);
        String input = myObj.nextLine();

        while (!input.equals("bye")) {
            if (input.equals("list")) {
                ui.printList(this.tasks);
            } else {
                try {
                    this.parser.parse(input);
                    storage.writeToFile("./data", this.tasks);
                } catch (DukeException e) {
                    ui.showError(e);
                }
            }
            input = myObj.nextLine();
        }
        this.ui.close();
        myObj.close();
    }

    /**
     * Kick starts all interactions with the user.
     * @param args
     */
    public static void main(String[] args) {
        new Duke("data/tasks.txt").run();
    }
}
