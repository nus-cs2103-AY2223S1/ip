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

        String hello = "____________________________________________________________\n" +
                "     Hello! I'm Duke\n" +
                "     What can I do for you?\n" +
                "____________________________________________________________\n";
        System.out.println(hello);

        List<String> list = new ArrayList<>();
        int size = 0;

        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        while (!input.equals("bye")) {
            if (input.equals("list")) {
                for (int i = 0; i < size; i++) {
                    System.out.println(i + 1 + "." + list.get(i));
                }
            } else {
                System.out.println("Added: " + input);
                list.add(input);
                size++;
            }
            input = sc.nextLine();
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
