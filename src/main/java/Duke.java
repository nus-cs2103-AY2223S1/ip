import java.util.Objects;
import java.util.Scanner;
import java.util.ArrayList;

public class Duke {

    private static final String lineBreak = "    ____________________________________________________________";
    private static final String endLineBreak = "    ____________________________________________________________\n";
    private static ArrayList<Task> todoList = new ArrayList<>();
    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        String logo = " ____        _        \n" +
                      "|  _ \\ _   _| | _____ \n" +
                      "| | | | | | | |/ / _ \\\n" +
                      "| |_| | |_| |   <  __/\n" +
                      "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println(lineBreak);
        System.out.println("     Hello! I'm Duke\n     What can I do for you?");
        System.out.println(endLineBreak);

        String input = sc.nextLine();

        while (!input.equals("bye")) {
            System.out.println(lineBreak);
            if (input.equals("list")) {
                if (todoList.size() == 0) System.out.println(" ☹ OOPS!!! Seems like your list is empty.");
                for (int i = 0; i < todoList.size(); i++) {
                    System.out.println("     " + (i + 1) + ". " + todoList.get(i).toString());
                }
                System.out.println(endLineBreak);
                input = sc.nextLine();
            }
            if (input.startsWith("mark")) {
                System.out.println(endLineBreak);
                input = sc.nextLine();
            }
            if (input.startsWith("unmark")) {
                //handleException(" ☹ OOPS!!! Please enter task to unmark.");
                int taskID = Integer.parseInt(input.substring(7)) - 1;
                todoList.get(taskID).unmark();
                System.out.println(endLineBreak);
                input = sc.nextLine();
            }
            if (input.startsWith("todo")) {
                //handleException(" ☹ OOPS!!! The description of a todo cannot be empty.");
                Todo todo = new Todo(input);
                addTask(todo);
                input = sc.nextLine();
            }
            if (input.startsWith("deadline")) {
                //handleException(" ☹ OOPS!!! The description of a deadline cannot be empty.");
                Deadline deadline = new Deadline(input);
                addTask(deadline);
                input = sc.nextLine();
            }
            if (input.startsWith("event")) {
                //handleException(" ☹ OOPS!!! The description of a event cannot be empty.");
                Event event = new Event(input);
                addTask(event);
                input = sc.nextLine();
            }
        }
        System.out.println(lineBreak);
        System.out.println("     Bye. Hope to see you again soon!");
        System.out.println(endLineBreak);
    }

    public static void addTask(Task task) {
        todoList.add(task);
        System.out.println("    " + " Got it. I've added this task: ");
        System.out.println("       " + task.toString());
        System.out.println("    " + " Now you have " + todoList.size() + " tasks in the list.");
        System.out.println(endLineBreak);
    }

    public static void checkValid(String input) throws DukeException {
        Scanner lineSC = new Scanner(input);
        if (lineSC.next() == null) {
            throw new DukeException("test");
        }
    }
}
