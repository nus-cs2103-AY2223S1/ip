package roger;

import roger.commands.Command;
import roger.exceptions.RogerInvalidInputException;

import java.io.IOException;
import java.util.List;
import java.nio.file.Paths;


public class Roger {
    private TaskList tasks;
    private Ui ui;
    private Parser parser;
    private Storage storage;

    public Roger(String filepath) {
        this.ui = new Ui();
        this.parser = new Parser();
        this.tasks = new TaskList();
        this.storage = new Storage(Paths.get(filepath));

        try {
            List<String> taskStrings = this.storage.load();
            for (String taskString : taskStrings) {
                this.tasks.add(StorageParser.toTask(taskString));
            }
        } catch (IOException | IllegalArgumentException e) {
            this.ui.show("data/database.txt is corrupted. Starting over with a fresh database.");
        }
    }

    private void sayHello() {
        this.ui.showWelcome();
    }

    private void shutDown() {
        try {
            List<String> newTaskStrings = this.tasks.toTaskStrings();
            storage.write(newTaskStrings);
        } catch (IOException e) {
            this.ui.show("Unable to write tasks to database. Check the path provided.");
        }
    }


    public void run() {
        this.sayHello();

        boolean isExit = false;
        while (!isExit) {
            try {
                String input = this.ui.getUserInput();
                Command command = this.parser.parse(input);
                command.execute(this.tasks, this.ui, this.storage);
                isExit = command.isExit();
            } catch (RogerInvalidInputException e) {
                this.ui.showException(e);
            }
        }

        this.shutDown();
    }

    public static void main(String[] args) {
        /**
         * Logic for Roger program. Takes user input and matches it
         * with various commands (list, mark, unmark, todo, deadline, event, bye).
         * 'bye' shuts Roger down.
         */
        new Roger("data/database.txt").run();
    }
}
