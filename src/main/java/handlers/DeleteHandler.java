package handlers;

import entities.Task;
import exceptions.DukeException;

import java.util.List;

import static utils.Utils.customPrint;

public class DeleteHandler implements IHandler{
    private String taskIndex;

    public DeleteHandler(HandlerFactory factory) {
        this.taskIndex = factory.taskName;
    }

    @Override
    public void handle(List<Task> list) throws DukeException {
        if (this.taskIndex == null) {
            throw new DukeException("Invalid list index!\nUsage: `delete 2`");
        }
        try{
            int number = Integer.parseInt(this.taskIndex);
            Task item = list.get(number - 1);
            list.remove(number - 1);
            customPrint("Noted. I've removed this task:\n" +
                    item +
                    "Now you have " + list.size() + " tasks in the list.");
        }
        catch (NumberFormatException ex) {
            throw new DukeException("Invalid list index!\nUsage: `delete 2`");
        }
    }
}