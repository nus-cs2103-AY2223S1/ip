package duke;

import java.util.Objects;

/**
 * An interactive chatbot which can keep track of a list of tasks.
 *
 * @author Lai Han Wen
 */
public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            storage.createFolder();
            storage.createFile();
            tasks = new TaskList();
        }
    }

    public void run() {
        this.ui.showWelcomeMessage();
        this.runCommandLoopUntilExit();
    }

    public void runCommandLoopUntilExit() {
        String command; // User input
        String[] arr;
        String action;

        while (true) {

            command = this.ui.promptUserCommand();
            arr = new Parser().parseCommand(command);
            action = arr[0];

            if (Objects.equals(action, "T")) {
                Task t = this.executeTodo(arr[1]);
                this.ui.showTaskAdded(t, this.tasks);
                continue;
            }

            if (Objects.equals(action, "D")) {
                Task t = this.executeDeadline(arr[1], arr[2]);
                this.ui.showTaskAdded(t, this.tasks);
                continue;
            }

            if (Objects.equals(action, "E")) {
                Task t = this.executeEvent(arr[1], arr[2]);
                this.ui.showTaskAdded(t, this.tasks);
                continue;
            }

            if (Objects.equals(action, "d")) {
                this.ui.showTaskDeleted(Integer.parseInt(arr[1]), this.tasks);
                this.executeDelete(arr[1]);
                continue;
            }

            if (Objects.equals(action, "M")) {
                Task t = this.executeMark(arr[1]);
                this.ui.showTaskMarked(t);
                continue;
            }

            if (Objects.equals(action, "L")) {
                this.ui.showList(this.tasks);
                continue;
            }

            if (Objects.equals(action, "U")) {
                this.ui.showUnknownInputError();
                continue;
            }

            if (Objects.equals(action, "B")) {
                this.ui.showGoodbyeMessage();
                break;
            }

        }
    }

    public Task executeTodo(String description) {
        Task t = new Todo(description);
        this.tasks.add(t);
        this.storage.save(this.tasks);
        return t;
    }

    public Task executeDeadline(String description, String by) {
        Task t = new Deadline(description, by);
        this.tasks.add(t);
        this.storage.save(this.tasks);
        return t;
    }

    public Task executeEvent(String description, String at) {
        Task t = new Event(description, at);
        this.tasks.add(t);
        this.storage.save(this.tasks);
        return t;
    }

    public void executeDelete(String taskNum) {
        int num = Integer.parseInt(taskNum);
        this.tasks.delete(num);
        this.storage.save(this.tasks);
    }

    public Task executeMark(String input) {
        Task t = this.tasks.getTask(Integer.parseInt(input));
        this.tasks.getTask(Integer.parseInt(input)).markAsDone();
        this.storage.save(this.tasks);
        return t;
    }

    public static void main(String[] args) {
        new Duke("data/tasks.txt").run();
    }
}
