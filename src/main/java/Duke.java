import java.util.List;
import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    private static ArrayList tasks = new ArrayList();
    private static void Greet() {
        String logo = " _______               \n"
                    + "|  _____|  _   _____   \n"
                    + "|  |____  | | |  __ |  \n"
                    + "|   ____| | | |  ___|  \n"
                    + "|  |____  | | | |      \n"
                    + "|_______| |_| |_|";
        System.out.println("Greetings from Elp\n" + logo);
        System.out.println("What can I help you with?");
    }

    private static void TaskHandler() {
        Scanner sc = new Scanner(System.in);
        String in = sc.nextLine();

        while (true) {
            // Break out of loop
            if (in.equals("bye")) {
                break;
            }
            // List out current tasks in the list
            if (in.equals("list")) {
                for (int i = 1; i <= tasks.size(); i++) {
                    System.out.println(i + ". " + tasks.get(i - 1));
                }
                System.out.println("");
            } else {
                System.out.println("Added: " + in + "\n");
                tasks.add(in);
            }
            in = sc.nextLine();
        }
        System.out.println("Have a nice day!");
    }

    public static void main(String[] args) {
        Greet();
        TaskHandler();
    }
}
