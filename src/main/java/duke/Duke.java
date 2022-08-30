package duke;

import java.nio.file.Paths;
import java.util.Scanner;

import duke.command.Command;
import duke.command.EndCommand;
import duke.component.Parser;
import duke.component.Storage;
import duke.component.TaskList;
import duke.component.Ui;
import duke.exception.DukeException;


/**
 * Represents a personal task manager named Duke.
 */
public class Duke {

    private boolean hasEnded;
    private TaskList tasks;
    private Storage storage;
    private Ui ui;
    private Parser parser;


    /**
     * Constructs a new Duke.
     */
    public Duke() {
        this.hasEnded = false;
        this.ui = new Ui();
        this.parser = new Parser();
        try {
            this.storage = new Storage(Paths.get(System.getProperty("user.dir"),
                    "data", "duke.txt"));
            this.tasks = this.storage.load();
        } catch (DukeException e) {
            ui.printMessage(e.getMessage());
            this.tasks = new TaskList();
        }
    }

    /**
     * Starts the Duke task manager.
     */
    public void start() {
        Scanner sc = new Scanner(System.in);
        this.ui.welcome();
        while (!hasEnded) {
            try {
                Command command = parser.parse(sc.nextLine(), this.tasks);
                this.ui.printMessage(command.run());
                if (command instanceof EndCommand) {
                    this.hasEnded = true;
                }
                this.storage.save(this.tasks);
            } catch (DukeException e) {
                this.ui.printMessage(e.getMessage());
            }
        }
        sc.close();
    }

    /**
     * Starts the Duke task manager.
     * @param args No arguments are taken in.
     */
    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.start();
    }
}
