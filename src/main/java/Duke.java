import java.util.Scanner;
import java.util.ArrayList;
import java.util.List; 

public class Duke {
    public static void main(String[] args) {
        String input; // initializing the input
        List<String> list = new ArrayList<>(100); // initiliazing the list array to store previously added things
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
                    System.out.println((i+1) + ". " + list.get(i) + "\n");
                }
                System.out.println("____________________________________________________________\n");
            } else {
                list.add(input);
                System.out.println("____________________________________________________________\n" +
                                   "added: " + input + "\n" +
                                   "____________________________________________________________");
            }
        }
        scanner.close();
    }
}
