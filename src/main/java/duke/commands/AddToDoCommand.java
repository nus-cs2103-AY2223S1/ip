package duke.commands;

import duke.*;
import duke.exceptions.DukeEmptyCommandException;

public class AddToDoCommand extends Command{

    public AddToDoCommand(String description) {
        super(description);
    }

    public void execute(Storage storage) {

        try {
            if (description.length() <= 5) {
                throw new DukeEmptyCommandException();
            }
            String information = description.substring(5);

            Task newTask = new ToDo(information);
            TaskList.add(newTask);
            Ui.printAddTask(newTask);
        } catch (DukeEmptyCommandException e) {
            Ui.printError(e.getMessage());
        }

    }
}
