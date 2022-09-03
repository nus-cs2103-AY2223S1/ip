package duke;

import java.time.format.DateTimeParseException;

import duke.command.Command;
import duke.exception.InvalidDescriptionException;
import duke.exception.InvalidIndexException;
import duke.exception.InvalidInputException;
import duke.exception.InvalidTimeException;
import duke.task.TaskList;




/**
 * Duke is a chatbot that is able to manage a list of tasks
 */
public class Duke {

    private Storage storage;
    private TaskList taskList;
    private Ui ui;

    /**
     * Creates a Duke object
     */
    public Duke(String filePath) {
        storage = new Storage(filePath);
        taskList = new TaskList(storage.loadTaskFile());
        ui = new Ui();
    }

    /**
     * Takes in an input string, parses it for a command,
     * executes the command and returns a string describing
     * what command has been executed
     *
     * @param input
     * @return output string that Duke prints to user
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
