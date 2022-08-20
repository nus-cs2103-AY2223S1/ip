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
        ArrayList<Task> collection = new ArrayList<>();

        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();



        while (!input.equals("bye")) {
            if (input.equals("list")) {
                System.out.println("___________________________________");
                for (int i = 0; i < counter; i++) {
                    System.out.println( (i+1) + "."
                            + collection.get(i).toString());
                }
                System.out.println("___________________________________");
                input = sc.next();

            }

            else if (input.equals("mark")) {
                int number = sc.nextInt();
                collection.get(number-1).mark();
                System.out.println("___________________________________");
                System.out.println("Nice! I've marked this task done: " + "\n"
                        + "[" + collection.get(number-1).getStatusIcon() + "] " +
                        collection.get(number-1).description);
                System.out.println("___________________________________");
                input = sc.next();
            }

            else if (input.equals("unmark")) {
                int number = sc.nextInt();
                collection.get(number-1).unmark();
                System.out.println("___________________________________");
                System.out.println("OK, I've marked this task as not done yet: " + "\n"
                        + "[" + collection.get(number-1).getStatusIcon() + "] " +
                        collection.get(number-1).description);
                System.out.println("___________________________________");
                input = sc.next();
            }

            else if (input.equals("deadline")) {
                String what = sc.nextLine();
                if (what.equals("")) throw new DukeException("☹ OOPS!!! The description of a deadline cannot be empty.");
                String byWhen = sc.nextLine();
                collection.add(new Deadline(what, byWhen));
                System.out.println("___________________________________");
                System.out.println("Got it. I've added this task:" + "\n"
                        + "  " + collection.get(counter).toString() + "\n"
                        + "Now you have " + (counter+1) + " tasks in the list.");
                System.out.println("___________________________________");
                counter++;
                input = sc.nextLine();
            }

            else if (input.equals("todo")) {
                String what = sc.nextLine();
                if (what.equals("")) throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
                collection.add(new Todo(what));
                System.out.println("___________________________________");
                System.out.println("Got it. I've added this task:" + "\n"
                        + "  " + collection.get(counter).toString() + "\n"
                        + "Now you have " + (counter+1) + " tasks in the list.");
                System.out.println("___________________________________");
                counter++;
                input = sc.nextLine();
            }

            else if (input.equals("event")) {
                String what = sc.nextLine();
                if (what.equals("")) throw new DukeException("☹ OOPS!!! The description of a event cannot be empty.");
                String atWhen = sc.nextLine();
                collection.add(new Event(what, atWhen));
                System.out.println("___________________________________");
                System.out.println("Got it. I've added this task:" + "\n"
                        + "  " + collection.get(counter).toString() + "\n"
                        + "Now you have " + (counter+1) + " tasks in the list.");
                System.out.println("___________________________________");
                counter++;
                input = sc.nextLine();
            }

            else if (input.equals("delete")) {
                int number = sc.nextInt();
                Task temp = collection.get(number-1);
                collection.remove(number-1);
                counter--;
                System.out.println("___________________________________");
                System.out.println("Noted. I've removed this task:" + "\n"
                        + "  " + temp.toString() + "\n"
                        + "Now you have " + counter + " tasks in the list.");
                System.out.println("___________________________________");
                input = sc.next();

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
