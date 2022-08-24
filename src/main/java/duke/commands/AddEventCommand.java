package duke.commands;

import duke.*;
import duke.exceptions.DukeEmptyCommandException;
import duke.exceptions.DukeException;
import duke.exceptions.DukeInvalidDescriptionException;

import java.time.DateTimeException;

public class AddEventCommand extends Command {

    public AddEventCommand(String description) {
        super(description);
    }

    public void execute(Storage storage) {

        try {
            if (description.length() <= 6) {
                throw new DukeEmptyCommandException();
            }

            String[] information = description.substring(6).split(" /at ", 3);

            if (information.length != 2) {
                throw new DukeInvalidDescriptionException();
            }

            Task newTask = new Event(information[0], information[1]);
            TaskList.add(newTask);
            Ui.printAddTask(newTask);
        } catch (DukeException e) {
            Ui.printError(e.getMessage());
        } catch (DateTimeException e) {
            Ui.printError("Sorry i could not recognize the date. Pls use this format \"YYYY-MM-DD\"");
        }

    }

}
