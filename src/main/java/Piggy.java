public class Piggy {
    private Storage storage;
    private TaskList taskList;
    private Ui ui;

    public Piggy(String filePath) {
        storage = new Storage(filePath);
        taskList = new TaskList(storage.readTasks());
        ui = new Ui();
    }

    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            String command = ui.readCommand();
            isExit = Parser.parse(command, ui, taskList);
            storage.writeTasks(taskList);
        }
        ui.showBye();
    }
    
    public static void main(String[] args) {
        new Piggy("./tasks.txt").run();
    }
}
