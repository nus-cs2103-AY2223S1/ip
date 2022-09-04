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
    public String addTask(Task myTask) {
        String res = "";
        try {
            myList.add(myTask);
            res += "Got it. I've added this task:\n";
            res += myTask;
            res += "\nNow you have " + myList.size() + " task(s) in the list.";
            return res;
        } catch (Exception e) {
            return "☹ OOPS!!! The description of a todo cannot be empty.";
        }
    }

    /**
     * Find matching tasks from master list
     */
    public String findTasks(String myString) {
        final ArrayList<Task> findList = new ArrayList<>();
        for (Task myTask: myList) {
            if (myTask.description.toUpperCase().contains(myString.toUpperCase())) {
                findList.add(myTask);
            }
        }
        if (findList.size() == 0) {
            return "You have no matching search results.";
        }
        String res = "";
        res += "Here are the matching tasks in your list: \n";
        for(int i = 0; i<findList.size(); i++) {
            res += i+1 + "." + findList.get(i) + "\n";
        }
        assert res != "" : "listTasks returns empty string";
        return res;
        
    }

    /**
     * List all tasks from master list
     */
    public String listTasks() {
        String res = "";
        if (myList.size() == 0) {
            return "You have no tasks in your list.";
        }
        else {
            res += "Here are the tasks in your list: \n";
            for(int i = 0; i<myList.size(); i++) {
                res += (i+1);
                res += "."; 
                res += myList.get(i);
                res += "\n";
            }
            assert res != "" : "listTasks returns empty string";
            return res;
        }
    }

    /**
     * Marks task as done
     */
    public String markTask(Integer itemNumber) {
        String res = "";
        myList.get(itemNumber).markAsDone();
        res += "Nice! I've marked this task as done: [";
        res += myList.get(itemNumber).getStatusIcon();
        res += "] ";
        res += myList.get(itemNumber).getDescription();
        return res;
    }

    /**
     * Removes task from master list
     */
    public String removeTask(Integer itemNumber) {
        String res = "";
        res += "Noted. I've removed this task:\n";
        res += myList.get(itemNumber);
        myList.remove(myList.get(itemNumber));
        res += "\nNow you have " + myList.size() + " in the list.";
        return res;
    }

    /**
     * Saves tasks into duke textfile
     */
    public void saveTasks() {
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
    public String unmarkTask (Integer itemNumber) {
        String res = "";
        myList.get(itemNumber).markAsUndone();
        res += "OK, I've marked this task as not done yet:\n";
        res += "[" + myList.get(itemNumber).getStatusIcon() + "] "
        + myList.get(itemNumber).getDescription();
        return res;
    }

    /**
     * For JUnit tests
     */
    public String testListTasks () {
        StringBuilder sb = new StringBuilder();
        for (Task myTask: myList) {
            sb.append(myTask.toString());
        }
        return sb.toString();
    }
}