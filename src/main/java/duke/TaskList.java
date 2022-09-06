package duke;

import java.util.ArrayList;

import duke.exception.DukeException;
import duke.exception.InvalidInputException;
import duke.task.Task;


/**
 * Class to encapsulate the temporary list of items.
 */
public class TaskList {
    private ArrayList<Task> list;
    private StringBuilder replySupply = new StringBuilder();

    /**
     * Constructor for TaskList.
     */
    public TaskList() {
        list = new ArrayList<>();
    }

    /**
     * Prints the task list.
     */
    public String printList() {
        int i = 0;
        if (list.size() == 0) {
            return ("No tasks in list, great job!");
        }
        resetReplySupply();
        replySupply.append("Here are the tasks in your list:\n");
        while (i < list.size()) {
            String currLine = String.format("%d. %s \n", i + 1, list.get(i));
            replySupply.append(currLine);
            i++;
        }
        return replySupply.toString();
    }

    /**
     * Returns the task at index i of task list.
     *
     * @param i Index of task in task list.
     * @return The task in index i.
     */
    public Task get(int i) {
        return this.list.get(i);
    }

    /**
     * Returns the number of tasks in the task list.
     *
     * @return The size of the task list.
     */
    public int getsize() {
        return this.list.size();
    }


    /**
     * Adds given task into the task list.
     *
     * @param task The task to be added.
     */
    public String add(Task task, Boolean bool) {
        this.list.add(task);
        resetReplySupply();
        if (bool) {
            replySupply.append("Got it. I've added this task:\n" + task.toString() + "\n");
            replySupply.append("Now you have " + this.list.size() + " tasks in the list\n");
        }
        return replySupply.toString();
    }

    /**
     * Removes task from the task list.
     *
     * @param num The index of the task to be removed.
     * @throws DukeException If index > size of list or index <= 0.
     */
    public String removeTask(String num) throws DukeException {
        int index = Integer.parseInt(num);
        resetReplySupply();
        assert replySupply.toString().equals("") : "replySupply should be empty";
        try {
            Task task = list.get(index - 1);
            replySupply.append("Ok, I have removed this task:\n" + task.toString() + "\n");
            list.remove(index - 1);
            replySupply.append("You now have " + list.size() + " tasks left in the list\n");
            return replySupply.toString();
        } catch (NumberFormatException e) {
            throw new InvalidInputException(num, "delete");
        }
    }

    private void resetReplySupply() {
        replySupply.delete(0, replySupply.length());
    }

}
