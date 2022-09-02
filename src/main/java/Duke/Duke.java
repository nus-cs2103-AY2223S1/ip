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
    private DukeHandler handler;
    public Duke(String filePath) {
        ui = new Ui();
        storage = new TaskStorage(filePath);
        taskList = storage.loadTask();
        handler = new DukeHandler(storage, taskList, ui);
    }

    /**
     * Shows users that the bot is activated successfully
     * Reads input from the user and initialize the response
     * object to handle this input.
     */
    public String run(String input) {
        ui.displayWelcome();
        return handler.handleResponse(input);
    }
    public static void main(String[] args) {
        new Duke("data/Tasks.txt");
    }
}
