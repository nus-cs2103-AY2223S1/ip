import java.util.Scanner;
import java.util.ArrayList;

public class Duke {


    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("___________________________________");
        System.out.println("Hello! I'm Duke\n What can I do for you?");
        System.out.println("___________________________________");

        ArrayList<Task> list = new ArrayList<>();

        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();



        while (!input.equals("bye")) {
            if (input.equals("list")) {
                System.out.println("___________________________________");
                for (int i = 0; i < list.size(); i++) {
                    System.out.println( (i+1) + "." + list.get(i));
                }
                System.out.println("___________________________________");
                input = sc.nextLine();

            }

            else if (input.equals("mark")) {
                s
                System.out.println("___________________________________");
                System.out.println("Nice! I've marked this task done: " + "\n"
                        + "[" + list.get(i+1)

                );

            }

            else {
                Task task = new Task(input);
                list.add(task);
                System.out.println("___________________________________");
                System.out.println("added: " + input);
                System.out.println("___________________________________");
                input = sc.nextLine();
            }

        }

        System.out.println("___________________________________");
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("___________________________________");
    }
}
