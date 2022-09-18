package manmeowth;

import manmeowth.command.Command;
import manmeowth.exception.ManMeowthException;
import manmeowth.list.TaskList;
import manmeowth.parser.Parser;
import manmeowth.storage.ListLoader;
import manmeowth.ui.Ui;

/**
 * Represents an interactive 'ToDo list' with set commands to add, modify, and remove tasks.
 *
 * @author WR3nd3
 */
public class ManMeowth {

    /** storage.manmeowth.ListLoader used to load and save remaining tasks  */
    private ListLoader storage;

    /** list.manmeowth.TaskList storing current tasks */
    private TaskList tasks;

    /** ui.manmeowth.Ui handling interactions of input and output with users */
    private final Ui ui;
    private String startUpMessage;
    private String startUpError;

    private boolean isExit = false;

    /**
     * Constructs manmeowth.ManMeowth object and its components.
     */
    public ManMeowth() {
        ui = new Ui();
        startUpMessage = ui.cliShowWelcome();
        tasks = new TaskList();
        storage = new ListLoader(tasks);
        try {
            storage.load();
        } catch (ManMeowthException e) {
            startUpError = ui.showLoadingError();
            startUpMessage += startUpError;
            tasks = new TaskList();
            storage = new ListLoader(tasks);
            storage.load();
        }
    }

    /**
     * Executes the running of the manmeowth.ManMeowth program.
     */
    public void run() {
        ui.showLine();
        System.out.println(startUpMessage);
        ui.showLine();

        while (!isExit) {
            String response = "";
            try {
                String fullCommand = ui.readCommand();
                Command c = Parser.parse(fullCommand);
                response = c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (ManMeowthException e) {
                response = ui.showError(e.getMessage());
            } finally {
                ui.showLine();
                System.out.println(response);
                ui.showLine();
            }
        }
    }

    /**
     * Provides entry point into manmeowth.ManMeowth program when run through the CLI terminal.
     *
     * @param args The command line arguments.
     **/
    public static void main(String[] args) {
        new ManMeowth().run();
    }



    /**
     * Returns appropriate response to the given input through the form of a string representation
     * of the state and behaviour of the manmeowth program.
     *
     * @param input The input entered by users.
     * @return String representing the appropriate response to the input.
     */
    public String getResponse(String input) {
        String response;
        try {
            Command c = Parser.parse(input.trim());
            isExit = c.isExit();
            response = c.execute(tasks, ui, storage);
            return response;
        } catch (ManMeowthException e) {
            response = ui.showError(e.getMessage());
            return response;
        }
    }

    /**
     * Checks whether the last command primed ManMeowth to exit.
     *
     * @return boolean representing whether the application will end.
     */
    public boolean isExit() {
        return isExit;
    }

    /**
     * Returns ManMeowth's welcome message for the GUI interface.
     *
     * @return ManMeowth's welcome message.
     */
    public String getWelcome() {
        return ui.guiShowWelcome() + startUpError;
    }

}


