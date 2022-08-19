import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;
public class Duke {

    /**
     * Prints greeting message.
     */
    public static void greet() {
        System.out.println("Hello! I'm Lebron.");
        System.out.println("What can I do for you?");
    }

    /**
     * Prints exit message.
     */
    public static void bye() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    /**
     * Prints the task list.
     *
     * @param tasks
     */
    public static void printList(ArrayList<Task> tasks) {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0, d = 1; i < tasks.size(); i++, d++) {
            System.out.println(d + ". " + tasks.get(i));
        }
    }

    /**
     * use todo, event, deadlines
     *
     * @param tasks
     * @param str
     */
    public static void makeTask(ArrayList<Task> tasks, String str) throws DukeException {
        if(str.split(" ", 2)[0].equals("todo")) {

            if (str.equals("todo")) {
                throw new DukeException("The description of a todo cannot be empty.");
            } else {
                String task = str.split(" ", 2)[1];
                todo input = new todo(str);
                tasks.add(input);
                System.out.println("Got it. I've added this task:");
                System.out.println(input);
                System.out.printf("Now you have %d tasks in the list.\n", tasks.size());
            }
        }
        else if(str.split(" ", 2)[0].equals("deadline")) {
            int index = str.indexOf("/");
            String taskName = str.substring(0, index - 1);
            String taskNameOnly = taskName.split(" ",2)[1];
            deadline input = new deadline(taskNameOnly);
            tasks.add(input);
            input.date = str.substring(index + 4);
            System.out.println("Got it. I've added this task:");
            System.out.println(input);
            System.out.printf("Now you have %d tasks int the list. \n", tasks.size());
        }
        else if(str.split(" ", 2)[0].equals("event")) {
            int index = str.indexOf("/");
            String taskName = str.substring(0, index - 1);
            String taskNameOnly = taskName.split(" ",2)[1];
            event input = new event(taskNameOnly);
            tasks.add(input);
            input.day = str.substring(index + 4);
            System.out.println("Got it. I've added this task:");
            System.out.println(input);
            System.out.printf("Now you have %d tasks int the list. \n", tasks.size());
        }
    }

    /**
     * marks tasks in the list
     *
     * @param tasks
     * @param str
     */
    public static void markTask(ArrayList<Task> tasks, String str) throws DukeException {
        int number = Integer.parseInt(str.split(" ", 2)[1]);
        if (1 <= number && number <= tasks.size()) {
            Task currTask = tasks.get(number - 1);
            currTask.markAsDone();
            System.out.println("Nice! I've marked this task as done:");
            String checkbox = String.format("[%s] %s", currTask.getStatusIcon(), currTask.description);
            System.out.println(checkbox);
        } else {
            throw new DukeException("Invalid task number!");
        }

    }

    /**
     * unmarks task on the list.
     *
     * @param tasks
     * @param str
     */
    public static void unmarkTask(ArrayList<Task> tasks, String str) throws DukeException {
        int number = Integer.parseInt(str.split(" ", 2)[1]);
        if (1 <= number && number <= tasks.size()) {
            Task currTask = tasks.get(number - 1);
            currTask.unmark();
            System.out.println("OK, I've marked this task as not done yet:");
            String checkbox = String.format("[%s] %s", currTask.getStatusIcon(), currTask.description);
            System.out.println(checkbox);
        } else {
            throw new DukeException("Invalid task number!");
        }
    }

    /**
     * deletes task from the list.
     *
     * @param tasks
     * @param str
     */
    public static void deleteTask(ArrayList<Task> tasks, String str) throws DukeException {
        int number = Integer.parseInt(str.split(" ", 2)[1]);
        if (1 <= number && number <= tasks.size()) {
            Task currTask = tasks.get(number - 1);
            tasks.remove(number - 1);
            System.out.println("Noted. I've removed this task:");
            String checkbox = String.format("%s", currTask);
            System.out.println(checkbox);
            System.out.printf("Now you have %d tasks int the list. \n", tasks.size());
        } else {
            throw new DukeException("Invalid task number!");
        }
    }
    public static void main(String[] args) {
        ArrayList<Task> tasks = new ArrayList<Task>();
        Scanner sc = new Scanner(System.in);

        greet();

        while (true) {
            String str = sc.nextLine();

            try {
                if (str.equals("bye")) {
                    bye();
                    break;
                }
                else if (str.equals("list")) {
                    printList(tasks);
                }
                else if (str.split(" ")[0].equals("mark")) {
                    markTask(tasks, str);
                }
                else if (str.split(" ")[0].equals("unmark")) {
                    unmarkTask(tasks, str);
                }
                else if(str.split(" ", 2)[0].equals("todo")) {
                    makeTask(tasks, str);
                }
                else if(str.split(" ", 2)[0].equals("deadline")) {
                    makeTask(tasks, str);
                }
                else if(str.split(" ", 2)[0].equals("event")) {
                    makeTask(tasks, str);
                }
                else if (str.split(" ")[0].equals("delete")) {
                    deleteTask(tasks, str);
                }
                else {
                    throw new DukeException("I'm sorry, but I don't know what that means :-(");
                }
            } catch (DukeException d) {
                System.out.println(d.getMessage());


        }
        }

    }
}
