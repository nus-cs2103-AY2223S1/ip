package duke;

import java.io.FileNotFoundException;
import java.util.ArrayList;

/**
 * Personal Assistant Chatbot used primarily as a notebook.
 *
 * @author Aaron Pang
 * @version CS2103T AY22/23 Sem 1
 */
public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public String getList() {
        return tasks.list();
    }

    /**
     * Constructs the Duke.
     */
    public Duke() {
        this.ui = new Ui();
        this.storage = new Storage(System.getProperty("user.dir") + "/data/duke.txt");
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
                return ui.showBye();
            } else {
                return Parser.parse(input, tasks, ui, storage);
            }
        } catch (DukeException e) {
            return e.getMessage();
        }
    }
<<<<<<< HEAD

    public static void main(String[] args) {
        new Duke(System.getProperty("user.dir") + "/data/duke.txt").run();
    }
=======
>>>>>>> branch-Level-10
}

