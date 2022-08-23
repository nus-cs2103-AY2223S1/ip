package handlers;

import entities.Task;
import exceptions.DukeException;
import service.Service;

public class MarkHandler implements IHandler{
    private String taskIndex;

    public MarkHandler(HandlerFactory factory) {
        this.taskIndex = factory.taskName;
    }

    @Override
    public void handle(Service s) throws DukeException {
        if (this.taskIndex == null) {
            throw new DukeException("Invalid list index!\nUsage: `mark 2`");
        }
        try{
            int number = Integer.parseInt(this.taskIndex);
            if (number > s.list.size()) {
                throw new DukeException("Task not found! Please try again.");
            }
            Task item = s.list.get(number - 1);
            item.setDone(true);
            s.ui.customPrint("Nice! I've marked this task as done:\n  " + item);
        }
        catch (NumberFormatException ex) {
            throw new DukeException("Invalid list index!\nUsage: `mark 2`");
        }
    }
}