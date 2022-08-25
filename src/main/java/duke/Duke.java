package duke;

import java.io.IOException;
import java.util.ArrayList;

public class Duke {

    //    private String input;
//    private Scanner sc = new Scanner(System.in);
    private Ui ui;
    private Storage storage;
    private TaskList tasks;

    public Duke(String filePath) {
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.loadSaveData());
        } catch (DukeException | IOException e) {
            ui.dukeReply(e.getMessage());
            tasks = new TaskList();
        }
    }

    public static void main(String[] args) {
        Duke duke = new Duke("src/data/duke.txt");
        duke.start();
        Parser parser = new Parser(duke);
        parser.parse();
        duke.ui.exit();
    }

    private void start() {
        ui.greet();
    }

    public void printList() {
        ui.printList(tasks);
    }

    public void handleMark(int index) throws DukeException {
        tasks.mark(index);
        storage.save(tasks);
        ui.dukeReply("Nice! I've marked this task as done: \n  " + tasks.get(index));
    }

    public void handleUnmark(int index) throws DukeException {
        tasks.unmark(index);
        storage.save(tasks);
        ui.dukeReply("OK, I've marked this task as not done yet: \n  " + tasks.get(index));
    }

    public void handleToDo(String input) throws DukeException {
        Task todo = createToDo(input);
        tasks.addTask(todo);
        ui.echoTask(todo, tasks);
        storage.save(tasks);
    }

    private Task createToDo(String input) throws DukeException {
        try {
            String description = input.substring(5);
            return new ToDo(description);
        } catch (StringIndexOutOfBoundsException e) {
            throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
        }
    }

    public void handleDeadline(String input) throws DukeException {
        Task deadline = createDeadline(input);
        tasks.addTask(deadline);
        ui.echoTask(deadline, tasks);
        storage.save(tasks);
    }

    private Task createDeadline(String input) throws DukeException {
        int end = input.indexOf('/');
        String description = input.substring(9, end - 1);
        String by = input.substring(end + 4);
        return new Deadline(description, by);
    }

    public void handleEvent(String input) throws DukeException {
        Task event = createEvent(input);
        tasks.addTask(event);
        ui.echoTask(event, tasks);
        storage.save(tasks);
    }

    private Task createEvent(String input) throws DukeException {
        int end = input.indexOf('/');
        String description = input.substring(6, end - 1);
        String at = input.substring(end + 4);
        return new Event(description, at);
    }

    public void handleDelete(int index) throws DukeException {
        Task taskToDelete = tasks.delete(index);
        ui.echoDelete(taskToDelete, tasks);
        storage.save(tasks);
    }

    public void find(String input) {
        ArrayList<Task> newList = tasks.filterToArrayList(input);
        ui.printFind(newList);
        storage.save(tasks);
    }

    public void printException(DukeException e) {
        ui.dukeReply(e.getMessage());
    }
}



