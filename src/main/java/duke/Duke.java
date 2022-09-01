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
     * Returns reply which is given by the Duke Bot.
     *
     * @return The reply given by Duke.
     */
    public String getReply(String input) {
        Scanner scanner = new Scanner(System.in);
        String reply;
        try {
            Command command = Parser.parse(input, taskList);
            reply = command.execute(ui, storage, taskList);
        } catch (DukeException e) {
            reply = ui.showExceptionMessage(e.toString());
        } catch (NumberFormatException e) {
            reply = ui.showExceptionMessage("Please enter a valid number");
        } catch (DateTimeParseException e) {
            reply = ui.showExceptionMessage("Sorry! Please include a valid date entry");
        }
        scanner.close();
        return reply;
    }

}

