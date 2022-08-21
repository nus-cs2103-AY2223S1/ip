import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;

public class Jean {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Jean(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (JeanException e) {
            ui.showJeanError(e.getMessage());
            tasks = new TaskList();
        } catch (IOException e) {
            ui.showGeneralError(e.getMessage());
        }
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
        new Jean("data/list.txt").run();
    }
}
