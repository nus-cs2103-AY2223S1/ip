package duke.tasklist;

import duke.exceptions.InvalidItemException;
import duke.listobjects.ListObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Represents TaskList which stores a list of tasks the user wants to store and implements the Serializable interface
 */
public class TaskList implements Serializable {
    private ArrayList<ListObject> tasksList;

    /**
     * Constructs a TaskList with an empty ArrayList of ListObjects
     */
    public TaskList() {
        this.tasksList = new ArrayList<>();
    }

    /**
     * Sets the taskslist of the TaskList object to be input list
     *
     * @param list ArrayList of ListObjects the user wished to store
     */

    public void setTasks(ArrayList<ListObject> list) {
        assert list != null;
        this.tasksList = list;
    }

    /**
     * Returns the number of tasks stored
     *
     * @return int representing number of tasks stored
     */
    public int getListLength() {
        return this.tasksList.size();
    }

    /**
     * Handles instructions on modifying a specific item in the list
     *
     * @param instruction String representing type of action to be performed
     * @param itemNum     int representing the item number to be modified
     */
    public String handleItem(String instruction, int itemNum) throws InvalidItemException {

        if (itemNum >= 0 && itemNum < this.getListLength()) {
            ListObject currItem = tasksList.get(itemNum);
            if (instruction.equals("UNMARK")) {
                switch (currItem.getStatus()) {
                case 1:
                    assert currItem != null;
                    currItem.switchStatus();
                    return currItem.toString();

               default:
                    return "\n Oh I had forgotten...'tis already so!";
                }
            } else if (instruction.equals("MARK")){
                switch (currItem.getStatus()) {
                case 0:
                    assert currItem != null;
                    currItem.switchStatus();
                    return currItem.toString();

                default:
                    return "\n Oh I had forgotten...'tis already so!";
                }
            } else if (instruction.equals("DELETE")) {
                assert currItem != null;
                tasksList.remove(itemNum);
                return currItem.toString();
            } else {
                return "Oh dear! I was thinking of my sweet Duchess Anne...\n"
                        + "What was it that you asked me?";
            }
        } else {
            throw new InvalidItemException();
        }

    }

    /**
     * Adds a ListObject (task) to the taskslist field
     *
     * @param obj ListObject representing the task to be added
     */

    public void handleItemAddition(ListObject obj) {

        this.tasksList.add(obj);
    }

    /**
     * Prints the list of tasks stored
     */
    public void printList() {
        for (int i = 0; i < this.tasksList.size(); i++) {
            System.out.println(i + ". " + tasksList.get(i).toString());
        }
    }

    /**
     * Sorts the list based on natural comparison order of list objects
     */
    public void sortList() {
        Collections.sort(tasksList, (o1, o2) -> o1.compareTo(o2));
    }


    /**
     * Returns the number of tasks stored
     *
     * @return String representing the number of tasks stored
     */
    public String knowTaskCount() {
        return tasksList.size() + " tasks ";
    }

    /**
     * Returns the taskslist stored in the object
     *
     * @return ArrayList representing the list of tasks stored
     */
    public ArrayList<ListObject> storeAllTasks() {
        return this.tasksList;
    }

    /**
     * Finds all list items containing search keyword in their task description
     *
     * @param target String representing keyword to search for
     * @return String representing list containing only items whose task descriptions contain keyword
     * @throws InvalidItemException if there is no item in the list with the keyword in its description
     */
    public String findByKeyword(String target) throws InvalidItemException {
        Stream<ListObject> filteredOptions = tasksList.stream().filter(x -> x.hasWord(target));
        assert filteredOptions != null;
        List<ListObject> eligibleTasks = filteredOptions.collect(Collectors.toList());
        if (eligibleTasks.size() > 0) {
            ArrayList<ListObject> filteredTasks = new ArrayList<>(eligibleTasks);
            TaskList tasksToDisplay = new TaskList();
            tasksToDisplay.setTasks(filteredTasks);
            return tasksToDisplay.toString();
        } else {
            throw new InvalidItemException("There is no such item in the list, my friend!");
        }
    }

    /**
     * Returns the String representation of a TaskList object
     *
     * @return String representation of TaskList object
     */
    @Override
    public String toString() {
        String str = "";
        for (int i = 0; i < this.tasksList.size(); i++) {
            str = str + (i + 1) + ". " + tasksList.get(i).toString() + "\n";
        }
        return str;
    }

}
