import java.util.Scanner;
import java.util.ArrayList;

public class Duke {

    public Task[] tasks;


    public static void main(String[] args) throws DukeException {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("___________________________________");
        System.out.println("Hello! I'm Duke\n What can I do for you?");
        System.out.println("___________________________________");

        Task[] tasks = new Task[100];
        int counter = 0;

        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();



        while (!input.equals("bye")) {
            if (input.equals("list")) {
                System.out.println("___________________________________");
                for (int i = 0; i < counter; i++) {
                    System.out.println( (i+1) + "."
                            + tasks[i].toString());
                }
                System.out.println("___________________________________");
                input = sc.nextLine();

            }

            else if (input.equals("mark")) {
                int number = sc.nextInt();
                tasks[number-1].mark();
                System.out.println("___________________________________");
                System.out.println("Nice! I've marked this task done: " + "\n"
                        + "[" + tasks[number-1].getStatusIcon() + "] " +
                        tasks[number-1].description);
                System.out.println("___________________________________");
                input = sc.next();
            }

            else if (input.equals("unmark")) {
                int number = sc.nextInt();
                tasks[number-1].unmark();
                System.out.println("___________________________________");
                System.out.println("OK, I've marked this task as not done yet: " + "\n"
                        + "[" + tasks[number-1].getStatusIcon() + "] " +
                        tasks[number-1].description);
                System.out.println("___________________________________");
                input = sc.next();
            }

            else if (input.equals("deadline")) {
                String what = sc.nextLine();
                if (what.equals("")) throw new DukeException("☹ OOPS!!! The description of a deadline cannot be empty.");
                String byWhen = sc.nextLine();
                tasks[counter] = new Deadline(what, byWhen);
                System.out.println("___________________________________");
                System.out.println("Got it. I've added this task:" + "\n"
                        + "  " + tasks[counter].toString() + "\n"
                        + "Now you have " + (counter+1) + " tasks in the list.");
                System.out.println("___________________________________");
                counter++;
                input = sc.nextLine();
            }

            else if (input.equals("todo")) {
                String what = sc.nextLine();
                if (what.equals("")) throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
                tasks[counter] = new Todo(what);
                System.out.println("___________________________________");
                System.out.println("Got it. I've added this task:" + "\n"
                        + "  " + tasks[counter].toString() + "\n"
                        + "Now you have " + (counter+1) + " tasks in the list.");
                System.out.println("___________________________________");
                counter++;
                input = sc.nextLine();
            }

            else if (input.equals("event")) {
                String what = sc.nextLine();
                if (what.equals("")) throw new DukeException("☹ OOPS!!! The description of a event cannot be empty.");
                String atWhen = sc.nextLine();
                tasks[counter] = new Event(what, atWhen);
                System.out.println("___________________________________");
                System.out.println("Got it. I've added this task:" + "\n"
                        + "  " + tasks[counter].toString() + "\n"
                        + "Now you have " + (counter+1) + " tasks in the list.");
                System.out.println("___________________________________");
                counter++;
                input = sc.nextLine();
            }

            else {
                System.out.println("___________________________________");
                throw new DukeException(" ☹ OOPS!!! I'm sorry, but I don't know what that means :-(");

            }

        }

        System.out.println("___________________________________");
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("___________________________________");
    }
}
