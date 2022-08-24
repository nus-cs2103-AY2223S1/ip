package duke;

import duke.exceptions.DukeException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.tasklist.TaskList;
import ui.Ui;

import java.util.Scanner;

public class Duke {

    private TaskList tasks;
    private Ui ui;
    private Storage storage;

    public Duke() {
        this.ui = new Ui();
        this.storage = new Storage();
        this.tasks = new TaskList();

        storage.readSavedTasks();
    }

    public void run() {
        ui.bootUpDuke();
        loopInputRead();
    }

    private void loopInputRead() {
        Parser parser = new Parser(new Scanner(System.in));
        try {
            parser.handleInput();
        } catch (DukeException e) {
            e.getMessage();
        }

    }

    public static void main(String[] args) {
        Duke chatBotInstance = new Duke();
        chatBotInstance.run();
    }
}
