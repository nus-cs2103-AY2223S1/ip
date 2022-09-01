import amanda.command.*;
import amanda.ui.*;
import amanda.manager.*;
import amanda.exception.*;
import java.io.IOException;
import java.util.Scanner;

public class Amanda {

    private StoreManager store;
    private TaskManager tasks;
    private Ui ui;

    public Amanda (String filePath) {
        QueryInterpreter interpreter = new QueryInterpreter();
        store = new StoreManager(filePath);
        ui = new Ui();
        try {
            tasks = new TaskManager();
            store.load(tasks);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        Scanner sc = new Scanner(System.in);
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand(sc);
                ui.showLine(); // show the divider line ("_______")
                Command c = QueryInterpreter.interpret(fullCommand);
                c.execute(tasks, ui, store);
                isExit = c.isExit();
            } catch (AmandaException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
        sc.close();
    }

    public static void main(String[] args) {
        Amanda amanda = new Amanda("data/Amanda.txt");
        amanda.run();
    }
}
