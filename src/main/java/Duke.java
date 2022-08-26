import java.util.Scanner;

public class Duke {
    private Storage storage;
    private TaskList taskList;
    private Ui ui;

    public Duke(String fileName, String... directories) {
        this.ui = new Ui();
        this.storage = new Storage(ui, fileName, directories);
        this.taskList = new TaskList();
        ui.showWelcome();
        storage.load(taskList, ui);
    }

    // starts taking in the user's commands
    public void run() {
        Scanner scanner = new Scanner(System.in);
        boolean isExit = false;
        while (!isExit && scanner.hasNext()) {
            String commandString = scanner.nextLine();
            Command command = Parser.parse(commandString, taskList);
            command.execute(this.taskList, this.ui);
            isExit = command.getIsExit();
        }
    }

    public static void main(String[] args) {
        new Duke("data.txt", "data").run();
    }
}
