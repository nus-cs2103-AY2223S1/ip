import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        entryStatement();
        ArrayList<Task> taskList = new ArrayList<Task>();
        Scanner myObj = new Scanner(System.in);  // Create a Scanner object
        System.out.println("What can I do for you?");
        while (true) {
            String userIn = myObj.nextLine();  // Read user input
            if (userIn.equals("bye")) {
                byeStatement();
                break;
            }
            else if (userIn.equals("list")) {
                for (int i = 1; i <= taskList.size(); i++) {
                    System.out.println(i+". " + taskList.get(i - 1).toString());
                }
            }
            else if (userIn.contains("unmark")) {
                String[] inArr = userIn.split(" ", 2);
                int ind = Integer.parseInt(inArr[1]) - 1;
                taskList.get(ind).unmark();
                System.out.println("OK, I've marked this task as not done yet:\n" + "  " + taskList.get(ind).toString());

            }
            else if (userIn.contains("mark")) {
                String[] inArr = userIn.split(" ", 2);
                int ind = Integer.parseInt(inArr[1]) - 1;
                taskList.get(ind).mark();
                System.out.println("Nice! I've marked this task as done:\n" + "  " + taskList.get(ind).toString());
            }
            else {
                taskList.add(new Task(userIn));
                System.out.println("added: " + userIn);  // Output user input
            }
        }

    }
    public static void entryStatement() {
        System.out.println(" /\\_/\\");
        System.out.println("/ o o \\");
        System.out.println("/ ^  ^\\");
        System.out.println("Hello from Chan-bot!");
    }

    public static void byeStatement() {
        System.out.println("Bye bye!");
        System.out.println(" /\\_/\\");
        System.out.println("/ o o \\");
        System.out.println("/    ^\\");
    }
}
