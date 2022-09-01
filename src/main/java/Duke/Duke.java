package Duke;

import javafx.application.Application;

/**
 * The Duke.Duke class stores Storage and TaskList as parameters
 * @author LimWeiJun
 */
public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Duke(String filePath) {
        ui = new Ui();
        String[] seperates = filePath.split("/");
        storage = new Storage(ui, seperates[0], seperates[1]);
        try {
            tasks = new TaskList(ui, storage);
        } catch (Exception e) {
            ui.printErrorMessage(e.toString());
        }
    }

    public void takeCommand(String userCommand) {
        Parser.readLine(ui, userCommand, tasks);
    }

    public static void main(String[] args) {
        Application.launch(Ui.class, args);
    }
}
