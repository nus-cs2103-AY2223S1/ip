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
//    private String logo = "                                            \n"
//                + "                                                      \n"
//                + "    ,---,                                             \n"
//                + "  .'  .' `\\                       ,--,                \n"
//                + ",---.'     \\    ,---.    __  ,-.,--.'|                \n"
//                + "|   |  .`\\  |  '   ,'\\ ,' ,'/ /||  |,      .--.--.    \n"
//                + ":   : |  '  | /   /   |'  | |' |`--'_     /  /    '   \n"
//                + "|   ' '  ;  :.   ; ,. :|  |   ,',' ,'|   |  :  /`./   \n"
//                + "'   | ;  .  |'   | |: :'  :  /  '  | |   |  :  ;_     \n"
//                + "|   | :  |  ''   | .; :|  | '   |  | :    \\  \\    `.  \n"
//                + "'   : | /  ; |   :    |;  : |   '  : |__   `----.   \\ \n"
//                + "|   | '` ,/   \\   \\  / |  , ;   |  | '.'| /  /`--'  / \n"
//                + ";   :  .'      `----'   ---'    ;  :    ;'--'.     /  \n"
//                + "|   ,.'                         |  ,   /   `--'---'   \n"
//                + "'---'                            ---`-'               \n"
//                + "                                                      ";

    /**
     * Constructs a new UI.
     */
    public Ui() {
        sc = new Scanner(System.in);
    }

    /**
     * Show the welcome message to the user.
     */
    public String showWelcome() {
        return "Eh what you want? \nYou need help just say";
    }

    /**
     * Show the list of commands available.
     */
    public String showCommands() {
        return "Eh I can do these things la trust me I'm a woman in STEM"
                + "\nlist | View all tasks you need to do la"
                + "\ntodo <task> | Add something you need to do la"
                + "\ndeadline <task> /by <yyyy-MM-dd hh:mm AM/PM> | Add a deadline you need to meet la"
                + "\nevent <task> /at <yyyy-MM-dd hh:mm AM/PM> | Add an event you need to go for la"
                + "\nmark <index of task> | Mark the task as done la"
                + "\nunmark <index of task> | Mark the task as not done la"
                + "\nfind <text to find> | Find any tasks that contains that text la"
                + "\nbye | Stop using the bot la";
    }

    /**
     * Shows the user a successful addition of a Todo task.
     *
     * @param tasks List of tasks stored.
     * @param task Task added.
     */
    public String showAddTodo(TaskList tasks, Task task) {
        return "Eh remember to do this ah:\n" + task.getDescription()
                + "\nYou have " + TaskList.size() + " tasks leh better hurry up";
    }

    /**
     * Shows the user a successful addition of a Deadline task.
     *
     * @param tasks List of tasks stored.
     * @param task Task added.
     */
    public String showAddDeadline(TaskList tasks, Task task) {
        return "Eh this one due soon stop wasting time go do now:\n" + task.getDescription()
                + "\nYou have " + TaskList.size() + " tasks leh better hurry up";
    }

    /**
     * Shows the user a successful addition of an Event task.
     *
     * @param tasks List of tasks stored.
     * @param task Task added.
     */
    public String showAddEvent(TaskList tasks, Task task) {
        return "Oi remember to attend this ah:\n" + task.getDescription()
                + "\nYou have " + TaskList.size() + " tasks leh better hurry up";
    }

    /**
     * Shows the user the farewell message and ends the chatbot.
     */
    public String showBye() {
        sc.close();
        return "Bye you annoying sia don't want talk to you anymore";
    }

    /**
     * Shows the user an error.
     *
     * @param e An error to be shown.
     * @return
     */
    public String showError(DorisException e) {
        return e.toString();
    }

    /**
     * Shows the user a successful deletion of a task from the task list.
     *
     * @param tasks List of tasks.
     * @param task Task that was removed from the task list.
     */
    public String showDeleted(TaskList tasks, Task task) {
        return "Eh you don't want do this just say la:\n" + task.getDescription()
                + "\nYou have " + TaskList.size() + " tasks leh better hurry up";
    }

    /**
     * Shows the user a list of task to be done.
     *
     * @param tasks The task list storing the list of tasks to be done.
     */
    public String showList(TaskList tasks) {
        return "Eh faster go do these:\n" + tasks.list();
    }

    /**
     * Shows the user a confirmation that a task is marked as done.
     *
     * @param task Task to be marked as done.
     */

    public String showMark(Task task) {
        return "Huh you sure you do " + task.getDescription() + " already or not?\n Okay la I trust you I trust you";
    }

    /**
     * Shows the user a confirmation that a task is marked as not done.
     *
     * @param task Task to be marked as not done.
     */
    public String showUnmark(Task task) {
        return "Eh don't laze leh go do go do " + task.getDescription();
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
    public String showFound(String tasks) {
        if (tasks.equals("")) {
            return "Eh don't have anything like that leh try again";
        } else {
            return "Eh I managed to find these tasks hurry go do\n" + tasks;
        }
    }
}
