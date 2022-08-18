import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;
public class Duke {
    public static void main(String[] args) {
        ArrayList<Task> tasks = new ArrayList<Task>();
        Scanner sc = new Scanner(System.in);

        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello! I'm Lebron.");
        System.out.println("What can I do for you?");

        while (true) {
            String str = sc.nextLine();

            if (str.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            }
            else if (str.equals("list")) {
                System.out.println("Here are the tasks in your list:");
                for (int i = 0, d = 1; i < tasks.size(); i++, d++) {
                    String statement = String.format("%d. ", d);
                    System.out.println(statement + tasks.get(i));
                }
            }
            else if (str.contains(" ") && str.split(" ")[0].equals("mark")) {
                int number = Integer.parseInt(str.split(" ", 2)[1]);
                Task currTask = tasks.get(number - 1);
                currTask.markAsDone();
                System.out.println("Nice! I've marked this task as done:");
                String checkbox = String.format("[%s] %s", currTask.getStatusIcon(), currTask.description);
                System.out.println(checkbox);
            }
            else if (str.contains(" ") && str.split(" ")[0].equals("unmark")) {
                int number = Integer.parseInt(str.split(" ", 2)[1]);
                Task currTask = tasks.get(number - 1);
                currTask.unmark();
                System.out.println("OK, I've marked this task as not done yet:");
                String checkbox = String.format("[%s] %s", currTask.getStatusIcon(), currTask.description);
                System.out.println(checkbox);
            }
            else if(str.contains(" ") && str.split(" ", 2)[0].equals("todo")) {
                todo input = new todo(str.split(" ", 2)[1]);
                tasks.add(input);
                System.out.println("Got it. I've added this task:");
                System.out.println(input.toString());
                System.out.printf("Now you have %d tasks in the list.\n", tasks.size());
            }
            else if(str.contains(" ") && str.split(" ", 2)[0].equals("deadline")) {
                deadline input = new deadline(str.split(" ", 2)[1]);
                tasks.add(input);
                int index = str.indexOf("/");
                input.date = str.substring(index + 4);
                System.out.println("Got it. I've added this task:");
                System.out.println(input.toString());
                System.out.printf("Now you have %d tasks int the list. \n", tasks.size());
            }
            else if(str.contains(" ") && str.split(" ", 2)[0].equals("event")) {
                event input = new event(str.split(" ", 2)[1]);
                tasks.add(input);
                int index = str.indexOf("/");
                input.day = str.substring(index + 4);
                System.out.println("Got it. I've added this task:");
                System.out.println(input.toString());
                System.out.printf("Now you have %d tasks int the list. \n", tasks.size());
            }
            else if (str.contains(" ") && str.split(" ")[0].equals("delete")) {
                int number = Integer.parseInt(str.split(" ", 2)[1]);
                Task currTask = tasks.get(number - 1);
                tasks.remove(number - 1);
                System.out.println("Noted. I've removed this task:");
                String checkbox = String.format("%s", currTask);
                System.out.println(checkbox);
                System.out.printf("Now you have %d tasks int the list. \n", tasks.size());
            }
            else {
                Task input = new Task(str);
                tasks.add(input);
                System.out.println("added: " + str);
            }
        }

    }
}
