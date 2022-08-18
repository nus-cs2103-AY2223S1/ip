import java.util.*;

public class Duke {

    enum Ability {
        bye,
        list,
        mark,
        unmark,
        todo,
        delete
    }

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        String hello = "____________________________________________________________\n" +
                "     Hello! I'm Duke\n" +
                "     What can I do for you?\n" +
                "____________________________________________________________\n";
        System.out.println(hello);

        List<String> list = new ArrayList<String>();

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
