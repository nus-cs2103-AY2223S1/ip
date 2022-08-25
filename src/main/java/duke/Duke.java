package duke;
import java.io.IOException;
import java.util.ArrayList;

public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Duke(String filePath) {
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        try {
            this.tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        } catch (IOException e) {
            System.out.println("OOPS! I have issue creating a new file!");
        }
    }

    public static void main(String[] args) {
        //Run bot
        Duke duke = new Duke("src/tasks/tasks.txt");
        Parser parser = new Parser(duke);
        duke.run(parser);
    }

    private void run(Parser parser) {
        ui.hello();
        parser.start();
        ui.bye();
    }

    /**
     * @param tDescription A String of the description for the task
     * @throws DukeException
     */
    public void addTodo(String tDescription) throws DukeException {
        if (tDescription.equals("")) {
            throw new DukeException("OOPS! The description of a todo cannot be empty.");
        } else {
            Task todo = new Todo(tDescription);
            tasks.addTask(todo);
            int size = tasks.getSize();
            ui.printTodo(todo, size);
            storage.save(tasks);
        }
    }

    /**
     * @param dDescription A String of the description for the task
     * @param dBy A String representing the deadline for the task
     */
    public void addDeadline(String dDescription, String dBy) {
        Task deadline = new Deadline(dDescription, dBy);
        tasks.addTask(deadline);
        int size = tasks.getSize();
        ui.printDeadline(deadline, size);
        storage.save(tasks);
    }

    /**
     * @param eDescription A String of the description for the task
     * @param eAt A String representing the day for the task
     */
    public void addEvent(String eDescription, String eAt) {
        Task event = new Event(eDescription, eAt);
        tasks.addTask(event);
        int size = tasks.getSize();
        ui.printEvent(event, size);
        storage.save(tasks);
    }

    public void printList() {
        ui.listTasks(tasks);
    }

    public void mark(int index) throws DukeException {
        tasks.markTask(index);
        Task taskToBeMarked = tasks.getTask(index);
        ui.printMarkedTask(taskToBeMarked);
        storage.save(tasks);
    }

    public void unmark(int index) throws DukeException {
        tasks.unmarkTask(index);
        Task taskToBeUnmarked = tasks.getTask(index);
        ui.printUnmarkedTask(taskToBeUnmarked);
        storage.save(tasks);
    }

    public void delete(int index) throws DukeException {
        Task taskToBeDeleted = tasks.deleteTask(index);
        int size = tasks.getSize();
        ui.printDelete(taskToBeDeleted, size);
        storage.save(tasks);
    }

    public void find(String string) {
        ArrayList<Task> lst = tasks.getTasks();
        ArrayList<Task> filtered = new ArrayList<>();
        for (Task task : lst) {
            String description = task.description;
            if (description.contains(string)) {
                filtered.add(task);
            }
        }
        ui.printFind(filtered);


    }

}

