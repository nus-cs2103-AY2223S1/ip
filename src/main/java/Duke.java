import javax.swing.plaf.synth.SynthTextAreaUI;
import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    private ArrayList<Task> list;
    private static Scanner sc;

    public Duke() {
        this.list = new ArrayList<>();
        this.sc = new Scanner(System.in);
    }

    public static void main(String[] args) {
        Duke duke = new Duke();
        Task task;
        duke.greet();
        while (true) {
            String input = sc.nextLine();

            if (input.equals("list")) {
                duke.listTask();
            } else if (input.startsWith("mark")) {
                duke.markDone(input);
            } else if (input.startsWith("unmark")) {
                duke.unmark(input);
            } else if (input.startsWith("todo")) {
                try {
                    if (input.length() <= 5) {
                        throw new Exception("☹ OOPS!!! The description of a todo cannot be empty.");
                    }
                    duke.todo(input);
                } catch (Exception c) {
                    System.out.println(c);
                }
            } else if (input.startsWith("deadline")) {
                try {
                    if (input.length() <= 9) {
                        throw new Exception("☹ OOPS!!! The description of a deadline cannot be empty.");
                    }
                    duke.deadline(input);
                } catch (Exception c) {
                    System.out.println(c);
                }
            } else if (input.startsWith("event")) {
                try {

                    if (input.length() <= 6) {
                        throw new Exception("☹ OOPS!!! The description of a event cannot be empty.");
                    }
                    duke.event(input);
                } catch (Exception c) {
                    System.out.println(c);
                }
            } else if (input.startsWith("delete")) {
                duke.delete(input);
            } else if (input.equals("bye")) {
                duke.bye();
                break;
            }

        }
        sc.close();
    }

    private void greet() {
        System.out.println("Hello!, I'm Yiye.\nWhat can I do for you? ◠‿◠");
    }

    private void bye() {
        System.out.println("Bye! Hope to see you again soon!");
    }

    private void listTask() {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < list.size(); i++) {
            System.out.printf("%d.%s\n", i + 1, list.get(i).toString());
        }
    }

    private void markDone(String input) {
        System.out.println("Nice! I have marked this task as done:");
        int j = Integer.parseInt(input.substring(5)) - 1;
        Task task = list.get(j);
        task.markAsDone();
        System.out.println(task.toString());
    }

    private void unmark(String input) {
        System.out.println("This task is marked as not done:");
        int j = Integer.parseInt(input.substring(7)) - 1;
        Task task = list.get(j);
        task.markAsNotDone();
        System.out.println(task.toString());
    }

    private void todo(String input) {
        System.out.println("Got it, this task is added in your list:");
        Task todo = new Todo(input.substring(5));
        list.add(todo);
        System.out.println(todo.toString());
        if (list.size()>1) {
            System.out.printf("Now you have %d tasks in your list.\n", list.size());
        } else {
            System.out.printf("Now you have %d task in your list.\n", list.size());
        }
    }

    private void deadline(String input) {
        System.out.println("Got it, this task is added in your list:");
        String by = input.substring(input.indexOf("/") + 4);
        Task dl = new Deadline(input.substring(9, input.indexOf("/") - 1), by);
        list.add(dl);
        System.out.println(dl.toString());
        if (list.size()>1) {
            System.out.printf("Now you have %d tasks in your list.\n", list.size());
        } else {
            System.out.printf("Now you have %d task in your list.\n", list.size());
        }
    }

    private void event(String input) {
        System.out.println("Got it, this task is added in your list:");
        String at = input.substring(input.indexOf("/") + 4);
        Task event = new Event(input.substring(6, input.indexOf("/") - 1), at);
        list.add(event);
        System.out.println(event.toString());
        if (list.size()>1) {
            System.out.printf("Now you have %d tasks in your list.\n", list.size());
        } else {
            System.out.printf("Now you have %d task in your list.\n", list.size());
        }
    }

    private void delete(String input) {
        System.out.println("Noted. I've removed this task:");
        int index = Integer.parseInt(input.substring(7)) - 1;
        Task task = list.get(index);
        System.out.println(task);
        list.remove(index);
        if (list.size()>1) {
            System.out.printf("Now you have %d tasks in your list.\n", list.size());
        } else {
            System.out.printf("Now you have %d task in your list.\n", list.size());
        }
    }



}
