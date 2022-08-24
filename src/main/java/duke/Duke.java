package duke;

import duke.command.Command;
import duke.command.EndCommand;
import duke.component.Parser;
import duke.component.Storage;
import duke.component.TaskList;
import duke.component.Ui;
import duke.exception.DukeException;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;


public class Duke {
    private static Path PATH = Paths.get(System.getProperty("user.dir" ), "data", "duke.txt");


    private boolean hasEnded;
    private TaskList tasks;
    private Storage storage;
    private Ui ui;
    private Parser parser;


    public Duke() {
        this.hasEnded = false;
        this.ui = new Ui();
        this.parser = new Parser();
        try {
            this.storage = new Storage(PATH);
            this.tasks = this.storage.load();
        } catch (DukeException e) {
            ui.printMessage(e.getMessage());
            this.tasks = new TaskList();
        }
    }

    public void start() {
        Scanner sc = new Scanner(System.in);
        this.ui.welcome();
        while(!hasEnded) {
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

    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.start();
    }
}
