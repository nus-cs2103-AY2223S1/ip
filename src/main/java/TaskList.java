import java.util.ArrayList;

public class TaskList {

    private final ArrayList<Task> myList;

    TaskList() {
        this.myList = new ArrayList<>();
    }

    void listTasks() {
        System.out.println("Here are the tasks in your list: ");
        for(int i = 0; i<myList.size(); i++) {
            System.out.println(i+1 + "." + myList.get(i));
        }
    }

    void markTask(Integer itemNumber) {
        myList.get(itemNumber).markAsDone();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println("["
        + myList.get(itemNumber).getStatusIcon() + "] "
        + myList.get(itemNumber).getDescription());
    }

    void unmarkTask (Integer itemNumber) {
        myList.get(itemNumber).markAsUndone();
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println("["
        + myList.get(itemNumber).getStatusIcon() + "] "
        + myList.get(itemNumber).getDescription());
    }

    void addTask(Task myTask) {
        try {
            myList.add(myTask);
            System.out.println("Got it. I've added this task:");
            System.out.println(myTask);
            System.out.println("Now you have " + myList.size() + " in the list.");
        } catch (Exception e) {
            System.out.println("☹ OOPS!!! The description of a todo cannot be empty.");
        }
    }

    void removeTask(Integer itemNumber) {
        System.out.println("Noted. I've removed this task:");
        System.out.println(myList.get(itemNumber));
        myList.remove(itemNumber);
        System.out.println("Now you have " + myList.size() + " in the list.");
    }
 
}