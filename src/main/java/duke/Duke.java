package duke;

import duke.command.Command;

/**
 * The main class for the duke.Duke program.
 *
 * @author Samuel Cheong
 * @version 0.1
 */
public class Duke {

    private static TaskList taskList = new TaskList();
    //    private duke.Storage storage;
    private Ui ui;
    private String input;
    private Storage storage;


    public String getResponse(String input) {
        try {
            Command command = Parser.parseInput(storage, input, taskList);
            return command.execute(storage, taskList, ui);
//            return "duke.Duke heard: " + input;
        } catch (DukeException exception) {
            return ui.printErrorMessage(exception);
        }
    }
    /**
     * Creates a duke.Duke object using the filePath inputted by the user for storage of tasks.
     *
     */
    public Duke() {


        try {
            ui = new Ui();
            storage = new Storage("data/duke.txt");
            ui.printGreetings();
            taskList = storage.loadFileData();
        } catch (DukeException e) {
            taskList = new TaskList();
        }
//        input = ui.nextLine();
    }

    /**
     * run is the method that executes the duke.Duke program
     * run the program using launcher.java if you want a Duke GUI
     */
    public void run() {
        while (!input.equals("bye")) {
            try {
                Command command = Parser.parseInput(storage, input, taskList);
                System.out.println(command.execute(storage, taskList, ui));

            } catch (DukeException e) {
                System.out.println(e.getMessage());
            }
            input = ui.nextLine();
        }
        System.out.println("Bye. Hope to see you again soon!\n");
    }

//    public static void main(String[] args) {
//        new Duke().run();
//    }
}
