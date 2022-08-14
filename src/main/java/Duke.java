import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    private static Scanner sc = new Scanner(System.in);
    private static ArrayList<Task> tasks = new ArrayList<>();

    public static void main(String[] args) {
        System.out.println("    ____________________________________________________________\n" +
                "     Hello! I'm Duke\n" +
                "     What can I do for you?\n" +
                "    ____________________________________________________________");
        while (true) {
            String userInput = sc.nextLine();
            switch (userInput) {
                case "bye":
                    System.out.println("bye");
                    return;
                default:
                    System.out.println(userInput);
            }
        }
    }
}
