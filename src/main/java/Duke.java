import java.util.Scanner;
import java.util.ArrayList;
import java.util.List; 

public class Duke {
    public static String getNthWord(String str, int n) {
        return str.contains(" ") ? str.split(" ")[n] : str;
    }
    public static void main(String[] args) {
        String input; // initializing the input
        List<Task> list = new ArrayList<>(100); // initiliazing the list array to store previously added things
        Scanner scanner = new Scanner(System.in);// initializing the Scanner class


        String logo = "        / \\     |_   _| | |  / _|                   | |\n"
                    + "       /   \\      | |   | | | |_   _ __    ___    __| |\n"
                    + "      / / \\ \\     | |   | | |  _| | '__|  / _ \\  / _` |\n"
                    + "     / _____ \\   _| |_  | | | |   | |    |  __/ | (_| |\n"
                    + "    /_/     \\_\\ |_____| |_| |_|   |_|     \\___|  \\__,_|\n";

        // Welcome message
        System.out.println("____________________________________________________________\n" +
                           "                      Hello! I am \n" + logo +
                           "      Your personal assistant. What can I do for you?\n" +
                           "____________________________________________________________");

        // Reading user inputs
        while(scanner.hasNextLine()) {
            input = scanner.nextLine();
            if (input.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            } else if (input.equals("list")) {
                System.out.println("____________________________________________________________\n");
                for (int i = 0; i < list.size(); i++) {
                    System.out.println((i+1) + ". [" + list.get(i).getStatusIcon() + "] " + list.get(i).getDescription() + "\n");
                }
                System.out.println("____________________________________________________________\n");
            } else if (getNthWord(input, 0).equals("mark")) {
                Integer index = Integer.parseInt(getNthWord(input, 1)) - 1;
                if (index + 1 > list.size() || index + 1 <= 0) {
                    System.out.println("____________________________________________________________\n" +
                                       "There is no task created for index " + (index + 1) + "\n" +
                                       "____________________________________________________________\n");
                } else {
                    list.get(index).markAsDone();

                    System.out.println("____________________________________________________________\n" +
                                    "Nice! I've marked this task as done: \n" +
                                    "[" + list.get(index).getStatusIcon() + "] " + list.get(index).getDescription() + "\n" + 
                                    "____________________________________________________________\n");
                }
            } else if (getNthWord(input, 0).equals("unmark")) {
                Integer index = Integer.parseInt(getNthWord(input, 1)) - 1;
                if (index + 1 > list.size() || index + 1 <= 0) {
                    System.out.println("____________________________________________________________\n" +
                                       "There is no task created for index " + (index + 1) + "\n" +
                                       "____________________________________________________________\n");
                } else {
                    list.get(index).markAsUndone();

                    System.out.println("____________________________________________________________\n" +
                                    "OK, I've marked this task as not done yet: \n" +
                                    "[" + list.get(index).getStatusIcon() + "] " + list.get(index).getDescription() + "\n" + 
                                    "____________________________________________________________\n");
                }
            } else {
                Task curr = new Task(input);
                list.add(curr);
                System.out.println("____________________________________________________________\n" +
                                   "added: " + input + "\n" +
                                   "____________________________________________________________\n");
            }
        }
        scanner.close();
    }
}
