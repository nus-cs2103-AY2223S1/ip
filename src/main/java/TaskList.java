import java.util.ArrayList;
import java.util.LinkedList;

public class TaskList {

    private ArrayList<ListObject> tasksList;
    private static int numberOfTodos;
    private static int numberOfDeadlines;
    private static int numberOfEvents;

    public TaskList() {
        this.tasksList = new ArrayList<ListObject>();
    }

    public void addTask(ListObject task) {
        this.tasksList.add(task);
    }

    public int getListLength() {
        return this.tasksList.size();
    }

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

    public void handleItemAdditon(ListObject obj){
        this.tasksList.add(obj);
    }



    public void printList() {
        for (int i = 0; i < this.tasksList.size(); i++) {
            System.out.println(i + ". " + tasksList.get(i).toString());
        }
    }

    public String knowTaskCount(){
        return tasksList.size() + " tasks ";
    }


}