package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

/**
 * Task contains an ArrayList of the Tasks to be done, as well
 * as the behaviours that can change the Tasks within the ArrayList.
 *
 * @author Alvin Jiang Min Jun
 * @version v0.1
 */
public class TaskList {

    private ArrayList<Task> tasks;

    /**
     * TaskList constructor that creates an instance of a TaskList Object.
     *
     * @param taskList The ArrayList output by the Storage, to be converted into
     *                 an ArrayList of type Task, and stored as a field.
     */
    public TaskList(ArrayList<String> taskList) {
        if (taskList.isEmpty()) tasks = new ArrayList<>();
        ArrayList<Task> result = new ArrayList<>();
        for (String str : taskList) {
            char type = str.charAt(1);
            if (type == 'T') {
                String description = str.substring(7);
                result.add(new Todo(description));
            } else if (type == 'D') {
                String temp = str.substring(7);
                String description = temp.split(" \\(by: ")[0];
                String temp2 = temp.split(" \\(by: ")[1];
                String by = temp2.substring(0, temp2.length() - 1);
                LocalDate date = LocalDate.parse(by, DateTimeFormatter.ofPattern("MMM dd yyyy"));
                result.add(new Deadline(description, date.toString()));
            } else {
                String temp = str.substring(7);
                String description = temp.split(" \\(at: ")[0];
                String temp2 = temp.split(" \\(at: ")[1];
                String at = temp2.substring(0, temp2.length() - 1);
                LocalDate date = LocalDate.parse(at, DateTimeFormatter.ofPattern("MMM dd yyyy"));
                result.add(new Event(description, date.toString()));
            }
        }
        this.tasks = result;
    }

    /**
     * A method to output a Numbered List, to be printed when the list command is given.
     *
     * @return String The output List, which contains a numbering system.
     */
    public String displayList() {
        String result = "";
        for (int i = 0; i < this.tasks.size(); i++) {
            if (this.tasks.get(i) == null) break;
            result += i + 1 + ". " + this.tasks.get(i) + "\n";
        }
        return result;
    }

    /**
     * A method to a List represented as a String, which can then be stored in the File
     *
     * @return String The output List, which does not contain a numbering system.
     */
    public String getList() {
        String result = "";
        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i) == null) break;
            result += tasks.get(i).toString() + "\n" ;
        }
        return result;
    }

    /**
     * A method to mark a specific task in the TaskList.
     *
     * @param i The index of the Task to be marked.
     */
    public void markTask(int i) {
        if (i >= 100) return;
        if (this.tasks.get(i) == null) return;
        this.tasks.get(i).doTask();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println("  " + this.tasks.get(i));
    }

    /**
     * A method to unmark a specific task in the TaskList.
     *
     * @param i The index of the Task to be unmarked.
     */
    public void unmarkTask(int i) {
        if (i >= 100) return;
        if (this.tasks.get(i) == null) return;
        this.tasks.get(i).undoTask();
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println("  " + this.tasks.get(i));
    }

    /**
     * A method to delete a specific task in the TaskList.
     *
     * @param i The index of the Task to be deleted.
     */
    public void deleteTask(int i) {
        if (i >= 100) return;
        if (this.tasks.get(i) == null) return;
        Task t = this.tasks.get(i);
        this.tasks.remove(i);
        System.out.println("Noted. I've removed this task:");
        System.out.println("  " + t);
        System.out.println("Now you have " + this.tasks.size() + " tasks in the list.");
    }

    /**
     * A method to add Tasks into the TaskList.
     *
     * @param t The Task to be added into the TaskList.
     */
    public void addTask(Task t) {
        this.tasks = t.printAndStoreTask(this.tasks);
    }

}
