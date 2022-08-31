package Commands;

import Duck.Storage;
import Duck.TaskList;
import UI.UI;
import Models.Todo;

/**
 * Encapsulates the find command
 */
public class FindCommand extends Commands{
    private String search;

    /**
     * Uses java's .contains() method to locate strings within a title
     * @param search string to be searched
     */
    public FindCommand(String search){
        this.search = search;
    }

    /**
     * iterates through the TaskList and uses java's .contains() method to locate the string
     * prints the item if it finds the string within its title
     * @param list TaskList to be modified
     * @param storage Storage to be modified
     * @throws IndexOutOfBoundsException Inherited from Commands
     */
    @Override
    public void execute(TaskList<Todo> list, Storage storage, UI ui) throws IndexOutOfBoundsException {
        boolean foundAny = false;
        String s = "";
        for (int i = 0; i < list.size(); i++){
            if (list.get(i).getTitle().contains(search)) {
                foundAny = true;
                s = s.concat(list.get(i).toString() + "\n");
            }
        }
        if (foundAny) {
            ui.sendTextToUi("Here are the items:\n" + s);
            return ;
        }
        ui.sendTextToUi("Quack! No items found!!!");
    }

    /**
     * see commands superclass
     * @return returns false
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
