package duke;

import duke.command.Command;
import duke.task.Task;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Duke class represents the Duke Bot which serves the user.
 */
public class Duke {
    private Storage storage;
    private Ui ui;
    private TaskList taskList;

    /**
     * Constructor of the Duke class.
     * Sets the storage, ui and taskList to the local variables.
     */
    public Duke() {
        try {
            ui = new Ui();
            storage = new Storage();
            taskList = new TaskList(storage.readFile());
        } catch (FileNotFoundException e) {
            System.out.println("File should have been created by now!");
            taskList = new TaskList(new ArrayList<Task>());
        } catch (IOException e) {
            System.out.println("Sorry! This should not be happening!");
            taskList = new TaskList(new ArrayList<Task>());
        }
    }

    /**
     * Gets the command from the terminal, parses
     * the command and executes the command given.
     */
    public void run() {
        ui.Greet();
        boolean isTerminated = false;
        Scanner scanner = new Scanner(System.in);
        while (!isTerminated) {
            try {
                String completeCommand = ui.readCommand(scanner);
                Command command = Parser.parse(completeCommand, taskList);
                command.execute(ui, storage, taskList);
                isTerminated = command.isTerminated();
            } catch (DukeException e) {
                ui.showExceptionMessage(e.toString());
            } catch (NumberFormatException e) {
                ui.printBorder();
                ui.showExceptionMessage("Please enter a valid number");
                ui.printBorder();
            } catch (DateTimeParseException e) {
                ui.printBorder();
                ui.showExceptionMessage("Sorry! Please include a valid date entry");
                ui.printBorder();
            }
        }
        scanner.close();
    }

    public static void main(String[] args) {
        new Duke().run();
    }


}
