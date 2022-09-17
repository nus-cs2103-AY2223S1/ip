package duke;

import java.io.IOException;

/**
 * Parses the response taken by user from Ui.
 */
public class Parser {
    private boolean isDone;
    private Storage storage;
    private TaskList tasks;

    enum TaskUpdater {
        MARK,
        UNMARK,
        DELETE
    }

    enum Type {
        TODO,
        DEADLINE,
        EVENT
    }

    /**
     * Constructor of a Parser class.
     *
     * @param storage The text document used for storing and loading data.
     * @param tasks The arraylist used to store tasks.
     */
    public Parser(Storage storage, TaskList tasks) {
        this.storage = storage;
        this.tasks = tasks;
        this.isDone = false;
    }

    /**
     * Checks if Duke is done running
     *
     * @return True when "bye" is inputted, otherwise false
     */
    public boolean isItDone() {
        return this.isDone;
    }

    /**
     * Exit the application.
     *
     * @return String saying bye to the user.
     * @throws IOException If file does not exist.
     */
    public String exit() throws IOException {
        String textToFile = "";
        for (int i = 0; i < this.tasks.size(); i++) {
            textToFile = textToFile + this.storage.fileFormatString(tasks.getTask(i)) + System.lineSeparator();
        }
        this.storage.writeToFile(textToFile);
        this.tasks.clear();
        isDone = true;
        return "Bye. Hope to see you again soon!";
    }

    /**
     * Loads tasks from text document into arraylist.
     *
     * @throws DukeException If incorrect information is used to add into the arraylist.
     * @throws IOException If the relative path to the file in invalid.
     */
    public void load() throws DukeException, IOException {
        this.storage.load();
        this.storage.addDukeToList(this.tasks);
    }

    /**
     * runs command after receiving response from the user.
     *
     * @param response The string that consists of the user command.
     * @return Duke's response.
     * @throws DukeException If the response is invalid.
     * @throws IOException If the relative path to the text document is invalid.
     */
    public String reply(String response) throws DukeException, IOException {
        String[] stringComponents = response.split(" ", 2);
        if (response.equals("bye")) {
            return exit();
        } else if (response.equals("list")) {
            return this.tasks.viewList();
        } else if (stringComponents.length == 1) {
            throw new DukeException("Invalid command. Please check again.");
        } else {
            String action = stringComponents[0];
            String description = stringComponents[1];
            return editTask(action, description);
        }
    }

    /**
     * Edits the task.
     *
     * @param action the action of the task.
     * @param description The description of the task.
     * @return The String of the action applied on the task.
     * @throws DukeException The action ot description is invalid.
     */
    public String editTask(String action, String description) throws DukeException {;
        switch (action) {
        case "mark":
            return this.tasks.updateTask(TaskUpdater.MARK, description);
        case "unmark":
            return this.tasks.updateTask(TaskUpdater.UNMARK, description);
        case "deadline":
            return this.tasks.addTaskType(Type.DEADLINE, description);
        case "todo":
            return this.tasks.addTaskType(Type.TODO, description);
        case "event":
            return this.tasks.addTaskType(Type.EVENT, description);
        case "delete":
            return this.tasks.updateTask(TaskUpdater.DELETE, description);
        case "find":
            return this.tasks.findTask(description);
        case "tag":
            return this.tasks.tagTask(description);
        default:
            throw new DukeException("Invalid command. PLease check again.");
        }
    }
}
