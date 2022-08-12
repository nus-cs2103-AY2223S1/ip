import java.util.Scanner;
import static java.lang.Integer.parseInt;

public class Duke {

    public final static String initMsg = "Hello! I'm Duke \n What can I do for you?";
    public final static String byeMsg = "Bye. Hope to see you soon again!";

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        TaskList lst = new TaskList();
        printMessage(initMsg);
        while (true) {
            String input = scan.nextLine();
            if (input.equals("bye")) {
                break;
            } else if (input.equals("list")) {
                lst.printList();
            } else if (input.split(" ")[0].equals("mark") || input.split(" ")[0].equals("unmark")) {
                if (input.split(" ")[0].equals("mark")) {
                    lst.markTask(parseInt(input.split(" ")[1]));
                } else {
                    lst.unmarkTask(parseInt(input.split(" ")[1]));
                }
            } else {
                lst.addTask(new Task(input));
            }
        }
        scan.close();
        printMessage(byeMsg);
    }

    public static void printMessage(String s) {
        System.out.println("--------------------------------------");
        System.out.println(s);
        System.out.println("--------------------------------------");
    }
}
