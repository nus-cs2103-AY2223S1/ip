package duke;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Duke is an interactive personal chat robot to keep track of user inputted tasks.
 *
 * @author Liu Han
 */
public class Duke {
    private final static String PATH = "data/duke.txt";

    private final Storage storage;
    private TaskList tasks;
    private final Ui ui;

    /**
     * Duke Constructor
     * @param filePath the file path where user would like to store the .txt file consisting of list of tasks
     */
    public Duke(String filePath) {
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        try {
            this.tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError();
            this.tasks = new TaskList();
        }
    }

    public static void main(String[] args) {
        new Duke(PATH).run();
    }

    private void run() {

        ui.showWelcome();
        new Parser(this.tasks).parser();
        storage.save(this.tasks);
        ui.farewell();
    }
}
