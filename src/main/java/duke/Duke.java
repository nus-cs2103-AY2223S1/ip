package duke;

import duke.command.Command;

import javafx.scene.image.Image;



public class Duke {

    /**
     * Stores task information in file.
     */
    private Storage storage;
    /**
     * List to track current tasks.
     */
    private TaskList tasks;
    /**
     * User interface for duke bot.
     */
    private Ui ui;

    private boolean isExit = false;

    private Image user = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image duke = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));

    public Duke() {
        ui = new Ui();
        storage = new Storage("./data/dukeInfo.txt");
        tasks = new TaskList(storage.load());

    }

    public Ui getUi() {
        return this.ui;
    }

    public String getResponse(String input) {
        try {
            Command c = Parser.parse(input);
            String toReturn = c.execute(tasks, ui, storage);
            this.isExit = c.isExit();
            return toReturn;
        } catch (DukeException e) {
            return ui.showError(e);
        }

    }

    public boolean getIsExit() {
        return this.isExit;
    }

    public static void main(String[] args) {
        new Duke();

    }
}
