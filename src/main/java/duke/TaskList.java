package duke;

import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public void mark(int num, Ui ui) {
        tasks.get(num - 1).mark();
        ui.printMessage("OK, I've marked this task as done:");
        ui.printMessage(tasks.get(num - 1).toString());
    }

    public void unmark(int num, Ui ui) {
        tasks.get(num - 1).unMark();
        ui.printMessage("OK, I've marked this task as not done yet:");
        ui.printMessage(tasks.get(num - 1).toString());
    }

    public void delete(int num, Ui ui) {
        Task removedTask = tasks.get(num - 1);
        tasks.remove(num - 1);
        ui.printMessage("Noted. I've removed this task:" + "\n" + removedTask.toString()
                + "Now you have " + tasks.size() + " tasks in the list.");
    }


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

    public int size() {
        return tasks.size();
    }

    public Task get(int n) {
        return tasks.get(n);
    }

    public void remove(int n) {
        tasks.remove(n);
    }

    public ArrayList<Task> getTasks() {
        return this.tasks;
    }
}
