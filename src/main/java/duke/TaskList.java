package duke;

import java.util.ArrayList;
import java.util.Scanner;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

/**
 * List of Tasks recorded by the Duke program.
 * CS2103T iP
 * AY22/23 Semester 1
 *
 * @author Perry Wong
 */
public class TaskList {

    private final ArrayList<Task> taskArrayList;

    /**
     * Constructs a TaskList when there is no previously saved data.
     */
    public TaskList() {
        this.taskArrayList = new ArrayList<>(100);
    }

    /**
     * Constructs a TaskList based on previously saved data.
     */
    public TaskList(ArrayList<Task> taskArrayList) {
        this.taskArrayList = taskArrayList;
    }

    /**
     * Returns the list of tasks that are being kept track of.
     *
     * @return A list of tasks.
     */
    public String printList() {
        ArrayList<Task> list = this.taskArrayList;
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
            String index = i + 1 + ". ";
            String taskDescription = list.get(i).toString() + "\n";
            String fullDescription = index + taskDescription;
            str.append(fullDescription);
        }
        return str.toString();
    }

    /**
     * Adds a new task to the list.
     *
     * @param t New task to be added to the list.
     */
    public void add(Task t) {
        boolean isTodo = t instanceof Todo;
        boolean isDeadline = t instanceof Deadline;
        boolean isEvent = t instanceof Event;
        assert (isTodo || isDeadline || isEvent) : "Task to be added must be either Todo, Deadline or Event.";

        this.taskArrayList.add(t);
    }

    /**
     * Deletes a task from the list.
     *
     * @param i Index of the task in the list to be deleted.
     */
    public void delete(int i) {
        assert (i >= 1) : "Given index must be at least 1.";
        assert (i <= this.taskArrayList.size()) : "Index cannot exceed length of list";

        this.taskArrayList.remove(i - 1);
    }

    /**
     * Marks a task from the list as done.
     *
     * @param i Index of the task in the list to be marked.
     */
    public void mark(int i) {
        assert (i >= 1) : "Given index must be at least 1.";
        assert (i <= this.taskArrayList.size()) : "Index cannot exceed length of list";

        Task t = this.taskArrayList.get(i - 1);
        t.mark();
    }

    /**
     * Unmarks a task from the list as done.
     *
     * @param i Index of the task in the list to be unmarked.
     */
    public void unmark(int i) {
        assert (i >= 1) : "Given index must be at least 1.";
        assert (i <= this.taskArrayList.size()) : "Index cannot exceed length of list";

        Task t = this.taskArrayList.get(i - 1);
        t.unmark();
    }

    /**
     * Returns the list of tasks.
     *
     * @return List of tasks.
     */
    public ArrayList<Task> getTaskArrayList() {
        return this.taskArrayList;
    }

    /**
     * Finds tasks in the list that contains the specified input String.
     *
     * @param s Input string to be checked against.
     * @return List of tasks matching the input string.
     */
    public String find(String s) {
        int counter = 1;
        StringBuilder str = new StringBuilder();
        for (Task t : this.taskArrayList) {
            String description = t.toString();
            String[] descriptionArr = description.split(" ");
            for (int i = 0; i < descriptionArr.length; i++) {
                boolean hasWord = descriptionArr[i].equals(s);
                if (hasWord) {
                    String index = counter + ". ";
                    String taskDescription = t + "\n";
                    String fullDescription = index + taskDescription;
                    str.append(fullDescription);
                    counter++;
                }
            }
        }
        return str.toString();
    }

    /**
     * Returns a list of tasks that contains the input string.
     *
     * @param s Input string to match.
     * @return List of tasks with partially/fully matching strings.
     */
    public String search(String s) {
        int counter = 1;
        StringBuilder str = new StringBuilder();
        for (Task t : this.taskArrayList) {
            Scanner temp = new Scanner(t.toString());
            String filter = "\\[|\\]\\s*|by:\\s*|at:\\s*|\\s*\\(|\\s*\\)";
            temp.useDelimiter(filter);

            temp.next();
            temp.next();
            temp.next();
            String description = temp.next();
            temp.close();

            boolean hasWord = t.toString().contains(s) || s.contains(description);

            if (hasWord) {
                String index = counter + ". ";
                String taskDescription = t + "\n";
                String fullDescription = index + taskDescription;
                str.append(fullDescription);
                counter++;
            }
        }
        return str.toString();
    }

}
