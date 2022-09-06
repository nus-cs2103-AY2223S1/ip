package duke;

import duke.command.Command;

import java.io.IOException;
import java.util.Objects;
import java.util.Scanner;

/**
 * Main class of Duke.
 *
 * @author Lim Ai Lin
 */
public class Duke {

    private final Storage STORAGE;
    private TaskList tasks;
    private final Ui UI;
    private final Parser PARSER;

    /**
     * Creates a Duke object which saves all tasks into the filePath.
     *
     * @param filePath The text file to save the tasks.
     */
    public Duke(String filePath) {
        UI = new Ui();
        PARSER = new Parser();
        STORAGE = new Storage(filePath);
        try {
            tasks = new TaskList(STORAGE.load());
        } catch (IOException e) {
            UI.showLoadingError();
        }
    }

    protected void run() throws DukeException {

        Scanner sc = new Scanner(System.in);
        String command = "";

        while (!Objects.equals(command, "bye")) {
            command = sc.nextLine();
            UI.showLine();
            Command c = PARSER.parse(command);
            c.execute(tasks, UI, STORAGE);
            UI.newLine();
        }
    }

    /**
     * Main class of Duke
     */
    public static void main(String[] args) throws DukeException {

        new Duke("tasks.txt").run();

    }
}
