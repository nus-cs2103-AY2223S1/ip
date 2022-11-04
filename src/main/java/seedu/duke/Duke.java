package seedu.duke;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.Files;
import java.io.IOException;
import javafx.application.Platform;

public class Duke{

    private static Path DATA_FILE_PATH = Paths.get("data","duke.txt");


    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private Parser parser;


    /**
     * Initialise a new Duke instance with the specified filePath
     */
    public Duke() {
        this.ui = new Ui();
        this.storage = new Storage(DATA_FILE_PATH);
        this.tasks = new TaskList();
        this.parser = new Parser(this.tasks, this.ui, this.storage);
        this.storage.loadFromFile(tasks);
    }

    public String getResponse(String input) {
        try {
            String msg = this.parser.parse(input);
            if ("bye".equals(input)) {
                Platform.exit();
            }
            return msg;
        } catch (Parser.DukeException e) {
            return e.getMessage();
        }
    }
}
