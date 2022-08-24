package duke;

import duke.command.Command;
import duke.exception.DukeException;

import java.util.Scanner;

/**
 * The class that encapsulates the Duke bot.
 */
public class Duke {
    /** Stores all the data of tasks */
    private TaskList data;
    /** Deals with loading tasks and saving tasks */
    private Storage storage;
    /** Deals with interactions with the user */
    private Ui ui;
    /** Deals with making sense of the user command */
    private Parser parser;

    /**
     * Runs the duke program and handles the behaviour.
     */
    public void run() {
        data = new TaskList();
        ui = new Ui();
        parser = new Parser();
        try {
            storage = new Storage();
            storage.loadData(data);
        } catch (DukeException e) {
            ui.printError(e);
            return;
        }
        ui.helloMessage();
        Scanner sc = new Scanner(System.in);
        while (sc.hasNextLine()) {
            ui.printDivider();
            System.out.println("Duck:");
            String input = sc.nextLine();
            try {
                Command command = parser.parse(input);
                command.execute(data, ui, storage);
                if ("exit".equals(command.getCommand())) {
                    sc.close();
                    return;
                }
            } catch (DukeException e) {
                ui.printError(e);
            }
            ui.printDivider();
        }
    }

    /**
     * Instantiates a new Duke instance and run it.
     */
    public static void main(String[] args) {
        new Duke().run();
    }
}
