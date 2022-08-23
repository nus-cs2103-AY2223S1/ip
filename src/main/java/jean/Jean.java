package jean;

import java.io.File;
import java.io.IOException;
import java.time.format.DateTimeParseException;

import jean.command.Command;
import jean.exception.JeanException;
import jean.parser.Parser;
import jean.storage.Storage;
import jean.task.TaskList;
import jean.ui.Ui;

public class Jean {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Jean(Ui ui, Storage storage, TaskList tasks) {
        this.ui = ui;
        this.storage = storage;
        this.tasks = tasks;
    }

    public void run() {
        ui.greet();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                Command c = Parser.parse(fullCommand, tasks);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (JeanException e) {
                ui.showJeanError(e.getMessage());
            } catch (IOException e) {
                ui.showGeneralError(e.getMessage());
            } catch (DateTimeParseException e) {
                ui.showGeneralError(e.getMessage());
                ui.printMessage("Please give a valid deadline in the format of yyyy-MM-dd HHmm!"
                                + "\nVeuillez indiquer une date limite valide au format yyyy-MM-dd HHmm!");
            }
        }
    }

    public static void main(String[] args) {
        Ui ui = new Ui();
        Storage storage = new Storage("data/list.txt");
        TaskList tasks = null;
        try {
            File dir = new File("data");
            if (!dir.exists()) {
                dir.mkdirs();
            }
            tasks = new TaskList(storage.load());
        } catch (JeanException e) {
            ui.showJeanError(e.getMessage());
            tasks = new TaskList();
        } catch (IOException e) {
            ui.showGeneralError(e.getMessage());
        }
        new Jean(ui, storage, tasks).run();
    }
}
