import java.util.ArrayList;

public class MarkableList {

    private ArrayList<Task> items = new ArrayList<>();

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

    public String markItem(int index) 
            throws ArrayIndexOutOfBoundsException {
        index -= 1;
        if (index >=  items.size()) {
            throw new ArrayIndexOutOfBoundsException();
        }
        return items.get(index).markTask();
    }

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
