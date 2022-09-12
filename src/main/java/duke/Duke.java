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

        // Check for duplicate task in current list.
        if (this.storage.isDuplicateTask(typeOfTask, arr)) {
            return this.ui.getDuplicateTaskError();
        }

        switch (typeOfTask) {
        case "T":
            response = this.getTodoResponse(arr[1]);
            break;
        case "D":
            response = this.getDeadlineResponse(arr[1], arr[2]);
            break;
        case "E":
            response = this.getEventResponse(arr[1], arr[2]);
            break;
        case "d":
            response = this.getDeleteResponse(arr[1]);
            break;
        case "M":
            response = this.getMarkResponse(arr[1]);
            break;
        case "L":
            response = this.getListResponse();
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

    private String getTodoResponse(String description) {
        Task t;
        try {
            t = this.addAndGetTodo(description);
        } catch (DukeException e) {
            return e.toString();
        }
        return this.ui.getTaskAddedReply(t, this.tasks);
    }

    private String getDeadlineResponse(String description, String by) {
        Task t;
        try {
            t = this.addAndGetDeadline(description, by);
        } catch (DukeException e) {
            return e.toString();
        }
        return this.ui.getTaskAddedReply(t, this.tasks);
    }

    private String getEventResponse(String description, String at) {
        Task t;
        try {
            t = this.addAndGetEvent(description, at);
        } catch (DukeException e) {
            return e.toString();
        }
        return this.ui.getTaskAddedReply(t, this.tasks);
    }

    private String getDeleteResponse(String num) {
        Task toBeDeleted;
        try {
            toBeDeleted = this.deleteAndGetDeletedTask(num);
        } catch (DukeException e) {
            return e.toString();
        }
        return this.ui.getTaskDeletedReply(toBeDeleted, this.tasks);
    }

    private String getMarkResponse(String num) {
        Task t;
        try {
            t = this.markAndGetMarkedTask(num);
        } catch (DukeException e) {
            return e.toString();
        }
        return this.ui.getTaskMarkedReply(t);
    }

    private String getListResponse() {
        String response;
        try {
            response = this.ui.getList(this.tasks);
        } catch (DukeException e) {
            return e.toString();
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

    private Task deleteAndGetDeletedTask(String num) throws DukeException {
        if (num.equals("")) {
            throw new DukeException("OOPS!!! To delete a task, please enter number of task! :)");
        }
        int taskNum = Integer.parseInt(num);
        this.checkNumValidity(taskNum);
        Task t = this.tasks.getTask(taskNum);
        this.tasks.delete(taskNum);
        this.storage.save(this.tasks);
        return t;
    }

    private Task markAndGetMarkedTask(String num) throws DukeException {
        if (num.equals("")) {
            throw new DukeException("OOPS!!! To mark a task as done, please enter " +
                    "number of task! :)");
        }
        int taskNum = Integer.parseInt(num);
        this.checkNumValidity(taskNum);
        Task t = this.tasks.getTask(taskNum);
        this.tasks.getTask(taskNum).markAsDone();
        this.storage.save(this.tasks);
        return t;
    }

    private void checkNumValidity(int taskNum) throws DukeException {
        boolean isEmptyList = (this.tasks.size() == 0);
        if (isEmptyList) {
            throw new DukeException("OOPS!!! List is empty! Try adding a task first :)");
        }

        boolean validNum = (taskNum <= this.tasks.size()) && (taskNum >= 1);
        if (!validNum) {
            throw new DukeException("OOPS!!! Please enter a valid task number! :)");
        }
    }
}
