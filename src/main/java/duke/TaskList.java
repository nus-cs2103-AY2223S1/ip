package duke;
import java.util.*;

public class TaskList {
    private List<Task> list;

    public TaskList() {
        this.list = new ArrayList<>();
    }

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

    public List<Task> getTaskList() {
        return this.list;
    }

    public void markAsDone(int number, UI ui){
        Task item = list.get(number - 1);
        item.mark();
        ui.markedMsg(item);
        list.set(number - 1, item);
    }

    public void deleteFromList(int number, UI ui) {
        Task removed = list.remove(number);
        ui.rmvMsg(removed, list.size());
    }

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

}
