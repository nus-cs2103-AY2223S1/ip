import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    private static List<Task> tasks = new ArrayList<>();

    public static void main(String[] args) {
        Scanner reader = new Scanner(System.in);
        System.out.println("Hello! I'm Duke\nWhat can I do for you?");
        while (true) {
            String input = reader.nextLine();

            if (input.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                return;
            } else if (input.equals("list")) {
                System.out.println("Here are the tasks in your list:");
                for (int i = 0; i < tasks.size(); i++) {
                    System.out.println(i + 1 + ". " + tasks.get(i));
                }
            } else if (input.contains("mark")) {
                String[] strings = input.split(" ");
                int num = Integer.parseInt(strings[1]);
                String txt = strings[0];
                if(txt.equals("mark")) {
                    tasks.get(num - 1).setDone(true);
                    System.out.println("Nice! I've marked this task as done:\n" + tasks.get(num - 1));
                } else {
                    tasks.get(num - 1).setDone(false);
                    System.out.println("Ok, I've marked this task as not done yet:\n" + tasks.get(num - 1));
                }
            }  else {
                tasks.add(new Task(input));
                System.out.println("added: " + input);
            }
        }
    }

}
