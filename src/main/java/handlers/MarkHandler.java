package handlers;

import entities.Task;
import exceptions.DukeException;

import java.util.List;

import static utils.Utils.customPrint;

public class MarkHandler implements IHandler{
    private String taskIndex;

    public MarkHandler(HandlerFactory factory) {
        this.taskIndex = factory.taskName;
    }

    @Override
    public void handle(List<Task> list) throws DukeException {
        if (this.taskIndex == null) {
            throw new DukeException("Invalid list index!\nUsage: `mark 2`");
        }
        try{
            int number = Integer.parseInt(this.taskIndex);
            Task item = list.get(number - 1);
            item.setDone(true);
            customPrint("Nice! I've marked this task as done:\n  " + item);
        }
        catch (NumberFormatException ex) {
            throw new DukeException("Invalid list index!\nUsage: `mark 2`");
        }
    }
}