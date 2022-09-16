package duke;

/**
 * Represents Duke, a Personal Assistant Chatterbot that helps a person to keep track of various things.
 */
public class Duke {
    private final Storage storage = new Storage("./duke.txt");
    private final TaskList tasks = storage.load();
    private final Ui ui = new Ui();
    private boolean isActive = true;

    /**
     * Start the Duke Chatterbot.
     */
    public void start() {
        ui.greet();
        while (isActive) {
            String input = ui.read();
            Command command = Parser.parseInput(input);
            command.run(this);
            storage.save(tasks);
        } // System.out.println(e.getMessage());
        ui.close();
    }

    /**
     * Add a Task to Duke.
     *
     * @param task Task to add.
     */
    public void addTask(Task task) { // error if description is empty
        tasks.add(task);
        ui.addTask(task);
        ui.infoCount(tasks.size());
    }

    /**
     * Print all current tasks to the UI.
     */
    public void printTasks() {
        ui.printTasks(tasks);
    }

    /**
     * Mark a Task as done or not done.
     *
     * @param index Index of Task to mark.
     * @param isDone Boolean to mark the Task as done or not done.
     */
    public void markTask(int index, boolean isDone) {
        if (index < 0 || index >= tasks.size()) {
            return; // throw new duke.DukeException("Index out of bound!");
        }
        Task task = tasks.get(index);
        if (isDone) {
            task.markAsDone();
            ui.markAsDone(task);
        } else {
            task.markNotDone();
            ui.markNotDone(task);
        }
    }

    /**
     * Delete a Task.
     *
     * @param index Index of Task to delete.
     */
    public void deleteTask(int index) {
        if (index < 0 || index >= tasks.size()) {
            return; // throw new duke.DukeException("Index out of bound!");
        }
        Task task = tasks.remove(index);
        ui.deleteTask(task);
        ui.infoCount(tasks.size());
    }

    /**
     * Print all tasks that matches the keyword to the UI.
     *
     * @param keyword Keyword to match.
     */
    public void findTasks(String keyword) {
        TaskList matches = (TaskList) tasks.clone();
        matches.removeIf(task -> !task.toString().toLowerCase().contains(keyword.toLowerCase()));
        ui.findTasks(matches);
    }

    /**
     * Exit the Duke Chatterbot.
     */
    public void exit() {
        isActive = false;
    }

    public static void main(String[] args) {
        new Duke().start();
    }
}
