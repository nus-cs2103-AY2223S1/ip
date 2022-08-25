package dobby.commands;

import dobby.tasks.*;
import dobby.*;

public class DeleteCommand extends Command{
    @Override
    public void execute(DobbyList dl, UserInput ui) {
        try {
            int toDelete = ui.getInd();
            if(dl.getLength() == 0) {
                DobbyChat.noTaskToDelete();
            } else if(toDelete > dl.getLength()) {
                DobbyChat.tooLittleTasks();
            } else if(toDelete <= 0) {
                DobbyChat.wrongTaskNumber();
            } else {
                DobbyChat.deleted(dl.getTask(toDelete), dl);
                dl.delete(toDelete);
            }
        } catch(StringIndexOutOfBoundsException e) {
            DobbyChat.noTaskNumber();
        } catch(NumberFormatException e) {
            DobbyChat.noNumber();
        }
    }
}
