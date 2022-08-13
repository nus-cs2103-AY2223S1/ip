import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String input;
        Scanner scanner = new Scanner(System.in);
        String logo = "        / \\     |_   _| | |  / _|                   | |\n"
                    + "       /   \\      | |   | | | |_   _ __    ___    __| |\n"
                    + "      / / \\ \\     | |   | | |  _| | '__|  / _ \\  / _` |\n"
                    + "     / _____ \\   _| |_  | | | |   | |    |  __/ | (_| |\n"
                    + "    /_/     \\_\\ |_____| |_| |_|   |_|     \\___|  \\__,_|\n";

        System.out.println("____________________________________________________________\n" +
                           "                      Hello! I am \n" + logo +
                           "      Your personal assistant. What can I do for you?\n" +
                           "____________________________________________________________");
        while(scanner.hasNextLine()) {
            input = scanner.nextLine();
            if (input.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            } else {
                System.out.println("____________________________________________________________\n" +
                                   "added: " + input + "\n"+
                                   "____________________________________________________________");
            }
        }
    }
}
