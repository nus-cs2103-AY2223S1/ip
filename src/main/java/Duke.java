import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        List<Task> tasks = new ArrayList<>();
        prettyPrint("Hello! I'm Duke\nWhat can I do for you?");
        while (in.hasNextLine()) {
            String input = in.nextLine();
            String[] command = input.split(" ");
            if (command[0].equalsIgnoreCase("bye")) {
                prettyPrint("Bye. Hope to see you again soon!");
                break;
            } else if (command[0].equalsIgnoreCase("list")) {
                StringBuilder temp = new StringBuilder("Here are the tasks in your list:\n");
                for (int i = 0; i < tasks.size(); i++) {
                    temp.append(i + 1);
                    temp.append(".");
                    temp.append(tasks.get(i));
                    temp.append("\n");
                }
                prettyPrint(temp.toString());
            } else if (command[0].equalsIgnoreCase("mark")) {
                Task task = tasks.get(Integer.parseInt(command[1]) - 1);
                task.mark();
                prettyPrint("Nice! I've marked this task as done:\n  " + task);
            } else if (command[0].equalsIgnoreCase("unmark")) {
                Task task = tasks.get(Integer.parseInt(command[1]) - 1);
                task.unmark();
                prettyPrint("OK, I've marked this task as not done yet:\n  " + task);
            } else {
                tasks.add(new Task(input));
                prettyPrint("added: " + input);
            }
        }
    }

    private static void prettyPrint(String text) {
        String[] lines = text.split("\n");
        StringBuilder temp = new StringBuilder("    ____________________________________________________________\n");
        for (String line : lines) {
            temp.append("     ");
            temp.append(line);
            temp.append("\n");
        }
        temp.append("    ____________________________________________________________\n");
        System.out.println(temp);
    }
}
