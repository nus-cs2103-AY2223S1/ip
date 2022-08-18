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
            else if (userIn.contains("todo")) {
                String[] des = userIn.split(" ", 2);
                Task toAdd = new Todos(des[1]);
                taskList.add(toAdd);
                addStatement(toAdd.toString(), taskList.size());
            }
            else if (userIn.contains("deadline")) {
                String[] overall = userIn.split(" ", 2);
                String[] descriptdate = overall[1].split("/by", 2);
                Task toAdd = new Deadlines(descriptdate[0], descriptdate[1]);
                taskList.add(toAdd);
                addStatement(toAdd.toString(), taskList.size());
            }
            else if (userIn.contains("event")) {
                String[] overall = userIn.split(" ", 2);
                String[] descriptdate = overall[1].split("/at", 2);
                Task toAdd = new Events(descriptdate[0], descriptdate[1]);
                taskList.add(toAdd);
                addStatement(toAdd.toString(), taskList.size());
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

    public static void addStatement(String res, int len) {
        System.out.println("Got it. I've added this task:\n" + "  " + res + "\nNow you have " + len + " tasks in the list.");
    }
}
