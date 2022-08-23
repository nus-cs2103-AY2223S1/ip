public class Duke {
    public static void main(String[] args) {
        Storage storage = new Storage("./tasks.txt");
        TaskList taskList = new TaskList(storage.readTasks());
        Ui ui = new Ui();

        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            String command = ui.readCommand();
            isExit = Parser.parse(command, ui, taskList);
            storage.writeTasks(taskList);
        }

        ui.showBye();
    }
}
