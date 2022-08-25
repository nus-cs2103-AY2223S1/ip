package duke;

import java.util.ArrayList;

import duke.exception.DukeException;
import duke.exception.InvalidInputException;
import duke.task.Task;



public class TaskList {
    private ArrayList<Task> list;

    public TaskList() {
        list = new ArrayList<>();
    }

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

    public Task get(int i) {
        return this.list.get(i);
    }

    public int getsize() {
        return this.list.size();
    }

    public void add(Task task) {
        this.list.add(task);
        System.out.println("Got it. I've added this task:");
        System.out.println(task);
        System.out.println("Now you have " + this.list.size() + " tasks in the list");
    }

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
