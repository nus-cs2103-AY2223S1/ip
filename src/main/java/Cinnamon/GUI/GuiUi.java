package Cinnamon.GUI;
import Cinnamon.Handler.Ui;
import Cinnamon.Tasks.Deadline;
import Cinnamon.Tasks.TaskList;
import Cinnamon.Tasks.Event;
import Cinnamon.Tasks.Todo;
import Cinnamon.Tasks.Task;

import java.util.ArrayList;

/**
 * Class that deals with user interactions
 */
public class GuiUi {
    private final TaskList taskList;
    private final Ui ui;

    /**
     * Constructor of command that initialised ui and tasklist
     */
    public GuiUi() {
        this.ui = new Ui();
        this.taskList = new TaskList();
    }

    /**
     * Prints greeting message
     */
    public String greet() {
        return "Hello!, I'm Yiye.\nWhat can I do for you? ◠‿◠";
    }

    /**
     * Prints when input is empty
     */
    public String bye() {
        return "Bye! Hope to see you again soon!";
    }

    /**
     * prints list of tasks that has been added
     */
    public String displayTask() {
        ArrayList<Task> list = taskList.listTasks();
        if (list.isEmpty()) {
            return "There are no tasks in your taskList ~";
        }
        String t = "Here are the tasks in your taskList:";
        String s = "";
        for (int i = 0; i < list.size(); i++) {
            int index = i + 1;
            s = "" + index + "." + list.get(i).toString();
        }
        return t + "\n" + s;
    }

    public String displayMarkDone(String input) {
        String s = "Nice! I have marked this task as done:\n";
        int j = Integer.parseInt(input.substring(5)) - 1;
        Task task = taskList.get(j);
        task.markAsDone();
        return s + task.toString();
    }

    /**
     * Marks a task as not done using the task index
     *
     * @param input read task index
     */
    public String displayUnmark(String input) {
       String s = "This task is marked as not done:\n";
        int j = Integer.parseInt(input.substring(7)) - 1;
        Task task = taskList.get(j);
        task.markAsNotDone();
        return s + task.toString();
    }

    /**
     * Adds a task to list and print the task out
     *
     * @param input read name of task
     */
    public String displayTodo(String input) {
        String s = "Got it, this task is added in your list:\n";
        Task todo = new Todo(input.substring(5));
        taskList.add(todo);
        return s + todo.toString();
        //ui.printSummary(taskList.size());
    }

    /**
     * Adds a deadline task to list and print the task out
     *
     * @param input read name, time and date of task
     */
    public String displayDeadline(String input) {
        String s = "Got it, this task is added in your list:\n";
        String[] parts = input.split(" ");
        String date = parts[parts.length - 2];
        String time = parts[parts.length - 1];
        Task dl = new Deadline(input.substring(9, input.indexOf("/") - 1), date, time);
        taskList.add(dl);
        return s + dl.toString();
        //ui.printSummary(taskList.size());
    }

    /**
     * Adds an event task to list and print the task out
     *
     * @param input read name, time and date of task
     */
    public String displayEvent(String input) {
        String s = "Got it, this task is added in your list:";
        String[] parts = input.split(" ");
        String at = parts[parts.length - 3];
        String date = parts[parts.length - 2];
        String time = parts[parts.length - 1];
        Task event = new Event(input.substring(6, input.indexOf("/") - 1), at, date, time);
        taskList.add(event);
        return s + '\n' + event.toString();
        //ui.printSummary(taskList.size());
    }

    /**
     * Removes a task from list
     *
     * @param input read task index
     */
    public String displayDelete(String input) {
        String s = "Noted. I've deleted this task:";
        int index = Integer.parseInt(input.substring(7)) - 1;
        Task task = taskList.get(index);
        taskList.remove(index);
        return s + '\n' + task.toString();
        //ui.printSummary(taskList.size());
    }

    /**
     * Searches for task that matches the date inputted
     *
     * @param input read date
     */
    public String displaySearchDate(String input) {
        ArrayList<Task> matched = new ArrayList<>();
        String s = "";
        for (Task t : taskList.listTasks()) {
            String str = t.toString();
            if (str.contains(input)) {
                matched.add(t);
            }
        }
        if (matched.isEmpty()) {
            s = "No tasks on this date, check you format! --> MMM(eg. Apr) dd yyy";
            return s;
        } else {
            String t = "Here are the matching tasks:\n";
            for (int i = 0; i < matched.size(); i++) {
                int index = i + 1;
                s = "" + index + "." + matched.get(i).toString();
            }
            return t + s;
        }
    }

    /**
     * Searches for task that matches the name inputted
     *
     * @param input read name
     */
    public String displaySearchName(String input) {
        String[] parts = input.split(" ", 2);
        String s = "";
        ArrayList<Task> matched = new ArrayList<>();
        for (Task t : taskList.listTasks()) {
            String str = t.toString();
            if (str.contains(parts[1])) {
                matched.add(t);
            }
        }
        if (matched.isEmpty()) {
            s = "No matching task of matching name";
            return s;
        } else {
            String t = "Here are the matching tasks:\n";
            for (int i = 0; i < matched.size(); i++) {
                int index = i + 1;
                s = "" + index + "." + matched.get(i).toString();
            }
            return t + s;
        }
    }

    /**
     * Tags a task
     *
     * @param input by user
     * @return task with tag
     */
    public String tagTask(String input) {
        String s = "Your task is tagged:\n";
        int index = Integer.parseInt(input.substring(4, 5))- 1;
        Task task = taskList.get(index);
        task.tagged();
        String[] parts = input.split(" ", 3);
        task.setTag(parts[2]);
        return  s + task.toString() + " " + parts[2];
    }

    /**
     * Prints a task with its tag
     *
     * @param input by user
     * @return task with tag
     */
    public String printTaskTag(String input) {
        // input format = print tag 1(index)
        int index = Integer.parseInt(input.substring(10)) - 1;
        Task task = taskList.get(index);
        if (task.isTagged()) {
            String s = task.toString() + " ";
            s += task.getTag();
            return "Your task is tagged:\n" + s;
        } else {
            return "The task is not tagged";
        }
    }

    /**
     * Searches the task list and return tasks of matching tag
     *
     * @param input command by user
     * @return matching tasks
     */
    public String searchTag(String input) {
        String s = "";
        ArrayList<Task> matched = new ArrayList<>();
        for (Task t : taskList.listTasks()) {
            if (t.getTag().equals(input)) {
                matched.add(t);
            }
        }
        if (matched.isEmpty()) {
            s = "No matching task of given tag";
            return s;
        } else {
            String t = "Here are the matching tasks:\n";
            for (int i = 0; i < matched.size(); i++) {
                int index = i + 1;
                s = "" + index + "." + matched.get(i).toString();
            }
            return t + s;
        }
    }

    /**
     * Removes tag for the task
     *
     * @param input by user
     * @return task after removing tags
     */
    public String removeTag(String input) {
        String[] parts = input.split(" ", 3);
        int index = Integer.parseInt(parts[2]) - 1;
        Task task = taskList.get(index);
        task.setTag("");
        String s = "Tag is removed. \n";
        return s + task.toString();
    }

    public String enterText() {
       return "Sorry, please enter text again :(";
    }
}
