package handlers;

import entities.Task;
import exceptions.DukeException;
import service.Service;

public class UnmarkHandler implements IHandler{
    private String taskIndex;

    public UnmarkHandler(HandlerFactory factory) {
        this.taskIndex = factory.taskName;
    }

    @Override
    public void handle(Service s) throws DukeException {
        if (this.taskIndex == null) {
            throw new DukeException("Invalid list index!\nUsage: `unmark 2`");
        }
        try{
            int number = Integer.parseInt(this.taskIndex);
            Task item = s.list.get(number - 1);
            item.setDone(false);
            s.ui.customPrint("OK, I've marked this task as not done yet:\n  " + item);
        }
        catch (NumberFormatException ex) {
            throw new DukeException("Invalid list index!\nUsage: `unmark 2`");
        }
    }
}