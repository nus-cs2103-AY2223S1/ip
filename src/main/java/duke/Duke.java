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

    /**
     * Constructor for Duke.
     *
     * @param filePath Name of file where tasks are saved.
     */
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

    /**
     * Generates a response for the given user input.
     *
     * @param input User input.
     * @return Response.
     */
    public String getResponse(String input) {
        String[] arr = new Parser().parseCommand(input);
        String typeOfTask = arr[0];
        String response;
        Task t;

        // Check for duplicate task in current list.
        if (this.storage.isDuplicateTask(typeOfTask, arr)) {
            return this.ui.getDuplicateTaskError();
        }

        switch (typeOfTask) {
        case "T":
            try {
                t = this.addAndGetTodo(arr[1]);
            } catch (DukeException e) {
                return e.toString();
            }
            response = this.ui.getTaskAddedReply(t, this.tasks);
            break;
        case "D":
            try {
                t = this.addAndGetDeadline(arr[1], arr[2]);
            } catch (DukeException e) {
                return e.toString();
            }
            response = this.ui.getTaskAddedReply(t, this.tasks);
            break;
        case "E":
            try {
                t = this.addAndGetEvent(arr[1], arr[2]);
            } catch (DukeException e) {
                return e.toString();
            }
            response = this.ui.getTaskAddedReply(t, this.tasks);
            break;
        case "d":
            int taskNum = Integer.parseInt(arr[1]);
            Task toBeDeleted = this.deleteAndGetDeletedTask(taskNum);
            response = this.ui.getTaskDeletedReply(toBeDeleted, this.tasks);
            break;
        case "M":
            t = this.markAndGetMarkedTask(arr[1]);
            response = this.ui.getTaskMarkedReply(t);
            break;
        case "L":
            response = this.ui.getList(this.tasks);
            break;
        case "F":
            response = this.ui.getTasksWithKeyword(arr[1], this.tasks);
            break;
        case "B":
            response = this.ui.getGoodbyeMessage();
            break;
        default:
            assert typeOfTask.equals("U") : typeOfTask;
            response = this.ui.getUnknownInputError();
        }

        return response;
    }

    private Task addAndGetTodo(String description) throws DukeException {
        if (Objects.equals(description, "")) {
            throw new DukeException("OOPS!!! The description of a todo cannot be empty.");
        }
        Task t = new Todo(description);
        this.tasks.add(t);
        this.storage.save(this.tasks);
        return t;
    }

    private Task addAndGetDeadline(String description, String by) throws DukeException {
        if (Objects.equals(description, "")) {
            throw new DukeException("OOPS!!! The description of a deadline cannot be empty.");
        }
        Task t = new Deadline(description, by);
        this.tasks.add(t);
        this.storage.save(this.tasks);
        return t;
    }

    private Task addAndGetEvent(String description, String at) throws DukeException {
        if (Objects.equals(description, "")) {
            throw new DukeException("OOPS!!! The description of an event cannot be empty.");
        }
        Task t = new Event(description, at);
        this.tasks.add(t);
        this.storage.save(this.tasks);
        return t;
    }

    private Task deleteAndGetDeletedTask(int taskNum) {
        Task t = this.tasks.getTask(taskNum);
        this.tasks.delete(taskNum);
        this.storage.save(this.tasks);
        return t;
    }

    private Task markAndGetMarkedTask(String input) {
        Task t = this.tasks.getTask(Integer.parseInt(input));
        this.tasks.getTask(Integer.parseInt(input)).markAsDone();
        this.storage.save(this.tasks);
        return t;
    }
}
