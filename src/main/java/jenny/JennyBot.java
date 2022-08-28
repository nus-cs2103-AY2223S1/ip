package jenny;

import jenny.commands.Command;
import jenny.exceptions.JennyException;
import jenny.storage.Storage;
import jenny.storage.TaskStorage;
import jenny.tasks.Task;
import jenny.tasks.TaskList;
import jenny.util.Parser;
import jenny.util.Ui;

import java.util.ArrayList;

/**
 * Initialise the JennyBot application.
 * CS2103 Week 2
 * AY21/22 Semester 1
 *
 * @author Deon
 */
public final class JennyBot {
    private final Storage<ArrayList<Task>> storage;
    private TaskList tasks;
    private final Ui ui;

    public JennyBot(String filePath) {
        ui = new Ui(System.in, System.out);
        storage = new TaskStorage<>(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (JennyException e) {
            ui.print(e.getMessage());
            tasks = new TaskList();
        }
    }

    public void run() {
        ui.greet();
        boolean isExit = false;
        while(!isExit) {
            try {
                String nextLine = ui.read();
                Command cmd = Parser.parse(nextLine);
                cmd.run(tasks, ui, storage); // throws JennyException
                isExit = cmd.isExit();
            } catch (JennyException e) {
                ui.print(e.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        new JennyBot("tasks.txt").run();
    }
}
