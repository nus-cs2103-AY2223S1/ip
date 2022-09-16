package duke;

public class Duke {
    private final Storage storage = new Storage("./duke.txt");
    private final TaskList tasks = storage.load();
    private final Ui ui = new Ui();
    private boolean isActive = true;

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

    public void addTask(Task task) { // error if description is empty
        tasks.add(task);
        ui.addTask(task);
        ui.infoCount(tasks.size());
    }

    public void printTasks() {
        ui.printTasks(tasks);
    }

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

    public void exit() {
        isActive = false;
    }

    public static void main(String[] args) {
        new Duke().start();
    }
}
