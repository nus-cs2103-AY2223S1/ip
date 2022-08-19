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
            } else if (userInput.equals("list")) {
                if (list.isEmpty()) {
                    System.out.println(line + "nuuuu list empty! add an item first :-DD\n" + line);
                } else {
                    System.out.println(line + makeList(list) + line);
                }
            } else if (userInput.contains("mark")){
                String task = userInput.substring(5);
                markDone(task, list);
                System.out.println(line + "okie! " + task + " is done ~\n" + line);
            } else {
                list.add(new Task(userInput, false));
                System.out.println(line + " added: " + userInput + "\n" + line);  // Output user input
            }


        }
    }

    public static String makeList(ArrayList<Task> ls) {
        String s = "";

        for (int i = 0; i < ls.size(); i++) {
            int index = i + 1;
            s += index + "." + ls.get(i).isDoneString() + " " + ls.get(i).getTask() + "\n";
        }

        return s;
    }

    public static void markDone(String task, ArrayList<Task> ls) {
        for (int i = 0; i < ls.size(); i++) {
            if (ls.get(i).getTask().equals(task)) {
                ls.get(i).markDone();
            }
        }
    }
}
