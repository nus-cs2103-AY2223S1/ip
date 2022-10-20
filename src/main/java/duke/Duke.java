package duke;

import java.io.FileNotFoundException;
import java.util.ArrayList;

/* Inspiration taken from Bag Devesh Kumar and Zizheng ip to solve some prevalent issues
 */
public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Duke() {
        this.ui = new Ui();
        this.storage = new Storage(System.getProperty("user.dir") + "/database/duke.txt");
        try {
            tasks = new TaskList(storage.load());
            tasks.list();
        } catch (FileNotFoundException e) {
            tasks = new TaskList();
        }
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    public String getResponse(String input) {
        try {
            if (input.equals("bye")) {
                ArrayList<String> data = tasks.bye();
                storage.save(data);
                return ui.printBye();
            } else {
                return Parser.parse(input, tasks, ui, storage);
            }
        } catch (DukeException e) {
            return e.getMessage();
        }
    }
}
