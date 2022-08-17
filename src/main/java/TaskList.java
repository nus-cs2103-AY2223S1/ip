import java.util.ArrayList;

/**
 * This class represents the todo-list that allows users
 * to insert and remove different tasks, and also to mark or
 * unmark the tasks as completed.
 */
public class TaskList {

    /** This represents the todo-list to be populated with tasks */
    private ArrayList<Task> items = new ArrayList<>();

    /**
     * Insert a new task into the todo-list.
     * @param newItem The task to be added
     * @return Prompts indicating the result of the insertion
     * @throws ArrayIndexOutOfBoundsException
     */
    public String insertItem(String newItem) 
            throws ArrayIndexOutOfBoundsException {

        if (newItem.split(" ", 2).length < 2 && newItem.equals("todo")) {
            return "☹ OOPS!!! The description of a todo cannot be empty.";
        } else if (newItem.split(" ", 2).length < 2 && newItem.equals("deadline")) {
            return "☹ OOPS!!! The description of a deadline cannot be empty.";
        } else if (newItem.split(" ", 2).length < 2 && newItem.equals("event")) {
            return "☹ OOPS!!! The description of a event cannot be empty.";
        } else if (newItem.split(" ", 2).length < 2) {
            return "☹ OOPS!!! I'm sorry, but I don't know what that means :-(";
        }

        String taskType = newItem.split(" ", 2)[0].trim();
        String taskDetails = newItem.split(" ", 2)[1].trim();

        if (taskType.equalsIgnoreCase("deadline")) {
            if (taskDetails.split("/by").length < 2) {
                return "☹ Please specify the deadline!";
            }
            items.add(
                new DeadlineTask(
                    taskDetails.split("/by")[0].trim(), 
                    taskDetails.split("/by")[1].trim()));
        } else if (taskType.equalsIgnoreCase("event")) {
            if (taskDetails.split("/at").length < 2) {
                return "☹ Please specify the period!";
            }
            items.add(
                new EventTask(
                    taskDetails.split("/at")[0].trim(), 
                    taskDetails.split("/at")[1].trim()));
        } else if (taskType.equalsIgnoreCase("todo")) {
            items.add(new ToDoTask(taskDetails));
        } else {
            return "☹ OOPS!!! I'm sorry, but I don't know what that means :-(";
        }

        return String.format(
            "Got it. I've added this task:\n %s\nNow you have %d tasks in the list.",
            items.get( items.size() - 1).toString(),
            items.size());
    }

    /**
     * Mark an item in the list as done.
     * @param index The index of the task to be marked as done
     * @return Prompt indicating the result of the marking
     * @throws ArrayIndexOutOfBoundsException
     */
    public String markItem(int index) 
            throws ArrayIndexOutOfBoundsException {
        index -= 1;
        if (index >=  items.size()) {
            throw new ArrayIndexOutOfBoundsException();
        }
        return items.get(index).markTask();
    }

    /**
     * Unmark an item in the list.
     * @param index The index of the task to be unmarked as done
     * @return Prompt indicating the result of the unmarking
     * @throws ArrayIndexOutOfBoundsException
     */
    public String unmarkItem(int index) 
            throws ArrayIndexOutOfBoundsException {
        index -= 1;
        if (index >=  items.size()) {
            throw new ArrayIndexOutOfBoundsException();
        }
        return items.get(index).unmarkTask();
    }

    public String deleteItem(int index) 
            throws ArrayIndexOutOfBoundsException {
        index -= 1;
        if (index >=  items.size()) {
            throw new ArrayIndexOutOfBoundsException();
        }
        return String.format(
            "Noted. I've removed this task:\n %s\nNow you have %d tasks in the list.",
            items.remove(index).toString(),
            items.size());
    }

    /**
     * Print the output in customised format.
     * @param list The list to print
     * @return String representation of the todo-list
     */
    @Override
    public String toString() {
        String res = ("Here are the tasks in your list:");
        for (int i = 0; i <  items.size(); i++) {
            if (items.get(i) == null) {
                break;
            }
            res += String.format("\n %d.%s", i + 1, items.get(i).toString());
        }
        return res;
    }
}
