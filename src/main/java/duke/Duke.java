package duke;

import duke.command.Command;
import duke.exception.InvalidDescriptionException;
import duke.exception.InvalidIndexException;
import duke.exception.InvalidInputException;
import duke.exception.InvalidTimeException;
import duke.task.TaskList;

import java.time.format.DateTimeParseException;


/**
 * Duke ChatBot that is able to manage a list of tasks
 */
public class Duke {

    Storage storage;
    TaskList taskList;
    Ui ui;

    /**
     * Creates a Duke object
     */
    public Duke(String filePath) {
        storage = new Storage(filePath);
        taskList = new TaskList(storage.loadTaskFile());
        ui = new Ui();
    }

    /**
     * Executes command and returns Duke's response
     */
    public String getResponse(String input) {
        try {
            Command c = Parser.parse(input);
            c.execute(taskList, storage);
        } catch (InvalidInputException | InvalidIndexException | InvalidDescriptionException | InvalidTimeException e) {
            Ui.appendDukeResponse(e.getMessage());
        } catch (DateTimeParseException e) {
            Ui.appendDukeResponse("Enter a valid date and time! (Eg. 2020-12-25 1330)");
        }
        return Ui.getDukeResponse();
    }
}
