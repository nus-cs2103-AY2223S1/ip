package DukeBot;

import DukeBot.command.Command;

/**
 * Encapsulates the Duke class.
 */
public class Duke {

    private static Storage storage;
    private static TaskList tasks = new TaskList();
    private Parser p;
    boolean isExit;

    public Duke() {
        storage = new Storage("src/main/tasks.txt");
        try {
            tasks = storage.load();
        } catch (DukeException e) {
            Ui.showError(e);
            System.out.println("Creating new file.");
            tasks = new TaskList();
        }
        p = new Parser(tasks);
        storage.write(tasks);
    }

    public boolean isExit() {
        return isExit;
    }
    public String getResponse(String s) {
        try {
            Command c = p.parse(s);
            String response = c.execute();
            isExit = c.isExit();
            return response;
        } catch (DukeException e) {
            return Ui.showError(e);
        } finally {
            storage.write(tasks);
        }
    }

}
