package Duke;

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
     * prints list of tasks
     *
     * @param list that contains all tasks
     */
    public String displayTask() {
        ArrayList<Task> list = taskList.listTasks();
        String t = "Here are the tasks in your list:";
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
     * Mark a task as not done using the task index
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
     * Add a task to list and print the task out
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
     * Add a deadline task to list and print the task out
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
     * Add an event task to list and print the task out
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
     * Remove a task from list
     *
     * @param input read task index
     */
    public String displayDelete(String input) {
        String s = "Noted. I've removed this task:";
        int index = Integer.parseInt(input.substring(7)) - 1;
        Task task = taskList.get(index);
        taskList.remove(index);
        return s + '\n' + task.toString();
        //ui.printSummary(taskList.size());
    }

    /**
     * Search for task that matches the date inputted
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
     * Search for task that matches the name inputted
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

    public String enterText() {
       return "Sorry, please enter text again :(";
    }
}
