package duke;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import duke.commands.Command;

/**
 * The Duke is a personalized chatbot.
 */
public class Duke {

    private final String filePath = "./data/duke.txt";
    private final Storage myStorage;
    private boolean isExit;
    private TaskList tasks;
    private Ui ui;


    public boolean isExit() {
        return isExit;
    }

    /**
     * Constructor that initializes Duke.
     */
    public Duke() {
        ui = new Ui();
        myStorage = new Storage(filePath);
        try {
            tasks = new TaskList(myStorage.load());
        } catch (FileNotFoundException e) {
            ui.showLoadingError();
            File path = new File(filePath);
            tasks = new TaskList();
            try {
                path.createNewFile();
            } catch (IOException e2) {
                path.getParentFile().mkdirs();
            }
        }
    }




    public String sayHi() {
        ui.showWelcome();
        return ui.displayDescription();
    }



    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    public String getResponse(String input) {
        try {
            Command cmd = Parser.parse(input);
            cmd.execute(tasks, ui, myStorage);
            isExit = cmd.isExit();
            return ui.displayDescription();
        } catch (IllegalArgumentException e) {
            ui.showError("I'm sorry, but I don't know what that means :-(");
            return ui.displayDescription();
        } catch (DukeException e2) {
            ui.showError(e2.getMessage());
            return ui.displayDescription();
        }
    }

}
