import java.util.ArrayList;
import java.util.Scanner;


public class Duke {
    public static ArrayList<Task> list = new ArrayList<>();

    public static void main(String[] args) {
        boolean exit = false;
        System.out.println("Hello! I'm Milk");
        System.out.println("What can I do for you?");
        Scanner sc = new Scanner(System.in);

        while (!exit) {
            String userReply = sc.nextLine();
            if (userReply.equals("bye")) {
                exit = true;
            } else if (userReply.equals("list")) {
                System.out.println("Here are the tasks in your list:");
                for (int i = 0; i < list.size(); i++) {
                    System.out.println(i+1 + "." + list.get(i));
                }
                // need to replace contains with regex
            } else if (userReply.matches(".*\\bmark\\b.*")) {
                int index = Integer.parseInt(userReply.replaceAll("[^0-9]", ""));
                index--;
                list.get(index).finished();
                System.out.println("Nice! I've marked this task as done:");
                System.out.println(list.get(index));
            } else if (userReply.matches(".*\\bunmark\\b.*")) {
                int index = Integer.parseInt(userReply.replaceAll("[^0-9]", ""));
                index--;
                list.get(index).notFinished();
                System.out.println("OK, I've marked this task as not done yet:");
                System.out.println(list.get(index));
            } else {
                System.out.println("added: " + userReply);
                list.add(new Task(userReply));
            }
        }
        System.out.println("Bye. Hope to see you again soon!");
    }
}
