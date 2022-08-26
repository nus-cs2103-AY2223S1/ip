/**
 * Apollo is a chatbot that keeps tracks of various items, encapsulated
 * as a Duke instance.
 *
 * @author Kartikeya
 */
public class Duke {
    private Storage storage;
    private TaskList itemList;
    private final Ui ui;
    private final Parser parser;

    public Duke() {
        try {
            storage = new Storage();
            itemList = new TaskList(storage.load());
        } catch (DukeException e) {
            itemList = new TaskList();
        } finally {
            ui = new Ui();
            parser = new Parser(itemList, storage, ui);
        }
    }

    /**
     * Initialises the chatbot.
     */
    public void start() {
        ui.showIntro();
        run();
    }

    /**
     * Runs Apollo. Waits for input lines and
     * processes them accordingly.
     */
    private void run() {
        try {
            parser.parseUserInput(ui.getUserInput());
        } catch (DukeException e) {
            ui.showError(e);
        } finally {
            run();
        }
    }

    public static void main(String[] args) {
        new Duke().start();
    }
}
