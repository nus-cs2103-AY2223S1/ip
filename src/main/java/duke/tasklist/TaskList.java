package duke.tasklist;

import duke.listobjects.ListObject;

import java.io.Serializable;
import java.util.ArrayList;

public class TaskList implements Serializable {

    private ArrayList<ListObject> tasksList;
    private static int numberOfTodos;
    private static int numberOfDeadlines;
    private static int numberOfEvents;

    public TaskList() {
        this.tasksList = new ArrayList<ListObject>();
    }

    public void setTasks(ArrayList<ListObject> list) {
        this.tasksList = list;
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

    public void handleItemAddition(ListObject obj) {
        this.tasksList.add(obj);
    }



    public void printList() {
        for (int i = 0; i < this.tasksList.size(); i++) {
            System.out.println(i + ". " + tasksList.get(i).toString());
        }
    }

    public String knowTaskCount() {
        return tasksList.size() + " tasks ";
    }

    public ArrayList<ListObject> storeAllTasks() {
        return this.tasksList;
    }

    @Override
    public String toString() {
        String str = "";
        for (int i = 0; i < this.tasksList.size(); i++) {
            str = str + i + ". " + tasksList.get(i).toString();
        }
        return str;
    }

}