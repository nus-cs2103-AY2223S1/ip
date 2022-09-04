package duke;

import duke.DukeException;

import java.lang.reflect.Array;
import java.util.ArrayList;

/*
Contains list of tasks and operations to edit them
*/
public class TaskList {
    ArrayList<Task> taskList;

    public TaskList(ArrayList<Task> al) {
        this.taskList = al;
    }

    public TaskList() {
        this.taskList = new ArrayList<Task>();
    }

    public ArrayList<Task> getTaskList() {
        return taskList;
    }

    /**
     * Marks a task as done if it is present.
     *
     * @param task Name of the task to be marked as done.
     * @return The success or unsuccessful message to be shown.
     */
    public String markDone(String task) {
        for (int i = 0; i < this.taskList.size(); i++) {
            if (this.taskList.get(i).getTask().equals(task)) {
                this.taskList.get(i).markDone();
                return " okie! " + task + " is done ~\n [X] " + task + "\n";
            }
        }
        return task + " not found :(\n";
    }

    /**
     * Marks a task as undone if it is present.
     *
     * @param task Name of the task to be marked as undone.
     * @return The success or unsuccessful message to be shown.
     */
    public String unmarkDone(String task) {
        for (int i = 0; i < this.taskList.size(); i++) {
            if (this.taskList.get(i).getTask().equals(task)) {
                this.taskList.get(i).unmarkDone();
                return " owh ;< so you haven't done " + task + ". unmarked ~\n [ ] " + task + "\n";
            }
        }
        return task + " not found :(\n";
    }

    /**
     * Deletes a task if it is present.
     *
     * @param userInput Index number of the task to be removed from list.
     * @throws DukeException If task does not exist.
     */
    public String deleteTask(String userInput) throws DukeException {
        String line = " _______________________________________ \n";
        int index = Integer.parseInt(userInput.trim());
        if (index <= 0 || index > this.taskList.size()) {
            throw new DukeException("sowwie this item is not found. enter a valid index number from list please!");
        }
        Task taskRemoved = this.taskList.get(index - 1);
        this.taskList.remove(index - 1);
        return "okie! i've removed: \n " + taskRemoved +
                "\n now you have " + this.taskList.size() + " task(s) in your list!";
    }

    /**
     * Adds a task to the task list.
     *
     * @param userInput Complete user input.
     * @throws DukeException If task name does not exist.
     */
    public String addTask(String userInput) throws DukeException {
        String line = " _______________________________________ \n";
        if (!userInput.contains(" ") || userInput.substring(userInput.indexOf(" ")).trim().isEmpty()) {
            throw new DukeException("the description of a task cannot be empty.");
        }
        /* Task t = new Task(userInput.substring(userInput.indexOf(" ") + 1),
                userInput.substring(0, userInput.indexOf(" ")).toUpperCase(), false); */
        /* Task t = new Task(Parser.parseUserInput(userInput),
                Parser.getTaskName(userInput), Parser.parseUserDate(userInput), false); */
        Task t = new Task(userInput.substring(userInput.indexOf(" ") + 1),
                userInput.substring(0, userInput.indexOf(" ")).toUpperCase(), false);
        this.taskList.add(t);
        return "okie! i've added: \n " + t +
                "\n now you have " + this.taskList.size() + " task(s) in your list!";
    }

    public ArrayList<Task> find(String keyword) {
        ArrayList<Task> foundTasks = new ArrayList<>();
        for (int i = 0; i < taskList.size(); i++) {
            if (taskList.get(i).getTask().contains(keyword)) {
                foundTasks.add(taskList.get(i));
            }
        }
        return foundTasks;
    }

}
