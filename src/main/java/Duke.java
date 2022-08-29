import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {

    enum Ability {
        BYE,
        LIST,
        MARK,
        UNMARK,
        TODO,
        DELETE
    }

    public static void main(String[] args) {

        String hello = "    ____________________________________________________________\n" +
                "     Hello! I'm Duke\n" +
                "     What can I do for you?\n" +
                "    ____________________________________________________________\n";
        System.out.println(hello);

        List<Task> list = new ArrayList<Task>();

        Scanner sc = new Scanner(System.in);
        String input = sc.next();

        while (!input.equals("bye")) {
            if (input.equals("list")) {
                for (int i = 0; i < list.size(); i++) {
                    System.out.println(i + 1 + "." + list.get(i));
                }
            } else if (input.equals("mark")) {
                int number = sc.nextInt();
                Task task = list.get(number - 1);
                task.markAsDone();
                System.out.println("    ____________________________________________________________\n" +
                        "     Nice! I've marked this task as done:\n       " +
                        task +
                        "\n    ____________________________________________________________");
            } else if (input.equals("unmark")) {
                int number = sc.nextInt();
                Task task = list.get(number - 1);
                task.markAsNotDone();
                System.out.println("    ____________________________________________________________\n" +
                        "     Nice! I've marked this task as not done yet:\n       " +
                        task +
                        "\n    ____________________________________________________________");
            } else {
                input = input + sc.nextLine();
                System.out.println("Added: " + input);
                Task task = new Task(input);
                list.add(task);
            }
            input = sc.next();
        }



        String strlst = "____________________________________________________________\n" +
                "     Here are the tasks in your list:";

        System.out.println(strlst);

        String todolist = "____________________________________________________________\n" +
                "     Got it. I've added this task:";
        System.out.println(todolist);

        String todoEmpty = "____________________________________________________________\n" +
                "     ☹ OOPS!!! The description of a todo cannot be empty.\n" +
                "____________________________________________________________\n";
        System.out.println(todoEmpty);

        String unkown = "____________________________________________________________\n" +
                "     ☹ OOPS!!! I'm sorry, but I don't know what that means :-(\n" +
                "____________________________________________________________\n";

        System.out.println(unkown);

        // if("delete")

    }
}
