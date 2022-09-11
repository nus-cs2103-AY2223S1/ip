package Command;

import Duck.Storage;
import Duck.TaskList;
import Models.Deadline;
import Models.Event;
import Models.Todo;
import Quackceptions.InvalidObjectClass;
import UI.UI;

import java.util.Date;

public class UpdateCommand extends Commands{
    private int index;
    private Todo item;
    private Date updatedTime;
    private String updatedTitle;
    private boolean isUpdatingTime;

    public UpdateCommand(int index, String value) {
        this.index = index;
        this.updatedTitle = value;
        this.isUpdatingTime = false;
    }

    public UpdateCommand(int index, Date value) {
        this.index = index;
        this.updatedTime = value;
        this.isUpdatingTime = true;
    }

    /**
     * abstract function to be implemented by all subclasses
     * @param list    TaskList to be modified
     * @param storage Storage to be modified
     * @param ui UI to send information for display
     * @throws IndexOutOfBoundsException is thrown when accessing a non existent object
     */
    @Override
    public void execute(TaskList<Todo> list, Storage storage, UI ui) throws IndexOutOfBoundsException, InvalidObjectClass {
        this.item = list.get(index);
        updateEntry();
        ui.sendTextToUi("Quack! Item updated!" + item);
    }

    /**
     * checks if the command returned is an exit command
     * @return true if it's an exit command, false otherwise
     */
    @Override
    public boolean isExit() {
        return false;
    }

    /**
     * function to update the entry specified
     * @throws InvalidObjectClass thrown when a date is given to a Todo object
     */
    private void updateEntry() throws InvalidObjectClass {
        if (!isUpdatingTime) { //update title
            item.setTitle(this.updatedTitle);
        } else if (item instanceof Deadline) {
            ((Deadline) item).setDate(this.updatedTime);
        } else if (item instanceof Event) {
            ((Event) item).setDate(this.updatedTime);
        } else {
            throw new InvalidObjectClass("Quack! Todo does not have a date!");
        }
    }
}
