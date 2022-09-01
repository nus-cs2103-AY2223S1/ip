package duke;

import java.util.Scanner;

/**
 * Represents the interaction with the users.
 */
public class Ui {


    Ui() {

    }

    /**
     * Greet the users at the start of the program.
     */
    void greet() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
    }

    void exit() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    void added(String taskFullDescription, int totalNoOfTask) {
        System.out.println("Got it. I've added this task:");
        System.out.println(taskFullDescription);
        System.out.println("Now you have " + totalNoOfTask + " tasks in the list.");
    }

    void deleted(String taskFullDescription, int totalNoOfTask) {
        System.out.println("Noted. I've removed this task:");
        System.out.println(taskFullDescription);
        System.out.println("Now you have " + totalNoOfTask + " tasks in the list.");
    }

    void marked(String taskFullDescription) {
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(taskFullDescription);
    }

    void unmarked(String taskFullDescription) {
        System.out.println("OK! I've marked this task as not done yet:");
        System.out.println(taskFullDescription);
    }

    /**
     * Read the inputs from the user and convert it to a string.
     * @return String : the full command given by the user.
     */
    String readCommand() {
        Scanner sc = new Scanner(System.in);
        String command = sc.nextLine();
        return command;
    }

}
