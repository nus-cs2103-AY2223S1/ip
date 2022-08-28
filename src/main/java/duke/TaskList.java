package duke;

import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks;

    /**
     * The constructor of the class
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Marks a task as done
     *
     * @param num Specifies which task to mark
     * @param ui  An object to print output
     */
    public void mark(int num, Ui ui) {
        tasks.get(num - 1).mark();
        ui.printMessage("OK, I've marked this task as done:");
        ui.printMessage(tasks.get(num - 1).toString());
    }

    /**
     * Unmarks a task
     *
     * @param num Specifies which task to unmark
     * @param ui  An object to print output
     */
    public void unmark(int num, Ui ui) {
        tasks.get(num - 1).unMark();
        ui.printMessage("OK, I've marked this task as not done yet:");
        ui.printMessage(tasks.get(num - 1).toString());
    }

    /**
     * Deletes a task
     *
     * @param num Specifies which item to delete
     * @param ui  An object to print output
     */
    public void delete(int num, Ui ui) {
        Task removedTask = tasks.get(num - 1);
        tasks.remove(num - 1);
        ui.printMessage("Noted. I've removed this task:" + "\n" + removedTask.toString()
                + "Now you have " + tasks.size() + " tasks in the list.");
    }

    /**
     * Adds a task
     *
     * @param item The String that needs to be parsed and add to the list
     * @throws DukeException Throws an exception
     */
    public void addATask(String item) throws DukeException {
        String[] arr = item.split(" ", 2);
        String type = arr[0];
        switch (type) {
            case "todo":
                if (arr.length == 1) {
                    throw new DukeException(" OOPS!!! The description of a todo cannot be empty.");
                }
                String todoName = arr[1].trim();
                Task newTodo = new Todo(todoName);
                tasks.add(newTodo);
                break;
            case "deadline":
                String[] deadlineArr = arr[1].split("/");
                String deadlineName = deadlineArr[0];
                String dl = deadlineArr[1];
                Task newDeadline = new Deadline(deadlineName, dl);
                tasks.add(newDeadline);
                break;
            case "event":
                String[] eventArr = arr[1].split("/");
                String eventName = eventArr[0];
                String eventTime = eventArr[1];
                Task newEvent = new Event(eventName, eventTime);
                tasks.add(newEvent);
                break;
            default:
                DukeException e = new DukeException(" OOPS!!! I'm sorry, but I don't know what that means :-(");
                throw e;
        }

        Ui.printLine();
        int numOfTasks = tasks.size();
        System.out.println("Got it. I've added this task:" + "\n" + tasks.get(numOfTasks - 1).toString() +
                "\n" + "Now you have " + numOfTasks + " tasks in the list.");
        Ui.printLine();
    }

    public void find(String str, Ui ui) {
        //ArrayList<Task> result = new ArrayList<>();
        String result = "";
        for (Task task : tasks) {
            if (task.name.contains(str)) {
                result += task.toString() + "\n";
            }
        }
        ui.printMessage(result);
    }

    /**
     * Gets the size of the tasks
     *
     * @return the size of the task
     */
    public int size() {
        return tasks.size();
    }

    /**
     * Gets a specific task
     *
     * @param n The index of task wanted
     * @return The task wanted
     */
    public Task get(int n) {
        return tasks.get(n);
    }

    /**
     * Removes a specific task
     *
     * @param n The index of task to be removed
     */
    public void remove(int n) {
        tasks.remove(n);
    }

    /**
     * Gets an arraylist of tasks
     *
     * @return an arraylist of tasks
     */
    public ArrayList<Task> getTasks() {
        return this.tasks;
    }
}
