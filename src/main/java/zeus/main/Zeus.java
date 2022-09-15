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
     * Constructs a chatbot.
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

    /**
     * Returns String of response based on input.
     *
     * @param input A string of input message.
     * @return A string representing the response to the input message.
     */
    public String getResponse(String input) {
        if (isExit) {
            return "You have exited.";
        }
        try {
            ui.generateNewResponse();
            Command c = Parser.parse(input);
            c.execute(tasks, ui, storage);
            isExit = c.isExit();
            return ui.getResponse();
        } catch (ZeusException e) {
            return e.getMessage();
        }
    }
}
