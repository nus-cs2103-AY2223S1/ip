package duke.command;

import duke.*;

import java.time.format.DateTimeParseException;

/**
 * AddEventCommand class to represent an instruction to add a new Event task to the TaskList.
 *
 * @author Sheryl Kong (A0240686Y)
 */

public class AddEventCommand extends Command { //Creating an duke.Event duke.Task and adding to the taskList
    private String description;
    private boolean isDone;
    private String date;

    /**
     * Constructor for AddEventCommand class
     *
     * @param description String
     * @param isDone boolean
     * @param date String
     */

    public AddEventCommand(String description, boolean isDone, String date) {
        this.description = description;
        this.isDone = isDone;
        this.date = date;
    }

    @Override
    public void execute(TaskList taskList, Storage storage) throws DukeException {
        try {
            if (!storage.checkIsLoadingFile()) {
                this.date = Parser.parseDate(date);
            }
            Task event = new Event(this.description, this.isDone, this.date);
            taskList.addTask(event);
            storage.saveData(taskList);
            if (!storage.checkIsLoadingFile()) {
                UI.added(event);
                response = UI.addedResponse(event);
            }
        } catch (DateTimeParseException de) {
            response = "OOPS!! Please enter a valid date in the format: YYYY-MM-DD.";
        }
    }

}