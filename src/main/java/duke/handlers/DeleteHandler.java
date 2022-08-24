package duke.handlers;

import duke.entities.Task;
import duke.exceptions.DukeException;
import duke.service.Service;

public class DeleteHandler implements IHandler{
    private String taskIndex;

    public DeleteHandler(HandlerFactory factory) {
        this.taskIndex = factory.taskName;
    }

    @Override
    public void handle(Service s) throws DukeException {
        if (this.taskIndex == null) {
            throw new DukeException("Invalid list index!\nUsage: `delete 2`");
        }
        try{
            int number = Integer.parseInt(this.taskIndex);
            Task item = s.list.remove(number - 1);
            s.ui.customPrint("Noted. I've removed this task:\n" +
                    item +
                    "Now you have " + s.list.size() + " tasks in the list.");;
        }
        catch (NumberFormatException ex) {
            throw new DukeException("Invalid list index!\nUsage: `delete 2`");
        }
    }
}