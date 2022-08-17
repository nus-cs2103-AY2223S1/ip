import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

    private static String indent = "       ";

    private static ArrayList<String> tasks = new ArrayList<>();
    private static Integer taskCounter = 0;

    public static void main(String[] args) {
        System.out.println("\n Hello there! \n"
                + "\n My name is Zelk, nice to meet you :D \n"
                + " What can I do for you?\n"
                + " ___________________________________________________________________");

        Scanner s = new Scanner(System.in);
        String input = "";
        while ((input = s.nextLine()) != null) {
            if (input.equals("list")) {
                System.out.println(indent + "Here's what's in your list so far!");
                for (int i = 0; i < tasks.size(); i++) {
                    System.out.println(indent + tasks.get(i));
                }
                System.out.println(" ___________________________________________________________________");
            } else if (input.equals("bye")) {
                break;
            } else {
                taskCounter++;
                tasks.add(taskCounter.toString() + ". " + input);
                System.out.println(indent + "added: " + input
                        + "\n ___________________________________________________________________");
            }
        }

        System.out.println(indent + "Bye! Hope to see you again soon! Thank you for chatting with me :)");
    }
}
