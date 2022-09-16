package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

/**
 * Task contains an ArrayList of the Tasks to be done, as well
 * as the behaviours that can change the Tasks within the ArrayList.
 *
 * @author Alvin Jiang Min Jun
 * @version v0.2
 */
public class TaskList {

    private ArrayList<Task> tasks;

    /**
     * Creates an instance of a TaskList Object.
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
     * Outputs a Numbered List, to be printed when the list command is given.
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
     * Obtains a List represented as a String, which can then be stored in the File
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
     * Marks a specific task in the TaskList.
     *
     * @param i The index of the Task to be marked.
     * @return String the resulting String after the Task has been marked.
     */
    public String markTask(int i) {
        if (i >= 100) return null;
        if (this.tasks.get(i) == null) return null;
        this.tasks.get(i).doTask();
        return "Nice! I've marked this task as done:\n"
                + "  " + this.tasks.get(i);
    }

    /**
     * Umarks a specific task in the TaskList.
     *
     * @param i The index of the Task to be unmarked.
     * @return String the resulting String after the Task has been unmarked.
     */
    public String unmarkTask(int i) {
        if (i >= 100) return null;
        if (this.tasks.get(i) == null) return null;
        this.tasks.get(i).undoTask();
        return "OK, I've marked this task as not done yet:\n"
                + "  " + this.tasks.get(i);
    }

    /**
     * Deletes a specific task in the TaskList.
     *
     * @param i The index of the Task to be deleted.
     * @return String the resulting String after the Task has been deleted.
     */
    public String deleteTask(int i) {
        if (i >= 100) return null;
        if (this.tasks.get(i) == null) return null;
        Task t = this.tasks.get(i);
        this.tasks.remove(i);
        return "Noted. I've removed this task:\n"
                + "  " + t + "\n"
                + "Now you have " + this.tasks.size() + " tasks in the list.";
    }

    /**
     * Adds a Task into the TaskList.
     *
     * @param t The Task to be added into the TaskList.
     * @return String the resulting String after the Task has been added.
     */
    public String addTask(Task t) {
        return t.printAndStoreTask(this.tasks);
    }

    /**
     * Find Tasks from within the TaskList.
     *
     * @param keyword The keyword to search for.
     * @return String the resulting String displayed when the Task is found,
     * or it does not exist.
     */
    public String findTask(String keyword) {
        String result = "";
        int counter = 1;
        for (int i = 0; i < this.tasks.size(); i++) {
            if (this.tasks.get(i).match(keyword)) {
                result += counter + ". " + this.tasks.get(i) + "\n";
                counter++;
            }
        }
        if (result.isEmpty()) {
            return "Sorry! None of the tasks match " + "\"" + keyword + "\"";
        } else {
            return "Here are the matching tasks in your list:\n" + result;
        }
    }

    /**
     * Checks if the timing of an event clashes with that of another.
     *
     * @param dateTime The String containing the date and time that an event is at.
     * @throws DukeException Thrown when there is a clash between the dates of the Events.
     */
    public void checkClash(String dateTime) throws DukeException {
        for (Task t : this.tasks) {
            if (t.sameTime(dateTime)) {
                throw new DukeException("Sorry, there is a clash in the Events!");
            }
        }
    }

}
