package duke;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

import java.time.LocalDate;
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


    /**
     * Constructs the Duke.
     *
     * @param filePath filepath containing input from previous session.
     */
    public Duke(String filePath) {
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        try {
            this.tasks = new TaskList(storage.load());
            tasks.list();
        } catch (FileNotFoundException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    /**
     * Runs the Duke program.
     */
    public void run() {
        ui.showWelcome();
        boolean isExit = false;

        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine(); // show the divider line ("_______")
                if (fullCommand.equals("bye")) {
                    ArrayList<String> data = tasks.bye();
                    storage.save(data);
                    ui.showBye();
                    break;
                } else {
                    boolean exit = Parser.parse(fullCommand, tasks, ui, storage);
                    isExit = exit;
                }
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }

    public static void main(String[] args) {
        new Duke(System.getProperty("user.dir") + "/data/duke.txt").run();
    }

}

