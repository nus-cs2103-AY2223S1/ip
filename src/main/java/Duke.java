import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        ArrayList<Task> list = new ArrayList<>();

        String line = " _______________________________________ \n";
        System.out.println(line + " I'm Dukie\n" + " What can I do for you?\n" + line);

        Scanner myObj = new Scanner(System.in);  // Create a Scanner object
        String userInput = "0";

        while (true) {

            userInput = myObj.nextLine();  // Read user input

            if (userInput.equals("bye")) {
                System.out.println("Goodbaiiiii\n");
                System.exit(0);
            } else if (userInput.startsWith("list")) {
                if (list.isEmpty()) {
                    System.out.println(line + "nuuuu list empty! add an item first :-DD\n" + line);
                } else {
                    System.out.println(line + makeList(list) + line);
                }
            } else if (userInput.startsWith("mark")){
                String task = userInput.substring(5);
                String s = markDone(task, list);
                System.out.println(line + s + line);
            } else if (userInput.startsWith("unmark")){
                String task = userInput.substring(7);
                String s = unmarkDone(task, list);
                System.out.println(line + s + line);
            } else if (userInput.startsWith("todo") || userInput.startsWith("deadline") || userInput.startsWith("event")) {
                Task t = new Task(userInput.substring(userInput.indexOf(" ") + 1),
                        userInput.substring(0, userInput.indexOf(" ")).toUpperCase(), false);
                list.add(t);
                System.out.println(line + " okie! i've added: \n " + t.toString() + "\n now you have " + list.size() + " task(s) in your list!\n" + line);  // Output user input
            } else {
                /* list.add(new Task(userInput.substring(userInput.indexOf(" ") + 1),
                        userInput.substring(0, userInput.indexOf(" ")).toUpperCase(), false));
                System.out.println(line + " added: " + userInput + "\n" + line);  // Output user input */
                System.out.println("reach end");
            }
        }
    }

    public static String makeList(ArrayList<Task> ls) {
        String s = "";

        for (int i = 0; i < ls.size(); i++) {
            int index = i + 1;
            // s += index + "." + ls.get(i).isDoneString() + " " + ls.get(i).getTask() + "\n";
            s += " " + index + "." + ls.get(i).toString() + "\n";

        }

        return s;
    }

    public static String markDone(String task, ArrayList<Task> ls) {
        for (int i = 0; i < ls.size(); i++) {
            if (ls.get(i).getTask().equals(task)) {
                ls.get(i).markDone();
                return " okie! " + task + " is done ~\n [X] " + task + "\n";
            }
        }
        return task + " not found :(\n";
    }

    public static String unmarkDone(String task, ArrayList<Task> ls) {
        for (int i = 0; i < ls.size(); i++) {
            if (ls.get(i).getTask().equals(task)) {
                ls.get(i).unmarkDone();
                return " owh ;< so you haven't done " + task + ". unmarked ~\n [ ] " + task + "\n";
            }
        }
        return task + " not found :(\n";
    }
}
