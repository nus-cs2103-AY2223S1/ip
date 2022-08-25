package Commands;

import Duck.Storage;
import Duck.TaskList;
import Duck.UI;
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
    public void execute(TaskList<Todo> list, Storage storage) throws IndexOutOfBoundsException {
        boolean foundAny = false;
        for (int i = 0; i < list.size(); i++){
            if (list.get(i).getTitle().contains(search)) {
                foundAny = true;
                System.out.println(list.get(i).toString());
            }
        }
        if (foundAny) {
            UI.foundItems();
            return ;
        }
        UI.foundNoItems();
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
