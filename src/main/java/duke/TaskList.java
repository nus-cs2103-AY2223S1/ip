package duke;

import duke.DukeException;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class TaskList {
    ArrayList<Task> taskList;

    public TaskList(ArrayList<Task> al) {
        this.taskList = al;
    }

    public TaskList() {
        this.taskList = new ArrayList<Task>();
    }

    public ArrayList<Task> list() {
        return taskList;
    }

    public String markDone(String task) {
        for (int i = 0; i < this.taskList.size(); i++) {
            if (this.taskList.get(i).getTask().equals(task)) {
                this.taskList.get(i).markDone();
                return " okie! " + task + " is done ~\n [X] " + task + "\n";
            }
        }
        return task + " not found :(\n";
    }

    public String unmarkDone(String task) {
        for (int i = 0; i < this.taskList.size(); i++) {
            if (this.taskList.get(i).getTask().equals(task)) {
                this.taskList.get(i).unmarkDone();
                return " owh ;< so you haven't done " + task + ". unmarked ~\n [ ] " + task + "\n";
            }
        }
        return task + " not found :(\n";
    }

    public void deleteTask(String userInput) throws DukeException {
        String line = " _______________________________________ \n";
        int index = Integer.parseInt(userInput.trim());
        if (index <= 0 || index > this.taskList.size()) {
            throw new DukeException("sowwie this item is not found. enter a valid index number from list please!");
        }
        Task taskRemoved = this.taskList.get(index - 1);
        this.taskList.remove(index - 1);
        System.out.println(line + " okie! i've removed: \n " + taskRemoved + "\n now you have " + this.taskList.size() + " task(s) in your list!\n" + line);
    }


    public void addTask(String userInput) throws DukeException {
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
        System.out.println(line + " okie! i've added: \n " + t + "\n now you have " + this.taskList.size() + " task(s) in your list!\n" + line);
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
