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
    public String systemMessage(int i , Duke d, Task t) {
        if (i == 1) {
            return "    ____________________________________________________________\n"
                    + "     Bye. Why are you still here?\n"
                    + "    ____________________________________________________________";
        } else if (i == 2) {
            return "    ____________________________________________________________\n"
                    + "     Got it. I've added this task:\n"
                    + "      " + t + "\n"
                    + "     Now you have " + (Duke.d.tasklist.numTasks - Duke.d.tasklist.removed) + " tasks in the list.\n"
                    + "    ____________________________________________________________";
        }  else if (i == 3) {

            return "    ____________________________________________________________\n"
                    + "     Noted. I've removed this task:\n"
                    + "     " + t + "\n"
                    + "    ____________________________________________________________";
        }else {
            return "";
        }

    }

    /**
     * Prints the introduction for the Puke Chatbot
     */
    public String intro() {
        String logo = " ____         _        \n"
                + "|   | \\ _   _| | _____ \n"
                + "|  _|  | | | | |/ / _ \\\n"
                + "| |    | |_| |   <  __/\n"
                + "|_|    \\__,_|_|\\_\\___|\n";

        return logo + "Hello from puke\n"
                + "Features implemented and formats required:\n"
                + "list: lists current tasks in list\n"
                + "mark/unmark x : mark as done or unmark in list\n"
                + "ToDo, Deadline, Event: \n"
                + "todo xx\n"
                + "deadline xx /by yyyy-mm-dd\n"
                + "event xx /at yyyy-mm-dd\n"
                + "delete x : remove the task from the list\n";

    }

    public void echo(Scanner sc) {
        String s = sc.nextLine();
        if (s.equals("bye")){
            return;
        }
        echo(sc);
    }

    /**
     * Prints out system message for marking tasks as done/undone
     * @param s input by user
     * @param pos index of task to mark
     * @param d puke chatbot
     */
    public String taskManager(String s, int pos, Duke d) {
        if (s.equals("do")) {
            Duke.d.tasklist.tasks.get(pos - 1).markAsDone();
            return "    ____________________________________________________________\n"
                    +  "     Nice! I've marked this task as done: \n"
                    +  "       " + Duke.d.tasklist.tasks.get(pos-1) + "\n"
                    +  "    ____________________________________________________________" ;

        } else if (s.equals("undo")) {
            Duke.d.tasklist.tasks.get(pos - 1).markAsUndone();
            System.out.println("       " + Duke.d.tasklist.tasks.get(pos-1));
            return "    ____________________________________________________________\n"
                    +  "     OK! I've marked this task as not done yet: \n"
                    +  "       " + Duke.d.tasklist.tasks.get(pos-1) + "\n"
                    +  "    ____________________________________________________________" ;
        } else {
            return "    Failed to manage your tasks, try doing it yourself next time";
        }
    }
} 
