import java.io.IOException;
import java.util.Scanner;

public class Duke {

    private final Storage fileStorage;
    private final Ui ui;
    private final TaskList tasks;

    public Duke(String filePath) throws IOException {
        ui = new Ui();
        fileStorage = new Storage(filePath);
        tasks = new TaskList(fileStorage.loadTasks());
    }

    public void run() throws IOException {
        ui.showWelcome();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            String command = scanner.nextLine();
            ui.showLine();
            Parser parser = new Parser(command, ui);
            if(parser.executeCommand(tasks)) {
                break;
            } else {
                ui.showLine();
                fileStorage.saveTasks(tasks);
            }
        }
    }

    public void main(String[] args) throws IOException {
        new Duke("tasks.txt").run();
    }
}
