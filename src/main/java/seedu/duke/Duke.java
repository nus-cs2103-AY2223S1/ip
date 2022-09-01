package seedu.duke;

import java.io.IOException;

/**
 * Main class of the program.
 */
public class Duke  {
    
    /*Storage object to handle reading and writing to hard disk */
    private Storage storage;
    /*Tasklist containing an arraylist of tasks */
    private TaskList tasks;
    /*Ui component to handle the user interface of the program */
    private Ui ui;
    /*Parser object to parse user input into recognised Commands */
    private Parser parser;

    /**
     * A constructor for Duke.
     *
     * @param filePath the path of the output to be stored and loaded when the program starts.
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        parser = new Parser();

        try {
            tasks = new TaskList(storage.load());

        } catch (IOException e) {
            // ui.showLoadingError(e);
            e.printStackTrace();
            tasks = new TaskList();
        }


    }
    

    /**
     * Runs the Duke program.
     */
    // public void run() {
    //     // ui.showWelcome();
    //     boolean isExit = false;
    //     while (!isExit) {
    //         try {
    //             String input = ui.readCommand();
    //             Command command = parser.parse(input);
    //             command.execute(tasks, ui, storage, input);
    //             isExit = command.isExit();
    //         } catch (DukeException e) {
    //             ui.showError(e);
    //         }
    //     }
    // }


    public String getResponse(String input) {
        try {
            Command command = parser.parse(input.strip());
            String output = command.execute(tasks, ui, storage, input);
            return output;
            
        } catch (DukeException e) {
            return e.getMessage();
        } 
    }



}
