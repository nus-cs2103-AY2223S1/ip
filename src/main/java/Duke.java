import java.io.File;
import java.util.Scanner;
import static java.lang.System.exit;

/**
 * Represents an interactive 'ToDo list' with set commands to add, modify, and remove tasks.
 *
 * @author WR3nd3
 */
public class Duke {
    public enum CommandList {
        BYE, LIST, MARK, UNMARK, TODO, EVENT, DEADLINE, DELETE
    }


    private ListLoader storage;
    private TaskList tasks;
    private Ui ui;

    public Duke() {
        ui = new Ui();
        tasks = new TaskList();
        storage = new ListLoader(tasks);
        try {
            storage.load();
        } catch (DukeException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    public void run() {
        //int count = 0;
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                //System.out.println(count++);
                String fullCommand = ui.readCommand();
                //System.out.println(count++);
                System.out.println("cmd: " + fullCommand);
                ui.showLine();
                //System.out.println(count++);
                Command c = Parser.parse(fullCommand);
                //System.out.println("past parsing");
                c.execute(tasks, ui, storage);
                //System.out.println(count++);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.showError("HIHIHIHIHIHIHI");
                //ui.showError(e.getMessage());
                //System.exit(0);
            } finally {
                ui.showLine();
            }
        }
    }

    public static void main(String[] args) {
        new Duke().run();
    }


}


