package duke;
import java.util.ArrayList;
import java.io.FileWriter;
import java.io.IOException;

public class TaskList {

    private final ArrayList<Task> myList;

    TaskList(ArrayList<Task> myList) {        
        this.myList = myList;
    }

    /**
     * Adds new task to master list
     */
    void addTask(Task myTask) {
        try {
            myList.add(myTask);
            System.out.println("Got it. I've added this task:");
            System.out.println(myTask);
            System.out.println("Now you have " + myList.size() + " task(s) in the list.");
        } catch (Exception e) {
            System.out.println("☹ OOPS!!! The description of a todo cannot be empty.");
        }
    }

    /**
     * List all tasks from master list
     */
    void listTasks() {
        if (myList.size() == 0) {
            System.out.println("You have no tasks in your list.");
        } else {
            System.out.println("Here are the tasks in your list: ");
            for(int i = 0; i<myList.size(); i++) {
                System.out.println(i+1 + "." + myList.get(i));
            }
        }
    }

    /**
     * Marks task as done
     */
    void markTask(Integer itemNumber) {
        myList.get(itemNumber).markAsDone();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println("["
        + myList.get(itemNumber).getStatusIcon() + "] "
        + myList.get(itemNumber).getDescription());
    }

    /**
     * Marks task as undone
     */
    void removeTask(Integer itemNumber) {
        System.out.println("Noted. I've removed this task:");
        System.out.println(myList.get(itemNumber));
        myList.remove(myList.get(itemNumber));
        System.out.println("Now you have " + myList.size() + " in the list.");
    }

    /**
     * Saves tasks into duke textfile
     */
    void saveTasks() {
        try {
            FileWriter myWriter = new FileWriter("duke.txt");
            for (Task myTask : myList) {
                myWriter.write(myTask.toString() + "\n");
            }
            myWriter.close();
          } catch (IOException e) {
            System.out.println("Error saving tasks.");
            e.printStackTrace();
          }
    }

    /**
     * Marks task as undone
     */
    void unmarkTask (Integer itemNumber) {
        myList.get(itemNumber).markAsUndone();
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println("["
        + myList.get(itemNumber).getStatusIcon() + "] "
        + myList.get(itemNumber).getDescription());
    }

    /**
     * For JUnit tests
     */
    String testListTasks () {
        StringBuilder sb = new StringBuilder();
        for (Task myTask: myList) {
            sb.append(myTask.toString());
        }
        return sb.toString();
    }
}