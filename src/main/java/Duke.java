import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        ArrayList<Task> arr = new ArrayList();
        // Task[] arr = new Task[100];
        int count = 0;
        System.out.println("Hello! I'm Duke\nWhat can I do for you?");

        Scanner sc = new Scanner(System.in);

        while (true) {
            String item = sc.nextLine();
            if (item.equals("bye")) {
                break;
            } else if (item.equals("list")) {
                for (int i =0; i < count; i++) {
                    String index = String.format("%d.", i+1);
                    System.out.println(index + arr.get(i).toStr());
                }
            } else if (item.startsWith("mark")) {
                String str = item.replace("mark ", "");
                int index = Integer.valueOf(str);
                arr.get(index - 1).mark();
                System.out.println("Nice! I've marked this task as done:\n" + arr.get(index - 1).toStr());
            } else if (item.startsWith("unmark")) {
                String str = item.replace("unmark ", "");
                int index = Integer.valueOf(str);
                arr.get(index - 1).unmark();
                System.out.println("OK, I've marked this task as not done yet:\n" + arr.get(index - 1).toStr());
            } else if (item.startsWith("todo")) {
                try {
                    String str = item.replace("todo", "");
                    arr.add(new Todo(str, count+1));
                    count++;
                    System.out.println(String.format("Got it. I've added this task:\n" +
                                    "%s\n" +
                                    "Now you have %d tasks in the list.",
                            arr.get(count - 1).toStr(),
                            count));
                } catch (MissingDescriptionException err) {
                    System.out.println("OOPS!!! The description of a todo cannot be empty.");
                }
            } else if (item.startsWith("deadline")) {
                try{
                    String[] input = item.split("/by ");
                    String name = input[0].replace("deadline", "");
                    arr.add(new Deadline(name, count + 1, input[1]));
                    count++;
                    System.out.println(String.format("Got it. I've added this task:\n" +
                                    "%s\n" +
                                    "Now you have %d tasks in the list.",
                            arr.get(count - 1).toStr(),
                            count));
                } catch (MissingDescriptionException err) {
                    System.out.println("OOPS!!! The description of a deadline cannot be empty.");
                } catch (ArrayIndexOutOfBoundsException e) {
                    System.out.println("OOPS!!! The end date of a deadline cannot be empty.");
                }
            } else if (item.startsWith("event")) {
                try{
                    String[] input = item.split("/at ");
                    String name = input[0].replace("event", "");
                    arr.add(new Event(name, count + 1, input[1]));
                    count++;
                    System.out.println(String.format("Got it. I've added this task:\n" +
                                    "%s\n" +
                                    "Now you have %d tasks in the list.",
                            arr.get(count - 1).toStr(),
                            count));
                } catch (MissingDescriptionException err) {
                    System.out.println("OOPS!!! The description of an event cannot be empty.");
                } catch (ArrayIndexOutOfBoundsException e) {
                    System.out.println("OOPS!!! The time of an event cannot be empty.");
                }
            } else if (item.startsWith("delete")) {
                try {
                    int index = Integer.valueOf(item.split(" ")[1]);
                    Task t = arr.remove(index - 1);
                    count--;
                    String str =  String.format("Noted. I've removed this task:\n" +
                            "%s\n" +
                            "Now you have %d tasks in the list.",
                            t.toStr(),
                            count);
                    System.out.println(str);

                } catch (IndexOutOfBoundsException e) {
                    System.out.println("OOPS!!! No such task exists.");
                }
            }
            else {
                UnknownInputException err = new UnknownInputException();
                System.out.println(err.toString());
            }
        }

        System.out.println("Bye. Hope to see you again soon!");

    }
}
