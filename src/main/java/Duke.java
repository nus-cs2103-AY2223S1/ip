import java.util.Scanner;

public class Duke {
    private Storage storage;
    private TaskList taskList;
    private Ui ui;

    public Duke(String path, String fileName) {
        this.ui = new Ui();
        this.storage = new Storage(ui, path, fileName);
        this.taskList = new TaskList();
        ui.showWelcome();
        storage.load(taskList, ui);
    }

    // starts taking in the user's commands
    public void run() {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            String commandString = scanner.nextLine();
            Command command = Parser.parse(commandString, taskList);
            command.execute();
        }
    }

    public static void main(String[] args) {
        new Duke("data", "data.txt").run();
    }
}
