package Rabbit.util;

import java.util.Scanner;

public class Ui {
    private Scanner sc;

    /**
     * Constructor of Ui.
     */
    public Ui(){
        this.sc = new Scanner(System.in);
    };

    /**
     * Shows the greeting lines and instructions.
     */
    public void showGreet() {
        System.out.println("-----------------------------------------------------------------------------\n"
                + "-----------------------------------------------------------------------------\n"
                + "Yo...nice to meet you. This is rabbit...Ughhhhh I hate this job.\n"
                + "You can input stuff that you want me to write on this grandma-aged notebook.\n"
                + "-----------------------------------------------------------------------------\n"
                + "1. Type the type of a task followed by its content and time to add it into the list.\n"
                + "   There are three types: todo, deadline and event.\n"
                + "   - To add todo, type 'todo the content' such as 'todo do homework'.\n"
                + "   - To add deadline, type 'deadline the content /year-month-day-time' "
                + "such as 'deadline do homework /2022-08-22-1800'.\n"
                + "   - To add event, type 'event the content /year-month-day-time' "
                + "such as 'deadline do homework /2022-08-22-1800'.\n"
                + "2. Type 'list' then I'll show all the existing lines to you.\n"
                + "3. Type 'mark + the index of an existing task' to marks it as done. Like 'mark 1'.\n"
                + "4. Type 'unmark + the index of an existing task' to unmark a task.\n"
                + "5. Type 'delete + the index of an existing task' to delete it.\n"
                + "-----------------------------------------------------------------------------\n"
                + "Actually why not just do me a favour? Type 'bye' in the console and free both of us.");
    }

    /**
     * Shows the line when the user types 'bye'.
     */
    public void showBye() {
        System.out.println("Thanks a lot. I'm gonna have some carrot tea later. See you...");
    }

    /**
     * Shows the exception line.
     *
     * @param e the exception caught.
     */
    public void showException(Exception e) {
        System.out.println(e);
    }

    /**
     * Shows the line when a task is added.
     *
     * @param content the content of the task.
     */
    public void showAddToList(String content) {
        System.out.println("Okay...noted.\n" + content + "...Huh? Hope you can remember it.");
    }

    /**
     * Shows the content of the list.
     *
     * @param content the content of the list.
     */
    public void showList(String content) {
        System.out.println(content);
    }

    /**
     * Shows the line when a task is marked.
     *
     * @param content the content of the task.
     */
    public void showMark(String content) {
        System.out.println("Okay...task: " + content + " is marked as done.");
    }

    /**
     * Shows the line when a task is unmarked.
     *
     * @param content the content of the task.
     */
    public void showUnmark(String content) {
        System.out.println("Okay...task: " + content + " is unmarked.");
    }

    /**
     * Shows the line when a task is deleted.
     *
     * @param content the content of the task.
     */
    public void showDelete(String content) {
        System.out.println("Okay...task: " + content + " is deleted.");
    }

    /**
     * Reads the command of the user.
     *
     * @return the user's command.
     */
    public String readCommand() {
        return this.sc.nextLine();
    }

    /**
     * Closes the scanner when the user types 'bye'.
     */
    public void endCommand() {
        this.sc.close();
    }
}
