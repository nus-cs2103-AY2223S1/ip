package main.java.duke;

import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * The self-named main class that controls our lovely chatbot, Duke
 *
 * @author eugeneleong
 * @version 1.0
 */

public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Loads Duke up with the appropriate list of tasks
     * @param filePath denoting the source of the file
     * @throws FileNotFoundException if the filePath is typed incorrectly
     */
    public Duke(String filePath) throws FileNotFoundException {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (FileNotFoundException de) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    /**
     * Main method to drive the program
     * @param args empty
     */
    public static void main(String[] args) {
        try {
            new Duke("./src/duke.txt").run();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * The crux of the pain our dear chatbot, Duke, has to go through
     */
    public void run() {
        ui.saysHi();
        tasks.listAll(); // prints out contents of pre-existing file
        while (true) {
            try {
                String fullCommand = ui.readCommand();
                String commandType = Parser.getCommandType(fullCommand);
                storage.save(fullCommand);

                switch(commandType) {
                case "LIST":
                    tasks.listAll();
                    break;
                case "MARK":
                    int markIdx = Integer.parseInt(fullCommand.substring(5)) - 1;
                    tasks.mark(markIdx);
                    break;
                case "UNMARK":
                    int unmarkIdx = Integer.parseInt(fullCommand.substring(7)) - 1;
                    tasks.unmark(unmarkIdx);
                    break;
                case "TODO":
                    tasks.add(new ToDo(fullCommand.substring(5)));
                    break;
                case "DEADLINE":
                    String dlAction = fullCommand.substring(9, fullCommand.indexOf("/") - 1);
                    tasks.add(new Deadline(dlAction, Parser.formatEventTime(fullCommand)));
                    break;
                case "EVENT":
                    String eAction = fullCommand.substring(6, fullCommand.indexOf("/") - 1);
                    tasks.add(new Event(eAction, Parser.formatEventTime(fullCommand)));
                    break;
                case "DELETE":
                    int deleteIdx = Integer.parseInt(fullCommand.substring(7)) - 1;
                    tasks.delete(deleteIdx);
                    break;
                case "FIND":
                    String keyword = fullCommand.substring(5);
                    tasks.find(keyword);
                    break;
                case "BYE":
                    ui.saysBye();
                    break;
                }
                if (commandType.equals("BYE")) { break; }
            } catch (IllegalArgumentException iae) {
                System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
            } catch (IOException e) {
                System.out.println("Your input cannot be saved. Sorry! :(");
            }
        }
    }
}
