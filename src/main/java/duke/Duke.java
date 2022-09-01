package duke;

import duke.command.Command;

import java.io.*;
import java.util.Objects;
import java.util.Scanner;

/**
 * Main class of Duke.
 * @author Lim Ai Lin
 */
public class Duke {

    private final Storage storage;
    private TaskList tasks;
    private final Ui ui;
    private final Parser parser;

    /**
     * Creates a Duke object which saves all tasks into the filePath.
     * @param filePath The text file to save the tasks.
     */
    public Duke(String filePath) {
        ui = new Ui();
        parser = new Parser();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (IOException e) {
            ui.showLoadingError();
        }
    }

    protected void run() throws DukeException {

        Scanner sc = new Scanner(System.in);
        String command = "";

        while (!Objects.equals(command, "bye")) {
            command = sc.nextLine();
            ui.showLine();
            Command c = parser.parse(command);
            c.execute(tasks, ui, storage);
            ui.newLine();
        }
    }

    public static void main(String[] args) throws DukeException {

        new Duke("tasks.txt").run();

    }
}
