package iana.ui;

import java.util.Scanner;

import iana.tasks.Task;
import iana.tasks.TaskList;

/**
 * User interface that will interact with user.
 */
public class Ui {
    private Scanner sc;

    /**
     * Constructor for Ui class to initialise system scanner.
     */
    public Ui() {
        this.sc = new Scanner(System.in);
    }

    /**
     * Returns desired message.
     * 
     * @param msg the message to be printed.
     * @return message to be printed.
     */
    public String say(String msg) {
        return msg;
    }

    /**
     * Returns task list of all current tasks.
     * 
     * @param tasks task list to be printed out.
     * @return IANA's response and task list.
     */
    public String list(TaskList tasks) {
        String listMessage = "Sure! These are the tasks left: \n\n" + tasks.toString();
        return listMessage;
    }

    /**
     * Returns goodbye message.
     * 
     * @return IANA's goodbye message.
     */
    public String sayBye() {
        return say("See you next time, goodbye! :P");
    }

    /**
     * Returns welcome message.
     * 
     * @return IANA's welcome message.
     */
    public String sayHi() {
        return say("Hello there~ I'm your assistant, Iana.\n\n" 
        + "\tHow can I help you today? ^_^");
    }

    /**
     * Returns message to request new user input.
     * 
     * @return IANA's request for a new input.
     */
    public String askNewCommand() {
        return say("Oops, my vocabulary is limited! Try another action >_<");
    }

    /**
     * Returns task information that is added to task list.
     * 
     * @param task task to be returned.
     * @return IANA's response and added task information. 
     */
    public String sayTaskAdded(Task task) {
        return say(String.format("Okay! Remember to complete the task:\n\n\t   %s", task.toString()));
    }

    /**
     * Returns information of task that is deleted from task list.
     *
     * @param task task that is deleted.
     * @param listSize number of tasks left in the task list.
     * @return IANA's response, deleted task information, and number of tasks left.
     */
    public String sayTaskDeleted(Task task, int listSize) {
        String msg = "";
        if (listSize <= 1) {
            msg = String.format("Now there is %d task left, good job! ^_^", listSize);
        } else {
            msg = String.format("Now there are %d task left, keep going! :D", listSize);
        }
        return say(String.format("Done, I've removed the task :\n\n\t%s\n\n%s", 
        task.toString(), msg));
    }

    /**
     * Returns information of task that is marked.
     *  
     * @param markedTask the description of task that is marked.
     * @return IANA's response and marked task description.
     */
    public String sayTaskMarked(String markedTask) {
        String markedMsg = "Nice! I've marked this task as done:\n\n";
        return String.format("%s\t   %s", markedMsg, markedTask);
    }

    /**
     * Returns information of task that is unmarked.
     *  
     * @param unmarkedTask the description of task that is unmarked.
     * @return IANA's response and unmarked task description.
     */
    public String sayTaskUnmarked(String unmarkedTask) {
        String unmarkedMsg = "Aw man, I've unmarked the following task:\n\n";
        return String.format("%s\t   %s", unmarkedMsg, unmarkedTask);
    }

    /**
     * Reads user input from command line.
     * 
     * @return the user's input.
     */
    public String readCommand() {
        return this.sc.nextLine();
    }

    /**
     * Gives available commands for user to input.
     * 
     * @return string of available commands.
     */
    public String help() {
        String todo = "\n\t1. todo <name>  : add new todo";
        String event = "\n\t2. event <name> /at <time> : add new event";
        String deadline = "\n\t3. deadline <name> /by <time> : add new deadline";
        String list = "\n\t4. list : list all current tasks";
        String delete = "\n\t5. delete <task number> : delete task";
        String mark = "\n\t6. mark <task number> : mark task as complete";
        String unmark = "\n\t7. unmark <task number> : unmark task as complete";
        String find = "\n\t8. find <keyword> : find task with keyword";
        String help = "\n\t9. help : get all available commands";
        String bye = "\n\t10. bye : save all current tasks and leave app";

        return String.format("These are the commands you can use, try them!\n%s%s%s%s%s%s%s%s%s%s", todo,
        event, deadline, list, delete, mark, unmark, find, help, bye);
    }
}
