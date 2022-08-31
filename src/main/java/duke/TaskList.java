package duke;
import java.util.*;

public class TaskList {
    private List<Task> list;

    /*
     * Constructor for class TaskList.
     */
    public TaskList() {
        this.list = new ArrayList<>();
    }

    /*
     * converts the contents of the list of tasks to String.
     * @return String containing all items in the list
     */
    @Override 
    public String toString(){
        String itemString = "";
        int index = 1;
        for (Task item : list) {
            itemString += String.valueOf(index) + ". " + item.toString() + "\n";
            index++;
        }
        return itemString;
    }

    /*
     *  getter for task objects in the list. 
     * @return List<Task>
     */
    public List<Task> getTaskList() {
        return this.list;
    }

    /*
     * sets the indicated task as completed.
     * @param number the order of the task
     * @param ui the UI Object that prints messages to the console
     */
    public void markAsDone(int number, UI ui){
        Task item = list.get(number - 1);
        item.mark();
        ui.markedMsg(item);
        list.set(number - 1, item);
    }

    /*
     * Deletes specified task from list.
     * @param number the order of the list to be removed
     * @param ui the UI object that prints to screen
     */
    public void deleteFromList(int number, UI ui) {
        Task removed = list.remove(number);
        ui.rmvMsg(removed, list.size());
    }

    /*
     * Unmarks task from list given a specified number.
     * @param number the index of the task to be unmarked
     * @param ui the UI Object that prints to screen
     */
    public void unmarkTask(int number, UI ui) {
        Task item = list.get(number - 1);
        item.unmark();
        ui.unmarkedMsg(item);
        list.set(number - 1, item);
    }

    public void addTaskFromFile(Task newTask) {
        this.list.add(newTask);
    }

    public void addToList(String item, UI ui){
        try {
            Task newTask = new Task(item);
            list.add(newTask);
            int listSize = list.size();
            ui.addToListMsg(listSize, newTask);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    public void findTasksByKeyword(String keyword, UI ui) {
        TaskList keywordTaskList = new TaskList();
        for (Task task : this.list) {
            String taskDesc = task.description;
            if (taskDesc.contains(keyword)) {
                keywordTaskList.list.add(task);
            } 
        }
        ui.printList(keywordTaskList);
    }

}
