package chacha;

import chacha.commands.Command;
import java.io.IOException;


/**
 * Chatbot for task tracking.
 * 
 * @author Singh Abdullah Alexander
 */
public class Chacha {

    private Storage storage;
    private TaskList taskList;
    private Ui ui;
    boolean isExit;

    /**
     * Constructor for Chacha.
     * 
     * @param filePath File path to save data to and load data from.
     */
    public Chacha(String filePath) {
        isExit = false;
        ui = new Ui();
        storage = new Storage("data/tasks.txt");
        try {
            taskList = new TaskList(storage.load());
        } catch (IOException e) {
            ui.printError("making new file");
            taskList = new TaskList();
        }
    }
    
    /** 
     * Main method to initialise Chacha.
     * 
     * @param args Command line arguments.
     */
    public static void main(String[] args) {
        new Chacha("data/tasks.txt").run();
    }     

    /**
     * Runs an instantiated Chacha.
     */
    public void run() {
        System.out.println("run");
        while (!isExit) {
            try {
                System.out.println("parsing");
                String fullCommand = ui.readInput();

                Command command = Parser.parse(fullCommand);

                command.execute(taskList, ui, storage);
                isExit = command.isExit();
            } catch (ChachaException e) {
                ui.printError(e.getMessage());
            }
        }
        try {
            storage.saveToFile(taskList);
        } catch (IOException e) {
            System.out.println("Unable to save file");
        }
    }

    /**
     * Returns reponse.
     *
     * @param input User input.
     * @return Response as a string.
     */
    public String getResponse(String input) {
        try {

            Command c = Parser.parse(input);

            c.execute(taskList, ui, storage);
        } catch (ChachaException e) {

            ui.printError(e.getMessage());
        }
        return ui.buildResponse();
    }
}
