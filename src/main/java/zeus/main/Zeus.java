package zeus.main;

import zeus.command.Command;
import zeus.exception.ZeusException;

/**
 * Chatbot class that helps a person to keep track of various things.
 */
public class Zeus {

    private static final String filePath = "data/tasks.txt";
    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private boolean isExit = false;

    /**
     * Constructor of chatbot class.
     */
    public Zeus() {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (ZeusException e) {
            System.out.println(e.getMessage());
            tasks = new TaskList();
        }
    }

    public String getResponse(String input) {
        if (isExit) {
            return "You have exited.";
        }
        try {
            ui.newResponse();
            Command c = Parser.parse(input);
            c.execute(tasks, ui, storage);
            isExit = c.isExit();
            return ui.getResponse();
        } catch (ZeusException e) {
            return e.getMessage();
        }
    }
}
