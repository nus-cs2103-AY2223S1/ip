package duke;

import java.util.List;
import java.util.Scanner;

public class Ui {
    private static final String LOGO = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";
    private static final String GREETING = "Hello from\n"  + LOGO + "\nHow can I help you ?\n" ;


    public Ui(){

    }

    /**
     * Prints the startup message
     */
    public void showWelcome() {
        System.out.println(GREETING);
    }

    /**
     * Prints the ending message
     */
    public void goodbyeMessage() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    public String readCommand() {
        Scanner scanner = new Scanner(System.in);
        String userInput = scanner.nextLine();
        return userInput;
    }

    public void showAddCommand(Task task,int size) {
        System.out.println("Got it. I've added this task:\n " + task.toString() + "\nNow you have " + size +" tasks in the list.");
    }

    public void showDelete(Task task,int size) {
        System.out.println(" Noted. I've removed this task:");
        System.out.println(" " + task.toString() +"\nNow you have " + size +" tasks in the list.");
    }

    public void showMark(Task task) {
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(" " + task.toString());
    }

    public void showUnmark(Task task) {
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(" " + task.toString());
    }

    public void showFindResult(List<Integer> indexList, TaskList taskList) throws DukeException {
        System.out.println("Here are the matching tasks in your list:");
        for (Integer index : indexList) {
            System.out.println(taskList.getTask(index).toString());
        }

    }



}
