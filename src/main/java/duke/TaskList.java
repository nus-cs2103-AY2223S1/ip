package duke;

import java.util.ArrayList;

import duke.exception.DukeException;
import duke.exception.InvalidInputException;
import duke.task.Task;



public class TaskList {
    private ArrayList<Task> list;

    /**
     * Constructor for TaskList.
     */
    public TaskList() {
        list = new ArrayList<>();
    }

    /**
     * Prints the task list.
     */
    public void printList() {
        int i = 0;
        if (list.size() == 0) {
            System.out.println("No tasks in list, great job!");
        }
        while (i < list.size()) {
            System.out.println(i + 1 + ". " + list.get(i));
            i++;
        }
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
    public void add(Task task) {
        this.list.add(task);
        System.out.println("Got it. I've added this task:");
        System.out.println(task);
        System.out.println("Now you have " + this.list.size() + " tasks in the list");
    }

    /**
     * Removes task from the task list.
     *
     * @param num The index of the task to be removed.
     * @throws DukeException if index > size of list or index <= 0.
     */
    public void removeTask(String num) throws DukeException {
        int index = Integer.parseInt(num);
        try {
            Task task = list.get(index - 1);
            System.out.println("Ok, I have removed this task:");
            System.out.println(task);
            list.remove(index - 1);
            System.out.println("You now have " + list.size() + " tasks left in the list");
        } catch (NumberFormatException e) {
            throw new InvalidInputException(num, "delete");
        }
    }

}
