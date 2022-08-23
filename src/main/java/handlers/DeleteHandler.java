package handlers;

import entities.Task;
import exceptions.DukeException;
import service.Service;

import static utils.Utils.customPrint;

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
            s.list.remove(number - 1);
        }
        catch (NumberFormatException ex) {
            throw new DukeException("Invalid list index!\nUsage: `delete 2`");
        }
    }
}