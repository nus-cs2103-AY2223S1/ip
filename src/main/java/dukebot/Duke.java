package dukebot;

import dukebot.command.Command;

/**
 * Encapsulates the Duke class.
 */
public class Duke {

    private static Storage storage;
    private static TaskList tasks = new TaskList();
    private Parser p;
    private boolean isExit = false;

    public Duke() {
        storage = new Storage("src/main/tasks.txt");
        try {
            tasks = storage.load();
        } catch (DukeException e) {
            Ui.showError(e);
            tasks = new TaskList();
        }
        p = new Parser(tasks);
    }

    public boolean isExit() {
        return isExit;
    }

    /**
     * Responds to a command from the user.
     *
     * @param s String containing the user's command.
     * @return Response to be shown to the user.
     */
    public String getResponse(String s) {
        assert storage != null;
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
