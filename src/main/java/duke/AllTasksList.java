package duke;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

/**
 * All Tasks List class used to store the tasks created by the user.
 *
 * @author Cui Shen Yi
 * @version CS2103T AY22/23 Semester 1
 */
public class AllTasksList implements Serializable {

    /**
     * ArrayList used to store all the tasks created by the user
     */
    private ArrayList<Task> allTasks = new ArrayList<>();

    /**
     * Method used to mark a task at index as complete
     *
     * @param index the index of task to mark as complete
     * @throws DukeException
     */
    public String markTask(int index) throws DukeException {
        try {
            return this.allTasks.get(index).markAsDone();
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Error: duke.Task not found");
        }
    }

    /**
     * Method used to mark a task at index as incomplete
     *
     * @param index the index of task to mark as incomplete
     * @throws DukeException
     */
    public String unMarkTask(int index) throws DukeException {
        try {
            return this.allTasks.get(index).unmark();
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Error: duke.Task not found");
        }
    }

    /**
     * Method used to mark a task at index as incomplete
     *
     * @param index the index of task to mark as incomplete
     * @throws DukeException
     */
    public String delete(int index) throws DukeException {
        try {
            String output = "\nNoted. I've removed this task:" + this.allTasks.get(index) + "\n";
            this.allTasks.remove(index);
            output += this.getSize();
            return output;
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Error: duke.Task not found");
        }
    }

    /**
     * Method used to add a new task
     *
     * @param task the task to be added
     */
    public String addTask(Task task) {
        String output = "\nGot it. I've added this task:" + task + "\n";
        this.allTasks.add(task);
        output += this.getSize();
        return output;
    }

    /**
     * Method used to mark to list all available tasks
     */
    public String listAllTasks() {
        String output = "";
        for (int i = 0; i < this.allTasks.size(); i++) {
            output += "\n" + (i + 1) + ". " + this.allTasks.get(i).toString();
        }
        return output;
    }

    /**
     * Method used to list all deadlines that have the same due date.
     *
     * @param dueDate  used to list all deadlines with this due date
     * @return a string of all deadlines with this due date
     * @throws DukeException if the due date is malformed
     */
    public String listSchedule(String dueDate) throws DukeException {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            LocalDateTime date = LocalDateTime.parse(dueDate + " 00:00", formatter);
            String output = "";
            for (int i = 0; i < this.allTasks.size(); i++) {
                Boolean isDeadline = this.allTasks.get(i) instanceof Deadline;
                if (!isDeadline) {
                    continue;
                }
                Deadline dl = (Deadline) this.allTasks.get(i);
                Boolean isSameDate = dl.isSameDay(date);
                if (!isSameDate) {
                    continue;
                }

                output += "\n" + (i + 1) + ". " + this.allTasks.get(i).toString();
            }
            return output;
        } catch (DateTimeParseException e) {
            throw new DukeException("Date Format Incorrect: yyyy-MM-dd");
        }
    }

    /**
     * Method used to output to the user the number of items left on the list
     */
    private String getSize() {
        return "Now you have " + this.allTasks.size() + " tasks in the list.";
    }

    /**
     * Method used to find a specified item and return a list of items to the user
     *
     * @param searchItem  the item to search for
     */
    public String find(String searchItem) {
        ArrayList<Task> allTaskCopy = new ArrayList<>();
        allTaskCopy.addAll(this.allTasks);
        allTaskCopy.removeIf(s -> s.toString().contains(searchItem));

        String output = "";
        for (int i = 0; i < allTaskCopy.size(); i++) {
            output += "\n" + (i + 1) + ". " + allTaskCopy.get(i).toString();
        }
        return output;
    }
}
