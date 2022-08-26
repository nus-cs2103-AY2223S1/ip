package Duke;

import Duke.task.TaskList;
import Duke.task.TaskStorage;
import Duke.util.Ui;

import java.util.Scanner;

/**
 * Represents a chat-bot that can be used to manage
 * and store a list of tasks for users.
 */
public class Duke {
    private TaskStorage storage;
    private TaskList taskList;
    private Ui ui;
    public Duke(String filePath) {
        ui = new Ui();
        storage = new TaskStorage(filePath);
        taskList = storage.loadTask();
    }

    /**
     * Shows users that the bot is activated successfully
     * Reads input from the user and initialize the response
     * object to handle this input.
     */
    public void run() {
        try {
            ui.displayWelcome();
            DukeHandler handler = new DukeHandler(storage, taskList, ui);
            Scanner userInput = new Scanner(System.in);
            while (userInput.hasNextLine()) {
                String input = userInput.nextLine();
                handler.handleResponse(input);
            }
        }
        catch (DukeException e) {
            ui.showError(e.getMessage());
        }
    }
    public static void main(String[] args) {
        new Duke("data/Tasks.txt").run();
    }
}
