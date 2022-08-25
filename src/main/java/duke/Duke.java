import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine(); // show the divider line ("_______")
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }

    public void list() throws DukeException {
        try {
            if (tasks.size() == 0) {
                throw new DukeException("☹ OOPS!!! You have no tasks in the list.\n");
            }
            String content;
            content = INDENT + "Here are the tasks in your list:\n";
            for (int i = 0; i < tasks.size(); i++) {
                Task task = tasks.get(i);
                content += INDENT + (i + 1) + "." + task + "\n";
            }
            msg(content);
        } catch (DukeException e) {
            msg(INDENT + e.getMessage());
        }
    }

    public void bye() throws IOException {
        storeTasks();
        msg(INDENT + "Bye. Hope to see you again soon!\n");
    }

    public static void main(String[] args) throws IOException {
        new Duke(System.getProperty("user.dir") + "/data/tasks.txt").run();
    }
}
