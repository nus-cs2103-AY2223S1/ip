package ui;

import java.util.Scanner;

import exception.DorisException;
import task.Task;
import task.TaskList;

/**
 * Encapsulates a user interface that the user interacts with.
 *
 * @author Marcus Low
 */
public class Ui {
    private static Scanner sc;
    private String logo = "                                            \n"
                + "                                                      \n"
                + "    ,---,                                             \n"
                + "  .'  .' `\\                       ,--,                \n"
                + ",---.'     \\    ,---.    __  ,-.,--.'|                \n"
                + "|   |  .`\\  |  '   ,'\\ ,' ,'/ /||  |,      .--.--.    \n"
                + ":   : |  '  | /   /   |'  | |' |`--'_     /  /    '   \n"
                + "|   ' '  ;  :.   ; ,. :|  |   ,',' ,'|   |  :  /`./   \n"
                + "'   | ;  .  |'   | |: :'  :  /  '  | |   |  :  ;_     \n"
                + "|   | :  |  ''   | .; :|  | '   |  | :    \\  \\    `.  \n"
                + "'   : | /  ; |   :    |;  : |   '  : |__   `----.   \\ \n"
                + "|   | '` ,/   \\   \\  / |  , ;   |  | '.'| /  /`--'  / \n"
                + ";   :  .'      `----'   ---'    ;  :    ;'--'.     /  \n"
                + "|   ,.'                         |  ,   /   `--'---'   \n"
                + "'---'                            ---`-'               \n"
                + "                                                      ";

    /**
     * Constructs a new UI.
     */
    public Ui() {
        sc = new Scanner(System.in);
    }

    /**
     * Show the welcome message to the user.
     */
    public void showWelcome() {
        System.out.println(logo);
        System.out.println("Eh what you want?");
        System.out.println("You need help just say");
    }

    /**
     * Show the list of commands available.
     */
    public void showCommands() {
        System.out.println("Eh I can do these things la trust me I'm a woman in STEM"
                + "\n list | View all tasks you need to do la"
                + "\n todo <task> | Add something you need to do la"
                + "\n deadline <task> /by <yyyy-MM-dd hh:mm AM/PM> | Add a deadline you need to meet la"
                + "\n event <task> /at <yyyy-MM-dd hh:mm AM/PM> | Add an event you need to go for la"
                + "\n mark <index of task> | Mark the task as done la"
                + "\n unmark <index of task> | Mark the task as not done la"
                + "\n find <text to find> | Find any tasks that contains that text la"
                + "\n bye | Stop using the bot la");
    }

    /**
     * Shows the user a successful addition of a Todo task.
     *
     * @param tasks List of tasks stored.
     * @param task Task added.
     */
    public void showAddTodo(TaskList tasks, Task task) {
        System.out.println("Eh must remember to do this ah:");
        System.out.println(task.getDescription());
        System.out.println("You have " + TaskList.size() + " tasks leh better hurry up");
    }

    /**
     * Shows the user a successful addition of a Deadline task.
     *
     * @param tasks List of tasks stored.
     * @param task Task added.
     */
    public void showAddDeadline(TaskList tasks, Task task) {
        System.out.println("Eh this one due soon stop wasting time go do now:");
        System.out.println(task.getDescription());
        System.out.println("You have " + TaskList.size() + " tasks leh better hurry up");
    }

    /**
     * Shows the user a successful addition of an Event task.
     *
     * @param tasks List of tasks stored.
     * @param task Task added.
     */
    public void showAddEvent(TaskList tasks, Task task) {
        System.out.println("Oi remember to attend this ah:");
        System.out.println(task.getDescription());
        System.out.println("You have " + TaskList.size() + " tasks leh better hurry up");
    }

    /**
     * Shows the user the farewell message and ends the chat bot.
     */
    public void showBye() {
        System.out.println("Bye you annoying sia don't want talk to you anymore");
        sc.close();
    }

    /**
     * Shows the user an error.
     *
     * @param e An error to be shown.
     */
    public void showError(DorisException e) {
        System.out.println(e.toString());
    }

    /**
     * Shows the user a successful deletion of a task from the task list.
     *
     * @param tasks List of tasks.
     * @param task Task that was removed from the task list.
     */
    public void showDeleted(TaskList tasks, Task task) {
        System.out.println("Eh you don't want do this just say la:");
        System.out.println(task.getDescription());
        System.out.println("You have " + TaskList.size() + " tasks leh better hurry up");
    }

    /**
     * Shows the user a list of task to be done.
     *
     * @param tasks The task list storing the list of tasks to be done.
     */
    public void showList(TaskList tasks) {
        System.out.println("Eh faster go do these:");
        tasks.list();
    }

    /**
     * Shows the user a confirmation that a task is marked as done.
     *
     * @param task Task to be marked as done.
     */

    public void showMark(Task task) {
        System.out.println("Huh you sure you do " + task.getDescription() + " already or not?");
        System.out.println("Okay la I trust you I trust you");
    }

    /**
     * Shows the user a confirmation that a task is marked as not done.
     *
     * @param task Task to be marked as not done.
     */
    public void showUnmark(Task task) {
        System.out.println("Eh don't laze leh go do go do " + task.getDescription());
    }

    /**
     * Read the next command that the user inputs.
     *
     * @return A string representation of the next user command.
     */
    public String readCommand() {
        return sc.nextLine();
    }

    /**
     * Shows the user a list of tasks that match their query.
     *
     * @param tasks The list of tasks that match the query.
     */
    public void showFound(String tasks) {
        if (tasks.equals("")) {
            System.out.println("Eh don't have anything like that leh try again");
        } else {
            System.out.println("Eh I managed to find these tasks hurry go do");
            System.out.println(tasks);
        }
    }
}
