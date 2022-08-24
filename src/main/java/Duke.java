import java.io.IOException;
import java.util.Scanner;

public class Duke {
    private Ui ui;
    private TaskList tasks;
    private Storage storage;
    public static String filePath = "data/list.txt";

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage();
        tasks = new TaskList();
        storage.loadStorage(filePath, tasks);

    }

    public static void main(String[] args) {
        new Duke(Duke.filePath).run();

    }

    public void run() {
        ui.showWelcome();
        Parser parser = new Parser();


        boolean isExit = false;
        while (!isExit) {
            String fullInput = ui.readCommand();
            if (fullInput.equals("list")) {
                tasks.printList();
            } else {
                try {
                    Command command = parser.parse(fullInput);
                    command.execute(tasks, ui, storage);
                    ui.printBlankLine();
                    isExit = command.isExit();
                } catch (DukeException e) {
                    System.out.println(e);
                }
            }

        }
        storage.writeToTaskList(filePath, tasks);
        System.out.println("Bye. Hope to see you again soon!");
    }
}
