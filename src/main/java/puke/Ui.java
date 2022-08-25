package puke;

import java.util.Scanner;

/**
 * Handles the user interface for the User interactions
 */
public class Ui {

    /**
     * Sends a corresponding system message based on given tasks and conditions
     * @param i controller to decide which message to send
     * @param d chatbot object
     * @param t task that is being manipulated
     */
    public void systemMessage(int i , Duke d, Task t) {
        if (i == 1) {
            //Bye message
            System.out.println("    ____________________________________________________________");
            System.out.println("     Bye. Why are you still here?");
            System.out.println("    ____________________________________________________________");
        } else if (i == 2) {
            // to do message
            System.out.println("    ____________________________________________________________");
            System.out.println("     Got it. I've added this task:");
            System.out.println("      " + t);
            System.out.println("     Now you have " + (Duke.d.tasklist.numTasks - Duke.d.tasklist.removed) + " tasks in the list.");
            System.out.println("    ____________________________________________________________");
        }  else if (i == 3) {
            System.out.println("    ____________________________________________________________");
            System.out.println("     Noted. I've removed this task:");
            System.out.println("     " + t);
            System.out.println("     Now you have " + (Duke.d.tasklist.numTasks - Duke.d.tasklist.removed) + " tasks in the list.");
            System.out.println("    ____________________________________________________________");
        }else {
            return;
        }

    }

    /**
     * Prints the introduction for the Puke Chatbot
     */
    public void intro() {
        String logo = " ____         _        \n"
                + "|   | \\ _   _| | _____ \n"
                + "|  _|  | | | | |/ / _ \\\n"
                + "| |    | |_| |   <  __/\n"
                + "|_|    \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from puke\n" + logo);
        System.out.println("Features implemented and formats required:");
        System.out.println("list: lists current tasks in list");
        System.out.println("mark/unmark x : mark as done or unmark in list");
        System.out.println("ToDo, Deadline, Event: ");
        System.out.println("todo xx");
        System.out.println("deadline xx /by yyyy-mm-dd");
        System.out.println("event xx /at yyyy-mm-dd");
        System.out.println("delete x : remove the task from the list");
    }

    public void echo(Scanner sc) {
        String s = sc.nextLine();
        if (s.equals("bye")){
            System.out.println("    ____________________________________________________________");
            System.out.println("     Bye. Why are you still here?");
            System.out.println("    ____________________________________________________________");
            return;
        }
        System.out.println("    ____________________________________________________________");
        System.out.println("    " + s);
        System.out.println("    ____________________________________________________________");
        echo(sc);
    }

    /**
     * Prints out system message for marking tasks as done/undone
     * @param s input by user
     * @param pos index of task to mark
     * @param d puke chatbot
     */
    public void taskManager(String s, int pos, Duke d) {
        if (s.equals("do")) {
            System.out.println("    ____________________________________________________________");
            System.out.println("     Nice! I've marked this task as done: ");
            Duke.d.tasklist.tasks.get(pos - 1).markAsDone();
            System.out.println("       " + Duke.d.tasklist.tasks.get(pos-1));
            System.out.println("    ____________________________________________________________");
        } else if (s.equals("undo")) {
            System.out.println("    ____________________________________________________________");
            System.out.println("     OK, I've marked this task as not done yet: ");
            Duke.d.tasklist.tasks.get(pos - 1).markAsUndone();
            System.out.println("       " + Duke.d.tasklist.tasks.get(pos-1));
            System.out.println("    ____________________________________________________________");
        } else {
            return;
        }
    }
} 
