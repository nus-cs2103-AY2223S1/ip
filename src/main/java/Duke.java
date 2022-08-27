import java.io.IOException;
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
        try {
            storage.load(taskList, ui);
        } catch (IOException e) {
            ui.showLoadingError();
            this.taskList = new TaskList();     // if error loading, use an empty task list
        }
    }

    // starts taking in the user's commands
    public void run() {
        Scanner scanner = new Scanner(System.in);
        boolean isExit = false;
        while (!isExit && scanner.hasNext()) {
            String commandString = scanner.nextLine();
            Command command;
            try {
                command = Parser.parse(commandString, taskList);
            } catch (IllegalArgumentException e) {
                ui.println(e.getMessage());
                continue;
            }
            command.execute(this.taskList, this.storage, this.ui);
            isExit = command.getIsExit();
        }
    }

    public static void main(String[] args) {
        new Duke("data.txt", "data").run();
    }
}
