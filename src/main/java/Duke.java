import java.util.Scanner;
import java.util.ArrayList;
public class Duke {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Hello! I'm Duke\nWhat can I do for you?");
        String input = "";
        ArrayList<Task> list = new ArrayList<>();
        while (true) {
            input = scanner.nextLine();
            if (input.equals("bye")) {
                break;
            } else if (input.equals("list")) {
                for (int i = 0; i < list.size(); i++) {
                    System.out.println((i + 1) + ". " + list.get(i));
                }
            } else if (input.startsWith("mark")) {
                int target = Integer.valueOf(input.split(" ")[1]) - 1;
                System.out.println(list.get(target).mark());
            } else if (input.startsWith("unmark")) {
                int target = Integer.valueOf(input.split(" ")[1]) - 1;
                System.out.println(list.get(target).unmark());
            } else if (input.startsWith("todo")) {
                list.add(new ToDo(input.split("todo ")[1]));
                System.out.println("Got it. I've added this task:\n" + list.get(list.size() -1));
                System.out.println("Now you have " + list.size() + " tasks in the list.");
            } else if (input.startsWith("deadline")) {
                list.add(new Deadline(input.split("deadline ")[1].split(" /by")[0], input.split("/by ")[1]));
                System.out.println("Got it. I've added this task:\n" + list.get(list.size() -1));
                System.out.println("Now you have " + list.size() + " tasks in the list.");
            } else if (input.startsWith("event")) {
                list.add(new Event(input.split("event ")[1].split(" /at")[0], input.split("/at ")[1]));
                System.out.println("Got it. I've added this task:\n" + list.get(list.size() -1));
                System.out.println("Now you have " + list.size() + " tasks in the list.");
            } else {
                list.add(new Task(input));
                System.out.println("added: " + input);
            }
        }
        System.out.println("Bye. Hope to see you again soon!");
    }
}
