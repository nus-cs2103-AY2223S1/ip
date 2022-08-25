import java.util.ArrayList;
import java.util.Iterator;

public class Ui {
    private ArrayList<Task> tl;

    public void greet() {
        System.out.println("--------------------------------------\n");
        System.out.println("\tHello I'm Duke, what can I do for you?");
        System.out.println("--------------------------------------\n");
    }

    public void bye() {
        System.out.println("--------------------------------------\n");
        System.out.println("\tBye. Hope to see you soon again!");
        System.out.println("--------------------------------------\n");
    }

    public void showLoadingError() {
        System.out.println("--------------------------------------\n");
        System.out.println("File Loading Error!");
        System.out.println("--------------------------------------\n");
    }

    public void askForPath() {
        System.out.println("--------------------------------------\n");
        System.out.println("Please enter path name for me to read: ");
        System.out.println("--------------------------------------\n");
    }

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

    public void listOut() {
        int count = 1;
        System.out.println("-------------------------------\n");
        System.out.println("\tHere are the tasks in your list: ");
        for (Iterator<Task> it = this.tl.iterator(); it.hasNext();) {
            Task curr = it.next();
            System.out.println("\t\t" +count + ". " + curr.toString());
            count++;
        }
        System.out.println("-------------------------------\n");
    }

}
