package dukechatbot.utility;
import dukechatbot.dukeexception.DukeException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

public class Ui {
    private ArrayList<Task> tl;

    public Ui(ArrayList<Task> tl) {
        this.tl = tl;
    }
    public void greet() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("--------------------------------------\n");
        System.out.println("\tHello I'm Duke, what can I do for you?");
        System.out.println("--------------------------------------\n");
    }

    public void bye() {
        System.out.println("--------------------------------------\n");
        System.out.println("\tBye. Hope to see you soon again!");
        System.out.println("--------------------------------------\n");
    }

    public void showLoadingError() throws IOException {
        throw new IOException("File Loading Error!");
    }

    public void added(Task t) {
        System.out.println("------------------------------\n");
        System.out.printf("\tGot it. I've added this task: \n\t\t%s\n", t.toString());
        System.out.println("\tNow you have " + this.tl.size() + " task(s) in the list.");
        System.out.println("------------------------------\n");    }

    public void marked(Task t) {
        System.out.println("-------------------------------\n");
        System.out.println("\tNice! I have marked this task as done: ");
        System.out.println("\t\t" + t.toString());
        System.out.println("-------------------------------\n");
    }

    public void unmarked(Task t) {
        System.out.println("-------------------------------\n");
        System.out.println("\tOK, I've marked this task as not done yet: ");
        System.out.println("\t\t" + t.toString());
        System.out.println("-------------------------------\n");
    }

    public void removed(Task t, ArrayList<Task> tl) {
        System.out.println("------------------------------\n");
        System.out.println("\tNoted. I've removed this task:");
        System.out.println("\t\t" + t.toString());
        System.out.println("\tNow you have " + tl.size() + " task(s) in the list.");
        System.out.println("------------------------------\n");
    }

    public void showTimeMissingError() throws DukeException {
        throw new DukeException(
                "☹ OOPS!!! Associated time for event or deadline cannot be empty.");
    }

    public void showDescEmptyError(String str) throws DukeException {
        throw new DukeException("" +
                "☹ OOPS!!! The description of a " + str + " cannot be empty.");
    }

    public void showUnknownCommandError() throws DukeException {
        throw new DukeException(
                "☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
    }

    public void listOut(ArrayList<Task> tl) {
        int count = 1;
        System.out.println("-------------------------------\n");
        System.out.println("\tHere are the tasks in your list: ");
        for (Iterator<Task> it = tl.iterator(); it.hasNext();) {
            Task curr = it.next();
            System.out.println("\t\t" +count + ". " + curr.toString());
            count++;
        }
        System.out.println("-------------------------------\n");
    }

    public void listMatch(ArrayList<Task> tl) {
        int count = 1;
        System.out.println("-------------------------------\n");
        System.out.println("\tHere are the matching tasks in your list: ");
        for (Iterator<Task> it = tl.iterator(); it.hasNext();) {
            Task curr = it.next();
            System.out.println("\t\t" +count + ". " + curr.toString());
            count++;
        }
        System.out.println("-------------------------------\n");
    }

}
