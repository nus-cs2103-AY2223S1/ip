package duke.tasklist;

import duke.listobjects.ListObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Represents TaskList which stores a list of tasks the user wants to store and implements the Serializable interface
 */
public class TaskList implements Serializable {

    private ArrayList<ListObject> tasksList;
    private static int numberOfTodos;
    private static int numberOfDeadlines;
    private static int numberOfEvents;

    /**
     * Constructs a TaskList with an empty ArrayList of ListObjects
     */
    public TaskList() {
        this.tasksList = new ArrayList<ListObject>();
    }

    /**
     * Sets the taskslist of the TaskList object to be input list
     * @param list ArrayList of ListObjects the user wished to store
     */

    public void setTasks(ArrayList<ListObject> list) {

        this.tasksList = list;
    }

    /**
     * Returns the number of tasks stored
     * @return int representing number of tasks stored
     */
    public int getListLength() {
        return this.tasksList.size();
    }

    /**
     * Handles instructions on modifying a specific item in the list
     * @param instruction String representing type of action to be performed
     * @param itemNum int representing the item number to be modified
     */
    public void handleItem(String instruction, int itemNum) {
        if (instruction.equals("UNMARK") || instruction.equals("MARK")) {
            ListObject currItem = tasksList.get(itemNum);
            currItem.switchStatus();
            System.out.println(currItem.toString());
        } else if (instruction.equals("DELETE")) {
            ListObject currItem = tasksList.get(itemNum);
            tasksList.remove(itemNum);
            System.out.println(currItem.toString());
        }
    }

    /**
     * Adds a ListObject (task) to the taskslist field
     * @param obj ListObject representing the task to be added
     */

    public void handleItemAddition(ListObject obj) {

        this.tasksList.add(obj);
    }

    /**
     *Prints the list of tasks stored
     */
    public void printList() {
        for (int i = 0; i < this.tasksList.size(); i++) {
            System.out.println(i + ". " + tasksList.get(i).toString());
        }
    }


    /**
     * Returns the number of tasks stored
     * @return String representing the number of tasks stored
     */
    public String knowTaskCount(){
        return tasksList.size() + " tasks ";
    }

    /**
     * Returns the taskslist stored in the object
     * @return ArrayList representing the list of tasks stored
     */
    public ArrayList<ListObject> storeAllTasks() {
        return this.tasksList;
    }

    /**
     * Finds tasks containing the keyword and displays them
     * @param target String representing the keyword to search by
     */
    public void findByKeyword(String target) {
        Stream<ListObject> filteredOptions = tasksList.stream().filter(x -> x.hasWord(target));
        List<ListObject> eligibleTasks = filteredOptions.collect(Collectors.toList());
        ArrayList<ListObject> filteredTasks = new ArrayList<>(eligibleTasks);
        TaskList tasksToDisplay = new TaskList();
        tasksToDisplay.setTasks(filteredTasks);
        tasksToDisplay.printList();
    }

    /**
     * Returns the String representation of a TaskList object
     * @return String representation of TaskList object
     */
    @Override
    public String toString() {
        String str = "";
        for (int i = 0; i < this.tasksList.size(); i++) {
            str = str + i + ". " + tasksList.get(i).toString();
        }
        return str;
    }

}